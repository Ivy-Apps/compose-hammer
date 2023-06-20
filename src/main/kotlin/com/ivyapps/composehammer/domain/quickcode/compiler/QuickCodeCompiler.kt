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
                    variables = res.ast.extractAllVars()
                        .fixVariableConflicts()
                )
            }
        }
    }

    private fun List<QCVariable>.fixVariableConflicts(): List<QCVariable> {
        val namesSet = mutableSetOf<String>()
        val res = mutableListOf<QCVariable>()
        for (variable in this) {
            if (variable.name in namesSet) {
                // there's a conflict
                if (variable is QCVariable.Bool) {
                    // Bools have higher priority
                    res.remove(res.find { it.name == variable.name })
                    res.add(variable)
                }
            } else {
                res.add(variable)
            }
            namesSet.add(variable.name)
        }
        return res
    }

    private fun QuickCodeAst.extractAllVars(): List<QCVariable> {
        return when (this) {
            is QuickCodeAst.Begin -> emptyList()
            is IfStatement -> {
                condition.extractBoolVars() +
                        thenBranch.extractAllVars() +
                        (elseBranch?.extractAllVars() ?: emptyList())
            }

            is RawText -> emptyList()
            is Variable -> {
                listOf(QCVariable.Str(name))
            }
        } + (next?.extractAllVars() ?: emptyList())
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