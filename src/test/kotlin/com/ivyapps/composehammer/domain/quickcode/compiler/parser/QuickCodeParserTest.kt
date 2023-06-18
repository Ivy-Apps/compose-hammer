package com.ivyapps.composehammer.domain.quickcode.compiler.parser

import com.intellij.testFramework.TestDataPath
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import com.ivyapps.composehammer.domain.quickcode.compiler.data.*
import junit.framework.TestCase

@TestDataPath("\$CONTENT_ROOT/src/test/testData")
class QuickCodeParserTest : BasePlatformTestCase() {

    private lateinit var parser: QuickCodeParser

    override fun setUp() {
        super.setUp()
        parser = QuickCodeParser()
    }


    fun testSimpleIf() {
        // given
        val tokens = listOf(
            QuickCodeToken.RawText("println(\"Hello, "),
            QuickCodeToken.Variable("name"),
            QuickCodeToken.RawText("!\""),
            QuickCodeToken.If,
            QuickCodeToken.IfExpression.BoolVariable("a"),
            QuickCodeToken.IfExpression.And,
            QuickCodeToken.IfExpression.Not,
            QuickCodeToken.IfExpression.BoolVariable("b"),
            QuickCodeToken.Then,
            QuickCodeToken.RawText("test"),
            QuickCodeToken.EndIf,
        )

        // when
        val res = parser.parse(tokens)

        // then
        res shouldBe buildAst(
            RawText("println(\"Hello, "),
            Variable("name"),
            RawText("!\""),
            IfStatement(
                condition = IfStatement.Condition.And(
                    IfStatement.Condition.BoolVar("a"),
                    IfStatement.Condition.Not(IfStatement.Condition.BoolVar("b"))
                ),
                thenBranch = buildAst(
                    RawText("test")
                )
            )
        )
    }

    fun testIfElse() {
        // given
        val tokens = listOf(
            QuickCodeToken.If,
            QuickCodeToken.IfExpression.BoolVariable("day"),
            QuickCodeToken.Then,
            QuickCodeToken.RawText("Good day, "),
            QuickCodeToken.Variable("name"),
            QuickCodeToken.RawText("!"),
            QuickCodeToken.Else,
            QuickCodeToken.RawText("Good night!"),
            QuickCodeToken.EndIf,
        )

        // when
        val res = parser.parse(tokens)

        // then
        res shouldBe buildAst(
            IfStatement(
                condition = IfStatement.Condition.BoolVar("day"),
                thenBranch = buildAst(
                    RawText("Good day, "),
                    Variable("name"),
                    RawText("!")
                ),
                elseBranch = buildAst(
                    RawText("Good night!")
                )
            )
        )
    }

    // region utils
    private fun QuickCodeAst.printAst(
        indent: Int = 0,
    ) {
        when (this) {
            is QuickCodeAst.Begin -> {
                printI("Begin", indent)
            }

            is IfStatement -> {
                printI("If: $condition", indent)
                thenBranch.printAst(indent = indent + 4)
                elseBranch?.printAst(indent = indent + 4)
            }

            is RawText -> {
                printI("RawText: $text", indent)
            }

            is Variable -> {
                printI("Variable: $name", indent)
            }
        }
        next?.printAst(indent)
    }

    private fun printI(text: String, indent: Int) {
        println(" ".repeat(indent) + text)
    }

    private infix fun ParseResult.shouldBe(expected: QuickCodeAst) {
        println("Actual:")
        (this as ParseResult.Success).ast.printAst()
        println("-------")
        println()
        println("Expected:")
        expected.printAst()
        println("-------")
        TestCase.assertEquals(ParseResult.Success(expected), this)
    }

    private fun buildAst(vararg ast: QuickCodeAst): QuickCodeAst {
        val begin = QuickCodeAst.Begin()
        var current: QuickCodeAst = begin
        for (node in ast) {
            current.next = node
            current = node
        }
        return begin
    }
    // endregion
}