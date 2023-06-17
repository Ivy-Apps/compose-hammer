package com.ivyapps.composehammer.domain.quickcode.compiler.data

sealed interface QuickCodeToken {
    val text: String

    data class RawText(override val text: String) : QuickCodeToken
    data class Variable(override val text: String) : QuickCodeToken
    data class Operator(override val text: String) : QuickCodeToken
    data class OpenParenthesis(override val text: String = "(") : QuickCodeToken
    data class CloseParenthesis(override val text: String = ")") : QuickCodeToken
    data class IfCondition(val condition: String) : QuickCodeToken {
        override val text: String = "#if {{$condition}}"
    }

    object Else : QuickCodeToken {
        override val text: String = "#else"
    }

    data class ElseIfCondition(val condition: String) : QuickCodeToken {
        override val text: String = "#else if {{$condition}}"
    }

    object EndIf : QuickCodeToken {
        override val text: String = "#endif"
    }
}
