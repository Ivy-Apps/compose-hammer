package com.ivyapps.composehammer.domain.quickcode.compiler.data

sealed interface VariableValue {
    data class Str(val value: String) : VariableValue
    data class Bool(val value: Boolean) : VariableValue
}