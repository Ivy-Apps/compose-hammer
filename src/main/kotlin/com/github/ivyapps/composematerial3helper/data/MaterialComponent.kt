package com.github.ivyapps.composematerial3helper.data

data class MaterialComponentsGroup(
    val title: String,
    val components: List<MaterialComponent>
)

data class MaterialComponent(
    val name: String,
    val description: String?,
    val guidelinesUrl: String,
    val specUrl: String,
    val menuScreenshot: String,
    val detailsScreenshot: String,
    val imports: List<String> = emptyList(),
    val defaultImplementation: String,
    val defaultImplementationTip: String? = null,
    val customImplementation: String? = null,
    val customImplementationTip: String? = null,
)