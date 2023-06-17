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

    sealed interface IfCondition {
        data class BoolVariable(val name: String) : QuickCodeToken {
            companion object {
                val syntax = TokenSyntax(
                    starsWith = "{{",
                    endsWith = "}}"
                )
            }
        }

        object Not : QuickCodeToken {
            val syntax = TokenSyntax(
                starsWith = "NOT",
            )
        }

        object And : QuickCodeToken {
            val syntax = TokenSyntax(
                starsWith = "AND",
            )
        }

        object Or : QuickCodeToken {
            val syntax = TokenSyntax(
                starsWith = "OR",
            )
        }

        object OpenBracket : QuickCodeToken {
            val syntax = TokenSyntax(
                starsWith = "(",
            )
        }

        object CloseBracket : QuickCodeToken {
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
