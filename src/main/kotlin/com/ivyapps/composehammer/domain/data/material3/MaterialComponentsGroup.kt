package com.ivyapps.composehammer.domain.data.material3

data class MaterialComponentsGroup(
    val title: String,
    val shortTitle: String?,
    val components: List<MaterialComponent>,
    val showInToolWindow: Boolean,
)