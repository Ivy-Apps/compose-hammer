package com.ivyapps.composehammer.domain.quickcode.compiler.data

sealed interface QuickCodeToken {
    val startSyntax: String?
    val endSyntax: String?

    data class RawText(val text: String) : QuickCodeToken {
        override val startSyntax = null
        override val endSyntax = null
    }

    data class Variable(val name: String) : QuickCodeToken {
        override val startSyntax = "{{"
        override val endSyntax = "}}"
    }

    object If : QuickCodeToken {
        override val startSyntax = "#if"
        override val endSyntax = "}}"
    }

    object Then : QuickCodeToken

    sealed interface IfCondition {
        data class BoolVariable(val name: String) : QuickCodeToken

        object Not : QuickCodeToken
        object And : QuickCodeToken
        object Or : QuickCodeToken

        object OpenBracket : QuickCodeToken
        object CloseBracket : QuickCodeToken
    }

    object Else : QuickCodeToken
    object EndIf : QuickCodeToken
}

