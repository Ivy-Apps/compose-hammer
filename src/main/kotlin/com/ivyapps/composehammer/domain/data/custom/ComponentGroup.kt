package com.ivyapps.composehammer.domain.data.custom

data class ComponentGroup(
    val name: String,
    val order: Double,
    val components: List<Component>,
)