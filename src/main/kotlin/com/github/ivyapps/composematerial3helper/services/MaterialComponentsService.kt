package com.github.ivyapps.composematerial3helper.services

import com.github.ivyapps.composematerial3helper.data.MaterialComponent
import com.github.ivyapps.composematerial3helper.data.MaterialComponentsGroup
import com.intellij.openapi.components.Service
import com.intellij.openapi.project.Project

@Service(Service.Level.PROJECT)
class MaterialComponentsService(project: Project) {
    val content by lazy {
        buildList {
            common()
            buttons()
            fab()
            iconButtons()

            group("Navigation") {

            }
        }
    }

    fun findGroupByTitle(groupTitle: String): MaterialComponentsGroup {
        return requireNotNull(content.find { it.title == groupTitle }) {
            "Couldn't find '$groupTitle' group in $content!!!"
        }
    }

    fun findComponentByNameInGroup(
        group: MaterialComponentsGroup,
        componentName: String
    ): MaterialComponent {
        return requireNotNull(group.components.find { it.name == componentName }) {
            "Couldn't find '$componentName' component in ${group.components}!!!"
        }
    }
}

// region DSL
@DslMarker
annotation class MaterialComponentDsl

@MaterialComponentDsl
fun MutableList<MaterialComponentsGroup>.group(
    title: String,
    showInToolWindow: Boolean = true,
    components: MutableList<MaterialComponent>.() -> Unit
) {
    add(
        MaterialComponentsGroup(
            title = title,
            components = buildList { components() },
            showInToolWindow = showInToolWindow,
        )
    )
}

class ComponentScope {
    var name: String? = null
    var description: String? = null
    var specUrl: String? = null
    var guidelinesUrl: String? = null
    var docsUrl: String? = null
    var screenshot: String? = null
    var code: String? = null
    var codeTip: String? = null
    var customCode: String? = null
    var customCodeTip: String? = null
    var import: String? = null
    var imports: List<String> = emptyList()
    var showInToolWindow = true

    fun build(): MaterialComponent {
        if (!showInToolWindow) {
            return MaterialComponent(
                name = name.required(),
                description = description,
                specUrl = specUrl ?: "",
                guidelinesUrl = guidelinesUrl ?: "",
                docsUrl = docsUrl ?: "",
                menuScreenshot = screenshot ?: "",
                detailsScreenshot = screenshot ?: "",
                imports = import?.let(::listOf) ?: imports.takeIf { it.isNotEmpty() } ?: emptyList(),
                defaultImplementation = code.required(),
                defaultImplementationTip = codeTip,
                customImplementation = customCode,
                customImplementationTip = customCodeTip,
                showInToolWindow = showInToolWindow,
            )
        }

        return MaterialComponent(
            name = name.required(),
            description = description.required(),
            specUrl = specUrl.required(),
            guidelinesUrl = guidelinesUrl.required(),
            docsUrl = docsUrl.required(),
            menuScreenshot = screenshot.required(),
            detailsScreenshot = screenshot.required(),
            imports = import?.let(::listOf) ?: imports.takeIf { it.isNotEmpty() }.required(),
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
// endregion