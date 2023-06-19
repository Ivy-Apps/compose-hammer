package com.ivyapps.composehammer.domain.data.quickcode

import kotlinx.serialization.Serializable

@Serializable
sealed interface QCVariable {
    val name: String

    @Serializable
    data class Str(override val name: String) : QCVariable

    @Serializable
    data class Bool(override val name: String) : QCVariable
}