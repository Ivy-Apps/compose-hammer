package com.ivyapps.composehammer.domain.quickcode.compiler.parser

import com.ivyapps.composehammer.domain.quickcode.compiler.data.*
import com.ivyapps.composehammer.domain.quickcode.compiler.data.IfStatement.Condition

class QuickCodeParser {
    fun parse(tokens: List<QuickCodeToken>): ParseResult {
        return try {
            ParseResult.Success(parseInternal(tokens))
        } catch (e: Exception) {
            ParseResult.Failure(e.message ?: "unknown error")
        }
    }

    private fun parseInternal(
        tokens: List<QuickCodeToken>,
    ): QuickCodeAst.Begin {
        val astBuilder = AstBuilder()
        val parserScope = QCParserScope<QuickCodeAst>(tokens, initialPosition = 0)

        with(parserScope) {
            var currentToken = consumeToken()
            while (currentToken != null) {
                parseToken(astBuilder, currentToken)
                currentToken = consumeToken()
            }
        }
        return astBuilder.begin
    }


    private fun QCParserScope<QuickCodeAst>.parseToken(
        astBuilder: AstBuilder,
        token: QuickCodeToken,
    ) {
        when (token) {
            QuickCodeToken.If -> {
                parseIfStatement(astBuilder)
            }

            is QuickCodeToken.RawText -> {
                astBuilder.addNode(RawText(token.text))
            }

            is QuickCodeToken.Variable -> {
                astBuilder.addNode(Variable(token.name))
            }

            else -> {
                error("Unexpected token: $token")
            }
        }
    }

    private fun QCParserScope<QuickCodeAst>.parseIfStatement(
        ast: AstBuilder
    ) {
        val condition = parseIfCondition()

        val thenAst = AstBuilder()
        val thenEnd = parseUntil(thenAst, QuickCodeToken.Else, QuickCodeToken.EndIf)

        val elseAst = if (thenEnd == QuickCodeToken.Else) {
            AstBuilder().apply {
                parseUntil(this, QuickCodeToken.EndIf)
            }
        } else null

        val ifStm = IfStatement(
            condition = condition,
            thenBranch = thenAst.begin,
            elseBranch = elseAst?.begin,
        )
        ast.addNode(ifStm)
    }

    private fun QCParserScope<QuickCodeAst>.parseUntil(
        astBuilder: AstBuilder,
        vararg end: QuickCodeToken
    ): QuickCodeToken {
        while (true) {
            val token = consumeToken()
            requireNotNull(token) {
                "Uncompleted if branch. It must end with $end."
            }
            if (token in end) {
                return token
            }
            parseToken(astBuilder, token)
        }
    }

    private fun QCParserScope<QuickCodeAst>.parseIfCondition(
    ): Condition {
        val parser = QuickCodeIfConditionParser(tokens)
        val (condition, newPos) = requireNotNull(parser.parse(position)) {
            "Invalid if condition!"
        }
        changePosition(newPos)
        return condition
    }

    private data class AstBuilder(
        val begin: QuickCodeAst.Begin = QuickCodeAst.Begin,
        private var current: QuickCodeAst = begin
    ) {
        fun addNode(node: QuickCodeAst) {
            current.next = node
            current = node
        }
    }
}

