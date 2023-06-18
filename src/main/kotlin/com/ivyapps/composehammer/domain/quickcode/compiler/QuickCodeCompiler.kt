package com.ivyapps.composehammer.domain.quickcode.compiler

import com.ivyapps.composehammer.domain.data.quickcode.QCVariable
import com.ivyapps.composehammer.domain.quickcode.compiler.data.IfStatement
import com.ivyapps.composehammer.domain.quickcode.compiler.data.QuickCodeAst
import com.ivyapps.composehammer.domain.quickcode.compiler.data.RawText
import com.ivyapps.composehammer.domain.quickcode.compiler.data.Variable
import com.ivyapps.composehammer.domain.quickcode.compiler.parser.ParseResult
import com.ivyapps.composehammer.domain.quickcode.compiler.parser.QuickCodeParser

class QuickCodeCompiler {
    private val lexer = QuickCodeLexer()
    private val parser = QuickCodeParser()

    fun compile(templateCode: String): CompilationResult {
        val tokens = lexer.tokenize(templateCode)
        when (val res = parser.parse(tokens)) {
            is ParseResult.Failure -> CompilationResult.Invalid(res.errorMsg)
            is ParseResult.Success -> CompilationResult.Valid(res.ast.extractVars())
        }
    }

    private fun QuickCodeAst.extractVars(): List<QCVariable> {
        when (this) {
            is QuickCodeAst.Begin -> emptyList()
            is IfStatement -> TODO()
            is RawText -> TODO()
            is Variable -> TODO()
        }
    }

    sealed interface CompilationResult {
        data class Valid(
            val variables: List<QCVariable>
        ) : CompilationResult

        data class Invalid(val errMsg: String) : CompilationResult
    }
}