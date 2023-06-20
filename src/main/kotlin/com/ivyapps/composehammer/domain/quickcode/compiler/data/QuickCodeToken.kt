package com.ivyapps.composehammer.domain.quickcode.compiler.data

sealed interface QuickCodeToken {
    data class RawText(val text: String) : QuickCodeToken

    data class Variable(val name: String) : QuickCodeToken {
        companion object {
            val syntax = TokenSyntax(
                starsWith = "{{",
                endsWith = "}}",
            )
        }
    }

    object If : QuickCodeToken {
        val syntax = TokenSyntax(
            starsWith = "#if",
        )

        override fun toString() = syntax.starsWith
    }

    sealed interface IfExpression : QuickCodeToken {
        data class BoolVariable(val name: String) : IfExpression {
            companion object {
                val syntax = TokenSyntax(
                    starsWith = "{{",
                    endsWith = "}}"
                )
            }
        }

        object Not : IfExpression {
            val syntax = TokenSyntax(
                starsWith = "NOT",
            )

            override fun toString() = syntax.starsWith
        }

        object And : IfExpression {
            val syntax = TokenSyntax(
                starsWith = "AND",
            )

            override fun toString() = syntax.starsWith
        }

        object Or : IfExpression {
            val syntax = TokenSyntax(
                starsWith = "OR",
            )

            override fun toString() = syntax.starsWith
        }

        object OpenBracket : IfExpression {
            val syntax = TokenSyntax(
                starsWith = "(",
            )

            override fun toString() = syntax.starsWith
        }

        object CloseBracket : IfExpression {
            val syntax = TokenSyntax(
                starsWith = ")",
            )

            override fun toString() = syntax.starsWith
        }
    }


    object Then : QuickCodeToken {
        val syntax = TokenSyntax(
            starsWith = "#then",
        )

        override fun toString() = syntax.starsWith
    }

    object ElseIf : QuickCodeToken {
        val syntax = TokenSyntax(
            starsWith = "#elseif",
        )

        override fun toString() = syntax.starsWith
    }

    object Else : QuickCodeToken {
        val syntax = TokenSyntax(
            starsWith = "#else",
        )

        override fun toString() = syntax.starsWith
    }

    object EndIf : QuickCodeToken {
        val syntax = TokenSyntax(
            starsWith = "#endif",
        )

        override fun toString() = syntax.starsWith
    }
}

data class TokenSyntax(
    val starsWith: String,
    val endsWith: String? = null,
)
