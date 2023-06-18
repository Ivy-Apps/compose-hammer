package com.ivyapps.composehammer.domain.quickcode.compiler.parser

import com.ivyapps.composehammer.domain.quickcode.compiler.data.IfCondition
import com.ivyapps.composehammer.domain.quickcode.compiler.data.QuickCodeToken

class QuickCodeIfConditionParser(
    private val tokens: List<QuickCodeToken>,
) {
    fun parse(
        position: Int
    ): Pair<IfCondition.Condition, Int>? {
        val scope = QCParserScope(tokens, position)
        if (scope.consumeToken() !is QuickCodeToken.If) return null
        val condition = scope.condition() ?: return null
        if (scope.currentToken() == QuickCodeToken.Then) {
            scope.consumeToken()
        }
        return condition to scope.position
    }

    private fun QCParserScope.condition(): IfCondition.Condition? {
        return or(
            a = { andExpr() },
            b = {
                or(
                    a = { orExpr() },
                    b = { term() },
                )
            }
        )
    }

    private fun QCParserScope.brackets(): IfCondition.Condition.Brackets? {
        if (consumeToken() !is QuickCodeToken.IfExpression.OpenBracket) return null
        val cond = condition() ?: return null
        if (consumeToken() !is QuickCodeToken.IfExpression.CloseBracket) return null
        return IfCondition.Condition.Brackets(cond)
    }

    private fun QCParserScope.andExpr(): IfCondition.Condition.And? {
        val cond1 = term() ?: return null
        if (consumeToken() !is QuickCodeToken.IfExpression.And) return null
        val cond2 = condition() ?: return null
        return IfCondition.Condition.And(
            cond1 = cond1,
            cond2 = cond2
        )
    }

    private fun QCParserScope.orExpr(): IfCondition.Condition.Or? {
        val cond1 = term() ?: return null
        if (consumeToken() !is QuickCodeToken.IfExpression.Or) return null
        val cond2 = condition() ?: return null
        return IfCondition.Condition.Or(
            cond1 = cond1,
            cond2 = cond2
        )
    }

    private fun QCParserScope.notExpr(): IfCondition.Condition.Not? {
        if (consumeToken() !is QuickCodeToken.IfExpression.Not) return null
        val cond = condition() ?: return null
        return IfCondition.Condition.Not(cond)
    }

    private fun QCParserScope.boolVar(): IfCondition.Condition.BoolVar? {
        return (consumeToken() as? QuickCodeToken.IfExpression.BoolVariable)?.let {
            IfCondition.Condition.BoolVar(it.name)
        }
    }

    private fun QCParserScope.term(): IfCondition.Condition? {
        return or(
            a = { brackets() },
            b = {
                or(
                    a = { notExpr() },
                    b = { boolVar() }
                )
            }
        )
    }
}