package com.ivyapps.composehammer.domain.data.quickcode

import kotlinx.serialization.Serializable

@Serializable
data class CodeGroup(
    val name: String,
    val order: Double,
    val codeItems: List<CodeItem>,
)