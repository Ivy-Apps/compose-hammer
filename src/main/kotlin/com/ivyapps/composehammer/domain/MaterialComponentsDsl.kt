package com.ivyapps.composehammer.domain

import com.ivyapps.composehammer.domain.data.MaterialComponent
import com.ivyapps.composehammer.domain.data.MaterialComponentsGroup


@DslMarker
annotation class MaterialComponentDsl

typealias ContentScope = MutableList<MaterialComponentsGroup>

@MaterialComponentDsl
fun ContentScope.group(
    title: String,
    shortTitle: String? = null,
    showInToolWindow: Boolean = true,
    components: MutableList<MaterialComponent>.() -> Unit
) {
    add(
        MaterialComponentsGroup(
            title = title,
            shortTitle = shortTitle,
            components = buildList { components() },
            showInToolWindow = showInToolWindow,
        )
    )
}

class ComponentScope {
    var name: String? = null
    var shortName: String? = null
    var description: String? = null
    var specUrl: String? = null
    var guidelinesUrl: String? = null
    var docsUrl: String? = null
    var screenshot: String? = null
    var code: String? = null
    var codeTip: String? = null
    var customCode: String? = null
    var customCodeTip: String? = null
    var imports: List<String> = emptyList()
    var showInToolWindow = true

    fun build(): MaterialComponent {
        if (!showInToolWindow) {
            return MaterialComponent(
                name = name.required(),
                shortName = shortName,
                description = description,
                specUrl = specUrl ?: "",
                guidelinesUrl = guidelinesUrl ?: "",
                docsUrl = docsUrl ?: "",
                menuScreenshot = screenshot ?: "",
                detailsScreenshot = screenshot ?: "",
                imports = imports.takeIf { it.isNotEmpty() } ?: emptyList(),
                defaultImplementation = code.required(),
                defaultImplementationTip = codeTip,
                customImplementation = customCode,
                customImplementationTip = customCodeTip,
                showInToolWindow = showInToolWindow,
            )
        }

        return MaterialComponent(
            name = name.required(),
            shortName = shortName,
            description = description.required(),
            specUrl = specUrl.required(),
            guidelinesUrl = guidelinesUrl.required(),
            docsUrl = docsUrl.required(),
            menuScreenshot = screenshot.required(),
            detailsScreenshot = screenshot.required(),
            imports = imports.takeIf { it.isNotEmpty() }.required(),
            defaultImplementation = code.required(),
            defaultImplementationTip = codeTip,
            customImplementation = customCode,
            customImplementationTip = customCodeTip,
            showInToolWindow = showInToolWindow,
        )
    }

    private fun <T> T?.required(): T {
        return requireNotNull(this) { "Invalid component: ${this@ComponentScope}" }
    }
}

@MaterialComponentDsl
fun MutableList<MaterialComponent>.component(
    init: ComponentScope.() -> Unit
) {
    add(
        ComponentScope()
            .apply(init)
            .build()
    )
}