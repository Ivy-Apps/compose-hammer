package com.ivyapps.composehammer.domain.quickcode.compiler

import com.ivyapps.composehammer.domain.quickcode.compiler.data.*
import com.ivyapps.composehammer.domain.quickcode.compiler.data.IfCondition.Condition

class QuickCodeParser {
    fun parse(tokens: List<QuickCodeToken>): ParseResult {
        return try {
            ParseResult.Success(parseInternal(tokens))
        } catch (e: Exception) {
            ParseResult.Failure(e.message ?: "unknown error")
        }
    }

    private fun parseInternal(
        tokens: List<QuickCodeToken>,
    ): QuickCodeAst.Begin {
        var position = 0
        val begin = QuickCodeAst.Begin
        var currentAst: QuickCodeAst = begin

        while (position < tokens.size) {
            when (val token = tokens[position]) {
                is QuickCodeToken.RawText -> {
                    val node = RawText(token.text)
                    currentAst.next = node
                    currentAst = node
                    position++
                }

                is QuickCodeToken.Variable -> {
                    val node = Variable(token.name)
                    currentAst.next = node
                    currentAst = node
                    position++
                }

                is QuickCodeToken.If -> {
                    val (node, nextPos) = parseIfCondition(tokens, position)
                    currentAst.next = node
                    currentAst = node
                    position = nextPos
                }

                else -> error("Unknown token type: $token")
            }
        }

        return begin
    }

    private fun parseIfCondition(
        tokens: List<QuickCodeToken>,
        startPos: Int
    ): Pair<IfCondition, Int> {
        var pos = startPos

        // Skip #if
        pos++

        val condition = parseCondition(tokens, pos)


        val thenBranch = parseUntil(tokens, pos, QuickCodeToken.Else, QuickCodeToken.EndIf)
        pos += thenBranch.second

        val elseBranch = if (tokens.getOrNull(pos) == QuickCodeToken.Else) {
            val elseResult = parseUntil(tokens, pos + 1, QuickCodeToken.EndIf)
            pos += elseResult.second
            elseResult.first
        } else {
            null
        }

        return IfCondition(condition, thenBranch.first, elseBranch) to pos
    }

    private fun parseUntil(
        tokens: List<QuickCodeToken>,
        startPos: Int,
        vararg endTokens: QuickCodeToken
    ): Pair<QuickCodeAst, Int> {
        val begin = QuickCodeAst.Begin
        var currentAst: QuickCodeAst = begin
        var pos = startPos

        loop@ while (pos < tokens.size) {
            when (val token = tokens[pos]) {
                is QuickCodeToken.RawText -> {
                    val node = RawText(token.text)
                    currentAst.next = node
                    currentAst = node
                    pos++
                }

                is QuickCodeToken.Variable -> {
                    val node = Variable(token.name)
                    currentAst.next = node
                    currentAst = node
                    pos++
                }

                is QuickCodeToken.If -> {
                    val (node, nextPos) = parseIfCondition(tokens, pos)
                    currentAst.next = node
                    currentAst = node
                    pos = nextPos
                }

                else -> {
                    if (token in endTokens) {
                        break@loop
                    } else {
                        throw IllegalArgumentException("Unexpected token type: $token")
                    }
                }
            }
        }

        return begin to (pos - startPos)
    }

    private fun parseCondition(
        tokens: List<QuickCodeToken>,
        pos: Int
    ): Condition {
        val parser = IfExpressionParser(tokens)
        return requireNotNull(parser.parse(pos)) {
            "Invalid if condition!"
        }
    }
}

sealed interface ParseResult {
    data class Success(
        val ast: QuickCodeAst
    ) : ParseResult

    data class Failure(
        val errorMsg: String
    ) : ParseResult
}