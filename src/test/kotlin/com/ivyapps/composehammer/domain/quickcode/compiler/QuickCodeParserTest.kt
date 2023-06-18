package com.ivyapps.composehammer.domain.quickcode.compiler

import com.intellij.testFramework.TestDataPath
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import com.ivyapps.composehammer.domain.quickcode.compiler.data.QuickCodeToken

@TestDataPath("\$CONTENT_ROOT/src/test/testData")
class QuickCodeParserTest : BasePlatformTestCase() {

    private lateinit var parser: QuickCodeParser

    override fun setUp() {
        super.setUp()
        parser = QuickCodeParser()
    }


    fun testSimpleExpression() {
        // given
        val tokens = listOf(
            QuickCodeToken.RawText("println(\"Hello, "),
            QuickCodeToken.Variable("name"),
            QuickCodeToken.RawText("!\""),
            QuickCodeToken.If,
            QuickCodeToken.IfExpression.BoolVariable("cond1"),
            QuickCodeToken.IfExpression.And,
            QuickCodeToken.IfExpression.Not,
            QuickCodeToken.IfExpression.BoolVariable("cond2"),
        )

        // when
        val ast = parser.parse(tokens)

        // then
        println(ast)
    }
}