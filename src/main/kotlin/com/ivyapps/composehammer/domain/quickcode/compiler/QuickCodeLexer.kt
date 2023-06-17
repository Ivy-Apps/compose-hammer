package com.ivyapps.composehammer.domain.quickcode.compiler

import com.ivyapps.composehammer.domain.quickcode.compiler.data.QuickCodeToken
import com.ivyapps.composehammer.domain.quickcode.compiler.data.TokenSyntax

class QuickCodeLexer {
    fun tokenize(text: String): List<QuickCodeToken> {
        val scope = LexerScope(
            text = text,
            isInsideIfCondition = false,
            position = 0,
            prevPosition = 0,
            tokens = mutableListOf(),
        )
        val rules = scope.parserRules()
        while (scope.position < scope.text.length) {
            scope.parse(rules)
        }
        return scope.tokens.filter {
            it !is QuickCodeToken.RawText || it.text.isNotBlank()
        }.concatRawTexts()
    }

    private fun List<QuickCodeToken>.concatRawTexts(): List<QuickCodeToken> {
        val original = this
        val res = mutableListOf<QuickCodeToken>()
        var i = 0
        while (i < original.size) {
            val current = original[i]
            if (current is QuickCodeToken.RawText) {
                val combinedRawText = buildString {
                    append(current.text)
                    var next: QuickCodeToken?
                    do {
                        next = original.getOrNull(i + 1)
                        if (next is QuickCodeToken.RawText) {
                            append(next.text)
                            i++
                        } else {
                            break
                        }
                    } while (true)
                }
                res.add(QuickCodeToken.RawText(combinedRawText))
            } else {
                res.add(current)
            }
            i++
        }

        return res
    }

    private fun LexerScope.parserRules(): List<() -> Boolean> {
        return listOf(
            { variable() },
            { ifCond() },
            { ifOpenBracket() },
            { ifCloseBracket() },
            { ifNot() },
            { ifBoolVar() },
            { ifAndCond() },
            { ifOrCond() },
            { thenCond() },
            { elseCond() },
            { endIfCond() },
        )
    }

    private fun LexerScope.parse(
        rules: List<() -> Boolean>
    ) {
        for (rule in rules) {
            if (rule()) {
                // parsed something special
                return
            }
        }

        // default case: raw text
        val nextSpecialChar = listOf(
            QuickCodeToken.Variable.syntax,
            QuickCodeToken.If.syntax,
            QuickCodeToken.IfCondition.OpenBracket.syntax,
            QuickCodeToken.IfCondition.CloseBracket.syntax,
            QuickCodeToken.IfCondition.And.syntax,
            QuickCodeToken.IfCondition.Or.syntax,
            QuickCodeToken.IfCondition.Not.syntax,
            QuickCodeToken.IfCondition.BoolVariable.syntax,
            QuickCodeToken.Then.syntax,
            QuickCodeToken.Else.syntax,
            QuickCodeToken.EndIf.syntax,
        ).mapNotNull { syntax ->
            text.indexOfOrNull(syntax.starsWith, position)
        }.minOrNull() ?: text.length
        if (!isInsideIfCondition) {
            tokens.add(QuickCodeToken.RawText(text.substring(position, nextSpecialChar)))
        }
        prevPosition = position
        position = nextSpecialChar
        if (position == prevPosition) {
            /*
            Special case wasn't parsed successfully (probably not satisfied condition).
            Consider this char as a RawText
             */
            tokens.add(QuickCodeToken.RawText("${text[position]}"))
            position++
        }
    }

    private fun LexerScope.variable(): Boolean = parseToken(
        condition = !isInsideIfCondition,
        syntax = QuickCodeToken.Variable.syntax,
    ) {
        QuickCodeToken.Variable(name = it)
    }

    private fun LexerScope.ifCond() = parseToken(
        condition = !isInsideIfCondition,
        syntax = QuickCodeToken.If.syntax,
    ) {
        isInsideIfCondition = true
        QuickCodeToken.If
    }

    private fun LexerScope.ifOpenBracket() = parseToken(
        condition = isInsideIfCondition,
        syntax = QuickCodeToken.IfCondition.OpenBracket.syntax,
    ) {
        QuickCodeToken.IfCondition.OpenBracket
    }

    private fun LexerScope.ifCloseBracket() = parseToken(
        condition = isInsideIfCondition,
        syntax = QuickCodeToken.IfCondition.CloseBracket.syntax,
    ) {
        QuickCodeToken.IfCondition.CloseBracket
    }

    private fun LexerScope.ifNot() = parseToken(
        condition = isInsideIfCondition,
        syntax = QuickCodeToken.IfCondition.Not.syntax,
    ) {
        QuickCodeToken.IfCondition.Not
    }

    private fun LexerScope.ifAndCond() = parseToken(
        condition = isInsideIfCondition,
        syntax = QuickCodeToken.IfCondition.And.syntax,
    ) {
        QuickCodeToken.IfCondition.And
    }

    private fun LexerScope.ifOrCond() = parseToken(
        condition = isInsideIfCondition,
        syntax = QuickCodeToken.IfCondition.Or.syntax,
    ) {
        QuickCodeToken.IfCondition.Or
    }

    private fun LexerScope.ifBoolVar() = parseToken(
        condition = isInsideIfCondition,
        syntax = QuickCodeToken.IfCondition.BoolVariable.syntax,
    ) {
        QuickCodeToken.IfCondition.BoolVariable(it)
    }

    private fun LexerScope.thenCond() = parseToken(
        condition = isInsideIfCondition,
        syntax = QuickCodeToken.Then.syntax,
    ) {
        isInsideIfCondition = false
        QuickCodeToken.Then
    }

    private fun LexerScope.elseCond() = parseToken(
        condition = !isInsideIfCondition,
        syntax = QuickCodeToken.Else.syntax
    ) {
        QuickCodeToken.Else
    }

    private fun LexerScope.endIfCond() = parseToken(
        condition = !isInsideIfCondition,
        syntax = QuickCodeToken.EndIf.syntax,
    ) {
        QuickCodeToken.EndIf
    }

    private fun LexerScope.parseToken(
        condition: Boolean,
        syntax: TokenSyntax,
        onParseToken: LexerScope.(text: String) -> QuickCodeToken?,
    ): Boolean {
        if (condition && text.startsWith(prefix = syntax.starsWith, startIndex = position)) {
            if (syntax.endsWith == null) {
                onParseToken("")?.let(tokens::add)
                position += syntax.starsWith.length
                return true
            } else {
                val endIndex = text.indexOfOrNull(
                    string = syntax.endsWith,
                    startIndex = position + syntax.starsWith.length
                )
                if (endIndex != null) {
                    onParseToken(
                        text.substring(
                            startIndex = position + syntax.starsWith.length,
                            endIndex = endIndex,
                        )
                    )?.let(tokens::add)
                    position = endIndex + syntax.endsWith.length
                    return true
                }
            }
        }
        return false
    }

    private fun String.indexOfOrNull(string: String, startIndex: Int): Int? =
        indexOf(string, startIndex).takeIf { it != -1 }

    private data class LexerScope(
        val text: String,
        var isInsideIfCondition: Boolean,
        var prevPosition: Int,
        var position: Int,
        val tokens: MutableList<QuickCodeToken>
    )
}
