package com.ivyapps.composematerial3helper.domain.data

data class MaterialComponentsGroup(
    val title: String,
    val components: List<MaterialComponent>,
    val showInToolWindow: Boolean,
)