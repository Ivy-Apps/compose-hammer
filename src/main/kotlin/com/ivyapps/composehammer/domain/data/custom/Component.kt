package com.ivyapps.composehammer.domain.data.custom

import kotlinx.serialization.Serializable

@Serializable
data class Component(
    val name: String,
    val imports: List<String>,
    val code: String,
    val order: Double,
)