package com.ivyapps.composehammer.domain.quickcode.compiler

import com.intellij.testFramework.TestDataPath
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import com.ivyapps.composehammer.domain.quickcode.compiler.data.IfCondition.*
import com.ivyapps.composehammer.domain.quickcode.compiler.data.QuickCodeToken
import junit.framework.TestCase

@TestDataPath("\$CONTENT_ROOT/src/test/testData")
class QuickCodeIfConditionParserTest : BasePlatformTestCase() {

    fun testBoolVar() {
        // given
        val tokens = listOf(
            QuickCodeToken.RawText("something"),
            QuickCodeToken.If,
            QuickCodeToken.IfExpression.BoolVariable("a"),
            QuickCodeToken.Then,
            QuickCodeToken.RawText("Okay")
        )
        val parser = QuickCodeIfConditionParser(tokens)

        // when
        val res = parser.parse(position = 1)

        // then
        res shouldBe Condition.BoolVar("a")
        res newPosShouldBe 4
    }

    fun testSimpleExpression() {
        // given
        val tokens = listOf(
            QuickCodeToken.If,
            QuickCodeToken.IfExpression.BoolVariable("cond1"),
            QuickCodeToken.IfExpression.And,
            QuickCodeToken.IfExpression.Not,
            QuickCodeToken.IfExpression.BoolVariable("cond2"),
            QuickCodeToken.Then,
            QuickCodeToken.RawText("Okay")
        )
        val parser = QuickCodeIfConditionParser(tokens)

        // when
        val res = parser.parse(position = 0)

        // then
        res shouldBe Condition.And(
            Condition.BoolVar("cond1"),
            Condition.Not(Condition.BoolVar("cond2"))
        )
        res newPosShouldBe 6
    }


    private infix fun Pair<Condition, Int>?.shouldBe(expected: Condition?) {
        printCondition(this?.first)
        TestCase.assertEquals(expected, this?.first)
    }


    private fun printCondition(condition: Condition?) {
        println("Actual: $condition")
    }

    private infix fun Pair<Condition, Int>?.newPosShouldBe(expected: Int) {
        TestCase.assertEquals(expected, this?.second)
    }
}