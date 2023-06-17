package com.ivyapps.composehammer.domain.quickcode.compiler

import com.ivyapps.composehammer.domain.quickcode.compiler.data.QuickCodeToken

class QuickCodeLexer {

    fun tokenize(text: String): List<QuickCodeToken> {
        var position = 0

        val tokens = mutableListOf<QuickCodeToken>()
        while (position < text.length) {
            when {
                text.startsWith("{{", position) -> {
                    val end = text.indexOfOrNull("}}", position)?.plus(2)
                    if (end != null) {
                        tokens.add(QuickCodeToken.Variable(text.substring(position, end)))
                        position = end
                    }
                }

                text.startsWith("&&", position) -> {
                    tokens.add(QuickCodeToken.Operator(text.substring(position, position + 2)))
                    position += 2
                }

                text.startsWith("||", position) -> {
                    tokens.add(QuickCodeToken.Operator(text.substring(position, position + 2)))
                    position += 2
                }

                text.startsWith("!", position) -> {
                    tokens.add(QuickCodeToken.Operator(text.substring(position, position + 1)))
                    position += 1
                }

                text.startsWith("(", position) -> {
                    tokens.add(QuickCodeToken.OpenParenthesis())
                    position += 1
                }

                text.startsWith(")", position) -> {
                    tokens.add(QuickCodeToken.CloseParenthesis())
                    position += 1
                }

                text.startsWith("#if {{", position) -> {
                    val end = text.indexOfOrNull("}}", position)?.plus(2)
                    if (end != null) {
                        tokens.add(QuickCodeToken.IfCondition(text.substring(position + 6, end - 2)))
                        position = end
                    }
                }

                text.startsWith("#else if {{", position) -> {
                    val end = text.indexOfOrNull("}}", position)?.plus(2)
                    if (end != null) {
                        tokens.add(QuickCodeToken.ElseIfCondition(text.substring(position + 11, end - 2)))
                        position = end
                    }
                }

                text.startsWith("#else", position) -> {
                    tokens.add(QuickCodeToken.Else)
                    position += 5
                }

                text.startsWith("#endif", position) -> {
                    tokens.add(QuickCodeToken.EndIf)
                    position += 6
                }

                else -> {
                    val nextSpecialChar = listOfNotNull(
                        text.indexOfOrNull("{{", position),
                        text.indexOfOrNull("&&", position),
                        text.indexOfOrNull("||", position),
                        text.indexOfOrNull("!", position),
                        text.indexOfOrNull("(", position),
                        text.indexOfOrNull(")", position),
                        text.indexOfOrNull("#if {{", position),
                        text.indexOfOrNull("#else if {{", position),
                        text.indexOfOrNull("#else", position),
                        text.indexOfOrNull("#endif", position)
                    ).minOrNull() ?: text.length
                    tokens.add(QuickCodeToken.RawText(text.substring(position, nextSpecialChar)))
                    position = nextSpecialChar
                }
            }
        }
        return tokens
    }

    private fun String.indexOfOrNull(string: String, startIndex: Int): Int? =
        indexOf(string, startIndex).takeIf { it != -1 }
}
