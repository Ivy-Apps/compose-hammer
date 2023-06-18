package com.ivyapps.composehammer.domain.quickcode.compiler.parser

import com.ivyapps.composehammer.domain.quickcode.compiler.data.IfStatement
import com.ivyapps.composehammer.domain.quickcode.compiler.data.QuickCodeToken

typealias IfCondParserScope = QCParserScope<IfStatement.Condition>

class QuickCodeIfConditionParser(
    private val tokens: List<QuickCodeToken>,
) {
    fun parse(
        position: Int
    ): Pair<IfStatement.Condition, Int>? {
        val scope = IfCondParserScope(tokens, position)
        val condition = scope.condition() ?: return null
        if (scope.currentToken() == QuickCodeToken.Then) {
            scope.consumeToken()
        }
        return condition to scope.position
    }

    private fun IfCondParserScope.condition(): IfStatement.Condition? {
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

    private fun IfCondParserScope.brackets(): IfStatement.Condition.Brackets? {
        if (consumeToken() !is QuickCodeToken.IfExpression.OpenBracket) return null
        val cond = condition() ?: return null
        if (consumeToken() !is QuickCodeToken.IfExpression.CloseBracket) return null
        return IfStatement.Condition.Brackets(cond)
    }

    private fun IfCondParserScope.andExpr(): IfStatement.Condition.And? {
        val cond1 = term() ?: return null
        if (consumeToken() !is QuickCodeToken.IfExpression.And) return null
        val cond2 = condition() ?: return null
        return IfStatement.Condition.And(
            cond1 = cond1,
            cond2 = cond2
        )
    }

    private fun IfCondParserScope.orExpr(): IfStatement.Condition.Or? {
        val cond1 = term() ?: return null
        if (consumeToken() !is QuickCodeToken.IfExpression.Or) return null
        val cond2 = condition() ?: return null
        return IfStatement.Condition.Or(
            cond1 = cond1,
            cond2 = cond2
        )
    }

    private fun IfCondParserScope.notExpr(): IfStatement.Condition.Not? {
        if (consumeToken() !is QuickCodeToken.IfExpression.Not) return null
        val cond = condition() ?: return null
        return IfStatement.Condition.Not(cond)
    }

    private fun IfCondParserScope.boolVar(): IfStatement.Condition.BoolVar? {
        return (consumeToken() as? QuickCodeToken.IfExpression.BoolVariable)?.let {
            IfStatement.Condition.BoolVar(it.name)
        }
    }

    private fun IfCondParserScope.term(): IfStatement.Condition? {
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