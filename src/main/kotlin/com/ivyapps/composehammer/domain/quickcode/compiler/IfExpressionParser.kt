package com.ivyapps.composehammer.domain.quickcode.compiler

import com.ivyapps.composehammer.domain.quickcode.compiler.data.IfCondition
import com.ivyapps.composehammer.domain.quickcode.compiler.data.QuickCodeToken

class IfExpressionParser(
    private val tokens: List<QuickCodeToken>,
) {
    fun parse(
        position: Int
    ): IfCondition.Condition? {
        return ParseScope(position).condition()
    }

    private fun ParseScope.condition(): IfCondition.Condition? {
        return or(
            a = { brackets() },
            b = {
                or(
                    a = { andExpr() },
                    b = {
                        or(
                            a = { orExpr() },
                            b = {
                                or(
                                    a = { notExpr() },
                                    b = { boolVar() }
                                )
                            }
                        )
                    }
                )
            }
        )
    }

    private fun ParseScope.brackets(): IfCondition.Condition.Brackets? {
        if (tokens[position++] !is QuickCodeToken.IfExpression.OpenBracket) return null
        val cond = condition() ?: return null
        if (tokens[position++] !is QuickCodeToken.IfExpression.CloseBracket) return null
        return IfCondition.Condition.Brackets(cond)
    }

    private fun ParseScope.andExpr(): IfCondition.Condition.And? {
        val cond1 = condition() ?: return null
        if (tokens[position++] !is QuickCodeToken.IfExpression.And) return null
        val cond2 = condition() ?: return null
        return IfCondition.Condition.And(
            cond1 = cond1,
            cond2 = cond2
        )
    }

    private fun ParseScope.orExpr(): IfCondition.Condition.Or? {
        val cond1 = condition() ?: return null
        if (tokens[position++] !is QuickCodeToken.IfExpression.Or) return null
        val cond2 = condition() ?: return null
        return IfCondition.Condition.Or(
            cond1 = cond1,
            cond2 = cond2
        )
    }

    private fun ParseScope.notExpr(): IfCondition.Condition.Not? {
        if (tokens[position++] !is QuickCodeToken.IfExpression.Not) return null
        val cond = condition() ?: return null
        return IfCondition.Condition.Not(cond)
    }

    private fun ParseScope.boolVar(): IfCondition.Condition.BoolVar? {
        return (tokens[position++] as? QuickCodeToken.IfExpression.BoolVariable)?.let {
            IfCondition.Condition.BoolVar(it.name)
        }
    }

    private fun ParseScope.or(
        a: ParseScope.() -> IfCondition.Condition?,
        b: ParseScope.() -> IfCondition.Condition?,
    ): IfCondition.Condition? {
        val aScope = ParseScope(position)
        val resA = aScope.a()
        if (resA != null) {
            position = aScope.position
            return resA
        }

        val bScope = ParseScope(position)
        val resB = bScope.b()
        if (resB != null) {
            position = bScope.position
            return resB
        }

        return null
    }

    private data class ParseScope(
        var position: Int
    )
}