package com.ivyapps.composehammer.domain.quickcode.compiler

import com.ivyapps.composehammer.domain.data.quickcode.QCVariable
import com.ivyapps.composehammer.domain.quickcode.compiler.data.*
import com.ivyapps.composehammer.domain.quickcode.compiler.parser.ParseResult
import com.ivyapps.composehammer.domain.quickcode.compiler.parser.QuickCodeParser

class QuickCodeCompiler {
    private val lexer = QuickCodeLexer()
    private val parser = QuickCodeParser()

    fun execute(
        codeTemplate: String,
        vars: Map<String, QCVariableValue>
    ): String {
        val tokens = lexer.tokenize(codeTemplate)
        return when (val res = parser.parse(tokens)) {
            is ParseResult.Failure -> codeTemplate
            is ParseResult.Success -> {
                val interpreter = QuickCodeInterpreter(vars)
                interpreter.evaluate(res.ast)
            }
        }
    }

    fun compile(codeTemplate: String): CompilationResult {
        val tokens = lexer.tokenize(codeTemplate)
        return when (val res = parser.parse(tokens)) {
            is ParseResult.Failure -> CompilationResult.Invalid(res.errorMsg)
            is ParseResult.Success -> {
                CompilationResult.Valid(
                    ast = res.ast,
                    variables = res.ast.extractVars()
                        .toSet().toList()
                )
            }
        }
    }

    private fun QuickCodeAst.extractVars(): List<QCVariable> {
        return when (this) {
            is QuickCodeAst.Begin -> emptyList()
            is IfStatement -> {
                condition.extractBoolVars() +
                        thenBranch.extractVars() +
                        (elseBranch?.extractVars() ?: emptyList())
            }

            is RawText -> emptyList()
            is Variable -> {
                listOf(QCVariable.Str(name))
            }
        } + (next?.extractVars() ?: emptyList())
    }

    private fun IfStatement.Condition.extractBoolVars(): List<QCVariable.Bool> {
        return when (this) {
            is IfStatement.Condition.And -> {
                cond1.extractBoolVars() + cond2.extractBoolVars()
            }

            is IfStatement.Condition.BoolVar -> {
                listOf(QCVariable.Bool(name))
            }

            is IfStatement.Condition.Brackets -> {
                cond.extractBoolVars()
            }

            is IfStatement.Condition.Not -> {
                cond.extractBoolVars()
            }

            is IfStatement.Condition.Or -> {
                cond1.extractBoolVars() + cond2.extractBoolVars()
            }
        }
    }

    sealed interface CompilationResult {
        data class Valid(
            val ast: QuickCodeAst,
            val variables: List<QCVariable>
        ) : CompilationResult

        data class Invalid(val errMsg: String) : CompilationResult
    }
}