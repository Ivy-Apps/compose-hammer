package com.ivyapps.composehammer.domain.data.material3

import com.ivyapps.composehammer.domain.data.Code

data class MaterialComponent(
    val name: String,
    val shortName: String?,
    val description: String?,
    val guidelinesUrl: String,
    val specUrl: String,
    val docsUrl: String,
    val menuScreenshot: String,
    val detailsScreenshot: String,
    override val imports: List<String>,
    val defaultImplementation: String,
    val defaultImplementationTip: String?,
    val customImplementation: String?,
    val customImplementationTip: String?,
    val showInToolWindow: Boolean,
) : Code {
    override val menuName: String
        get() = shortName ?: name

    override val code: String
        get() = defaultImplementation
}