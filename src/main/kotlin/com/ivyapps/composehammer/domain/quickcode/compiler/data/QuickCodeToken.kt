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
        }

        object And : IfExpression {
            val syntax = TokenSyntax(
                starsWith = "AND",
            )
        }

        object Or : IfExpression {
            val syntax = TokenSyntax(
                starsWith = "OR",
            )
        }

        object OpenBracket : IfExpression {
            val syntax = TokenSyntax(
                starsWith = "(",
            )
        }

        object CloseBracket : IfExpression {
            val syntax = TokenSyntax(
                starsWith = ")",
            )
        }
    }


    object Then : QuickCodeToken {
        val syntax = TokenSyntax(
            starsWith = "#then",
        )
    }

    object Else : QuickCodeToken {
        val syntax = TokenSyntax(
            starsWith = "#else",
        )
    }

    object EndIf : QuickCodeToken {
        val syntax = TokenSyntax(
            starsWith = "#endif",
        )
    }
}

data class TokenSyntax(
    val starsWith: String,
    val endsWith: String? = null,
)
