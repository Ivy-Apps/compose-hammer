package com.ivyapps.composehammer.domain.quickcode.compiler

import com.intellij.testFramework.TestDataPath
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import com.ivyapps.composehammer.domain.quickcode.compiler.data.QuickCodeToken

@TestDataPath("\$CONTENT_ROOT/src/test/testData")
class QuickCodeIfConditionParserTest : BasePlatformTestCase() {

    fun testSimpleExpression() {
        // given
        val tokens = listOf(
            QuickCodeToken.IfExpression.BoolVariable("cond1"),
            QuickCodeToken.IfExpression.And,
            QuickCodeToken.IfExpression.Not,
            QuickCodeToken.IfExpression.BoolVariable("cond2"),
        )
        val parser = QuickCodeIfConditionParser(tokens)

        // when
        val ast = parser.parse(position = 0)

        // then
        println(ast)
    }
}