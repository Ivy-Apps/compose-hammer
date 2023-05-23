package com.github.ivyapps.composematerial3helper.data

data class MaterialComponentsGroup(
    val title: String,
    val components: List<MaterialComponent>
)

data class MaterialComponent(
    val name: String,
    val description: String?,
    val screenshot: String,
    val enlargedScreenshot: String,
    val codeSample: String,
)