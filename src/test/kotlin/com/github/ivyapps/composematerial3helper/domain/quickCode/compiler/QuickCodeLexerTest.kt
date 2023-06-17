package com.github.ivyapps.composematerial3helper.domain.quickCode.compiler

import com.intellij.testFramework.TestDataPath
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import com.ivyapps.composehammer.domain.quickcode.compiler.QuickCodeLexer
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
        val text = "#if ({{var1}} && {{var2}}) || !{{var3}}"

        // when
        val tokens = lexer.tokenize(text)

        // then
        tokens shouldBe listOf(
            QuickCodeToken.If,
            QuickCodeToken.IfCondition.OpenBracket,
            QuickCodeToken.IfCondition.BoolVariable("var1"),
            QuickCodeToken.IfCondition.And,
            QuickCodeToken.IfCondition.BoolVariable("var2"),
            QuickCodeToken.IfCondition.CloseBracket,
            QuickCodeToken.IfCondition.Or,
            QuickCodeToken.IfCondition.Not,
            QuickCodeToken.IfCondition.BoolVariable("var3"),
        )
    }


    fun dfdtestMixed() {
        // given
        val text = """
            |class {{name}}ViewModel : ViewModel() {
            |   @Composable
            |   fun content(): {{name}}State {
            |       //TODO:
            |   }
            |      
            |   #if {{hasEvents}} && !({{legacy}} || {{old}}) #then
            |   fun eventHandling(event: {{name}}Event) {
            |       // TODO:
            |   }
            |   #endif
            |}
        """.trimIndent()

        // when
        val tokens = lexer.tokenize(text)

        // then
        tokens shouldBe listOf(
            QuickCodeToken.RawText("class "),
            QuickCodeToken.Variable("name"),
            QuickCodeToken.RawText("ViewModel"),
        )
    }

    private infix fun List<QuickCodeToken>.shouldBe(tokens: List<QuickCodeToken>) {
        assertEquals(tokens, this)
    }
}