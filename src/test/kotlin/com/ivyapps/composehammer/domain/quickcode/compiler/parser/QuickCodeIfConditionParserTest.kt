package com.ivyapps.composehammer.domain.quickcode.compiler.parser

import com.intellij.testFramework.TestDataPath
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import com.ivyapps.composehammer.domain.quickcode.compiler.data.IfCondition.*
import com.ivyapps.composehammer.domain.quickcode.compiler.data.QuickCodeToken
import com.ivyapps.composehammer.domain.quickcode.compiler.data.QuickCodeToken.IfExpression
import junit.framework.TestCase

@TestDataPath("\$CONTENT_ROOT/src/test/testData")
class QuickCodeIfConditionParserTest : BasePlatformTestCase() {

    fun testBoolVar() {
        // given
        val parser = parser(
            QuickCodeToken.RawText("something"),
            QuickCodeToken.If,
            IfExpression.BoolVariable("a"),
            QuickCodeToken.Then,
            QuickCodeToken.RawText("Okay")
        )

        // when
        val res = parser.parse(position = 1)

        // then
        res conditionShouldBe Condition.BoolVar("a")
        res newPosShouldBe 4
    }

    fun testSimpleExpression() {
        // given
        val parser = parser(
            QuickCodeToken.If,
            IfExpression.BoolVariable("cond1"),
            IfExpression.And,
            IfExpression.Not,
            IfExpression.BoolVariable("cond2"),
            QuickCodeToken.Then,
            QuickCodeToken.RawText("Okay")
        )

        // when
        val res = parser.parse(position = 0)

        // then
        res conditionShouldBe Condition.And(
            Condition.BoolVar("cond1"),
            Condition.Not(Condition.BoolVar("cond2"))
        )
        res newPosShouldBe 6
    }

    fun testDoubleAnd() {
        // given
        val parser = parser(
            QuickCodeToken.If,
            IfExpression.BoolVariable("a"),
            IfExpression.And,
            IfExpression.BoolVariable("b"),
            IfExpression.And,
            IfExpression.BoolVariable("c"),
            QuickCodeToken.Then,
            QuickCodeToken.RawText("Okay")
        )

        // when
        val res = parser.parse(position = 0)

        // then
        res conditionShouldBe Condition.And(
            Condition.BoolVar("a"),
            Condition.And(
                Condition.BoolVar("b"),
                Condition.BoolVar("c"),
            )
        )
        res newPosShouldBe 7
    }

    fun testTripleOr() {
        // given
        val parser = parser(
            QuickCodeToken.If,
            IfExpression.BoolVariable("a"),
            IfExpression.Or,
            IfExpression.BoolVariable("b"),
            IfExpression.Or,
            IfExpression.BoolVariable("c"),
            IfExpression.Or,
            IfExpression.BoolVariable("d"),
            QuickCodeToken.Then,
            QuickCodeToken.RawText("Okay"),
            QuickCodeToken.Variable("name"),
        )

        // when
        val res = parser.parse(position = 0)

        // then
        res conditionShouldBe Condition.Or(
            Condition.BoolVar("a"),
            Condition.Or(
                Condition.BoolVar("b"),
                Condition.Or(
                    Condition.BoolVar("c"),
                    Condition.BoolVar("d"),
                )
            )
        )
        res newPosShouldBe 9
    }

    // region utils
    private fun parser(
        vararg tokens: QuickCodeToken
    ): QuickCodeIfConditionParser {
        return QuickCodeIfConditionParser(tokens.toList())
    }

    private infix fun Pair<Condition, Int>?.conditionShouldBe(expected: Condition?) {
        printCondition(this?.first)
        TestCase.assertEquals(expected, this?.first)
    }


    private fun printCondition(condition: Condition?) {
        println("Actual: $condition")
    }

    private infix fun Pair<Condition, Int>?.newPosShouldBe(expected: Int) {
        TestCase.assertEquals(expected, this?.second)
    }
    // endregion
}