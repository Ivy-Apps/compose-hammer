package com.github.ivyapps.composematerial3helper.domain.quickCode.compiler

import com.intellij.testFramework.TestDataPath
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import com.ivyapps.composehammer.domain.quickcode.compiler.QuickCodeLexer
import com.ivyapps.composehammer.domain.quickcode.compiler.data.QuickCodeToken

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
        assertEquals(1, tokens.size)
        assertTrue(tokens[0] is QuickCodeToken.RawText)
        assertEquals("Hello, World!", tokens[0].text)
    }


    fun testVariable() {
        // given
        val text = "{{variable}}"

        // when
        val tokens = lexer.tokenize(text)

        // then
        assertEquals(1, tokens.size)
        assertTrue(tokens[0] is QuickCodeToken.Variable)
        assertEquals("{{variable}}", tokens[0].text)
    }


    fun testOperators() {
        // given
        val text = "&& || !"

        // when
        val tokens = lexer.tokenize(text)

        // then
        assertEquals(3, tokens.size)
        assertTrue(tokens[0] is QuickCodeToken.Operator)
        assertTrue(tokens[1] is QuickCodeToken.Operator)
        assertTrue(tokens[2] is QuickCodeToken.Operator)
        assertEquals("&&", tokens[0].text)
        assertEquals("||", tokens[1].text)
        assertEquals("!", tokens[2].text)
    }


    fun testParentheses() {
        // given
        val text = "( )"

        // when
        val tokens = lexer.tokenize(text)

        // then
        assertEquals(2, tokens.size)
        assertTrue(tokens[0] is QuickCodeToken.OpenParenthesis)
        assertTrue(tokens[1] is QuickCodeToken.CloseParenthesis)
    }


    fun testIfCondition() {
        // given
        val text = "#if {{condition}}"

        // when
        val tokens = lexer.tokenize(text)

        // then
        assertEquals(1, tokens.size)
        assertTrue(tokens[0] is QuickCodeToken.IfCondition)
        assertEquals("condition", (tokens[0] as QuickCodeToken.IfCondition).condition)
    }


    fun testElseIfCondition() {
        // given
        val text = "#else if {{condition}}"

        // when
        val tokens = lexer.tokenize(text)

        // then
        assertEquals(1, tokens.size)
        assertTrue(tokens[0] is QuickCodeToken.ElseIfCondition)
        assertEquals("condition", (tokens[0] as QuickCodeToken.ElseIfCondition).condition)
    }


    fun testElse() {
        // given
        val text = "#else"

        // when
        val tokens = lexer.tokenize(text)

        // then
        assertEquals(1, tokens.size)
        assertTrue(tokens[0] is QuickCodeToken.Else)
    }


    fun testEndIf() {
        // given
        val text = "#endif"

        // when
        val tokens = lexer.tokenize(text)

        // then
        assertEquals(1, tokens.size)
        assertTrue(tokens[0] is QuickCodeToken.EndIf)
    }


    fun testMixed() {
        // given
        val text = "Hello, {{name}}! #if {{condition}} (&& || !)"

        // when
        val tokens = lexer.tokenize(text)

        // then
        assertEquals(8, tokens.size)
        assertEquals("Hello, ", tokens[0].text)
        assertEquals("{{name}}", tokens[1].text)
        assertTrue(tokens[2] is QuickCodeToken.RawText)
        assertTrue(tokens[3] is QuickCodeToken.IfCondition)
        assertTrue(tokens[4] is QuickCodeToken.OpenParenthesis)
        assertTrue(tokens[5] is QuickCodeToken.Operator)
        assertTrue(tokens[6] is QuickCodeToken.Operator)
        assertTrue(tokens[7] is QuickCodeToken.CloseParenthesis)
    }
}