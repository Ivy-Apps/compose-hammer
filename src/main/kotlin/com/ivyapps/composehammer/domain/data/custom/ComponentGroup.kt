package com.ivyapps.composehammer.domain.data.custom

import kotlinx.serialization.Serializable

@Serializable
data class ComponentGroup(
    val name: String,
    val order: Double,
    val components: List<Component>,
)