package com.ivyapps.composehammer.domain.quickcode.compiler.parser

import com.ivyapps.composehammer.domain.quickcode.compiler.data.IfCondition
import com.ivyapps.composehammer.domain.quickcode.compiler.data.QuickCodeToken

class QCParserScope(
    private val tokens: List<QuickCodeToken>,
    initialPosition: Int,
) {
    var position = initialPosition
        private set

    fun or(
        a: QCParserScope.() -> IfCondition.Condition?,
        b: QCParserScope.() -> IfCondition.Condition?,
    ): IfCondition.Condition? {
        val aScope = QCParserScope(tokens, position)
        val resA = aScope.a()
        if (resA != null) {
            position = aScope.position
            return resA
        }

        val bScope = QCParserScope(tokens, position)
        val resB = bScope.b()
        if (resB != null) {
            position = bScope.position
            return resB
        }

        return null
    }

    fun consumeToken(): QuickCodeToken? {
        return tokens.getOrNull(position++).run {
            if (this is QuickCodeToken.Then) null else this
        }
    }

    fun currentToken(): QuickCodeToken? {
        return tokens.getOrNull(position)
    }
}