package com.ivyapps.composehammer.domain.quickcode.compiler

import com.ivyapps.composehammer.domain.quickcode.compiler.data.QuickCodeToken

class QuickCodeLexer {
    fun tokenize(text: String): List<QuickCodeToken> {
        val scope = LexerScope(
            text = text,
            isInsideIfCondition = false,
            position = 0,
            tokens = mutableListOf(),
        )
        val rules = scope.parserRules()
        while (scope.position < scope.text.length) {
            scope.parse(rules)
        }
        return scope.tokens.filter {
            it !is QuickCodeToken.RawText || it.text.isNotBlank()
        }
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
        val nextSpecialChar = listOfNotNull(
            text.indexOfOrNull("{{", position),
            text.indexOfOrNull("#if", position),
            text.indexOfOrNull("#else", position),
            text.indexOfOrNull("#endif", position),
        ).minOrNull() ?: text.length
        if (!isInsideIfCondition) {
            tokens.add(QuickCodeToken.RawText(text.substring(position, nextSpecialChar)))
        }
        position = nextSpecialChar
    }

    private fun LexerScope.variable(): Boolean = parseToken(
        condition = !isInsideIfCondition,
        startSyntax = "{{",
        endSyntax = "}}"
    ) {
        QuickCodeToken.Variable(name = it)
    }

    private fun LexerScope.ifCond() = parseToken(
        condition = !isInsideIfCondition,
        startSyntax = "#if",
    ) {
        isInsideIfCondition = true
        QuickCodeToken.If
    }

    private fun LexerScope.ifOpenBracket() = parseToken(
        condition = isInsideIfCondition,
        startSyntax = "("
    ) {
        QuickCodeToken.IfCondition.OpenBracket
    }

    private fun LexerScope.ifCloseBracket() = parseToken(
        condition = isInsideIfCondition,
        startSyntax = ")"
    ) {
        QuickCodeToken.IfCondition.CloseBracket
    }

    private fun LexerScope.ifNot() = parseToken(
        condition = isInsideIfCondition,
        startSyntax = "!"
    ) {
        QuickCodeToken.IfCondition.Not
    }

    private fun LexerScope.ifAndCond() = parseToken(
        condition = isInsideIfCondition,
        startSyntax = "&&"
    ) {
        QuickCodeToken.IfCondition.And
    }

    private fun LexerScope.ifOrCond() = parseToken(
        condition = isInsideIfCondition,
        startSyntax = "||"
    ) {
        QuickCodeToken.IfCondition.Or
    }

    private fun LexerScope.ifBoolVar() = parseToken(
        condition = isInsideIfCondition,
        startSyntax = "{{",
        endSyntax = "}}",
    ) {
        QuickCodeToken.IfCondition.BoolVariable(it)
    }

    private fun LexerScope.thenCond() = parseToken(
        condition = isInsideIfCondition,
        startSyntax = "#then",
    ) {
        isInsideIfCondition = false
        QuickCodeToken.Then
    }

    private fun LexerScope.elseCond() = parseToken(
        condition = !isInsideIfCondition,
        startSyntax = "#else"
    ) {
        QuickCodeToken.Else
    }

    private fun LexerScope.endIfCond() = parseToken(
        condition = !isInsideIfCondition,
        startSyntax = "#endif"
    ) {
        QuickCodeToken.EndIf
    }

    private fun LexerScope.parseToken(
        condition: Boolean,
        startSyntax: String,
        endSyntax: String? = null,
        onParseToken: LexerScope.(text: String) -> QuickCodeToken?,
    ): Boolean {
        if (condition && text.startsWith(prefix = startSyntax, startIndex = position)) {
            if (endSyntax == null) {
                onParseToken("")?.let(tokens::add)
                position += startSyntax.length
                return true
            } else {
                val endIndex = text.indexOfOrNull(endSyntax, position + startSyntax.length)
                if (endIndex != null) {
                    onParseToken(
                        text.substring(
                            startIndex = position + startSyntax.length,
                            endIndex = endIndex,
                        )
                    )?.let(tokens::add)
                    position = endIndex
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
        var position: Int,
        val tokens: MutableList<QuickCodeToken>
    )
}
