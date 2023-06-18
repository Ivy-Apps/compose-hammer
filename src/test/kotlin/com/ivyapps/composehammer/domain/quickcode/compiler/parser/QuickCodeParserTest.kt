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


    fun testSimpleExpression() {
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
        res.printResult()
        res shouldBe buildAst(
            QuickCodeAst.Begin,
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

    // region utils
    private fun ParseResult.printResult() {
        when (this) {
            is ParseResult.Failure -> "Failed to parse AST: ${this.errorMsg}"
            is ParseResult.Success -> {
                ast.printAst(indent = 0)
            }
        }
    }

    private fun QuickCodeAst.printAst(
        indent: Int = 0,
    ) {
        when (this) {
            QuickCodeAst.Begin -> {
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
        TestCase.assertEquals(ParseResult.Success(expected), this)
    }

    private fun buildAst(vararg ast: QuickCodeAst): QuickCodeAst {
        var first: QuickCodeAst? = null
        var current: QuickCodeAst = QuickCodeAst.Begin
        for (node in ast) {
            if (first == null) {
                first = node
            } else {
                current.next = node
            }
            current = node
        }
        return requireNotNull(first)
    }
    // endregion
}