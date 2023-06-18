package com.ivyapps.composehammer.domain.quickcode.compiler

import com.intellij.testFramework.TestDataPath
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import com.ivyapps.composehammer.domain.quickcode.compiler.data.QuickCodeToken
import junit.framework.TestCase.*

@TestDataPath("\$CONTENT_ROOT/src/test/testData")
class QuickCodeLexerTest : BasePlatformTestCase() {

    private lateinit var lexer: QuickCodeLexer

    override fun setUp() {
        super.setUp()
        lexer = QuickCodeLexer()
    }

    fun testEmptyString() {
        // given
        val text = ""

        // when
        val tokens = lexer.tokenize(text)

        // then
        assertEquals(0, tokens.size)
    }

    fun testRawText() {
        // given
        val text = "Hello, World!"

        // when
        val tokens = lexer.tokenize(text)

        // then
        tokens shouldBe listOf(
            QuickCodeToken.RawText("Hello, World!")
        )
    }

    fun testVariable() {
        // given
        val text = "{{variable}}"

        // when
        val tokens = lexer.tokenize(text)

        // then
        tokens shouldBe listOf(
            QuickCodeToken.Variable("variable")
        )
    }

    fun testIfCondition() {
        // given
        val text = "#if ({{var1}} AND {{var2}}) OR NOT {{var3}}"

        // when
        val tokens = lexer.tokenize(text)

        // then
        tokens shouldBe listOf(
            QuickCodeToken.If,
            QuickCodeToken.IfExpression.OpenBracket,
            QuickCodeToken.IfExpression.BoolVariable("var1"),
            QuickCodeToken.IfExpression.And,
            QuickCodeToken.IfExpression.BoolVariable("var2"),
            QuickCodeToken.IfExpression.CloseBracket,
            QuickCodeToken.IfExpression.Or,
            QuickCodeToken.IfExpression.Not,
            QuickCodeToken.IfExpression.BoolVariable("var3"),
        )
    }


    fun testMixed() {
        // given
        val text = """
            class {{name}}ViewModel : ViewModel() {
               @Composable
               fun content(): {{name}}State {
                   //TODO:
               }
                  
               #if {{hasEvents}} AND NOT ({{legacy}} OR {{old}}) #then
               fun eventHandling(event: {{name}}Event) {
                   // TODO:
               }
               #endif
            }
        """.trimIndent()

        // when
        val tokens = lexer.tokenize(text)

        // then
        tokens.printActual()
    }

    private infix fun List<QuickCodeToken>.shouldBe(expectedTokens: List<QuickCodeToken>) {
        printActual()

        for (index in this.indices) {
            val actualToken = this[index]
            val expected = expectedTokens[index]
            println("#$index: $actualToken")
            assertEquals(expected, actualToken)
        }
    }

    private fun List<QuickCodeToken>.printActual() {
        println("Actual result:")
        for ((index, token) in this.withIndex()) {
            println("#$index: $token")
        }
        println("------")
    }
}