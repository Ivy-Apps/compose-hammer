package com.github.ivyapps.composematerial3helper.services

import com.github.ivyapps.composematerial3helper.data.MaterialComponent
import com.github.ivyapps.composematerial3helper.data.MaterialComponentsGroup
import com.intellij.openapi.components.Service
import com.intellij.openapi.project.Project

@Service(Service.Level.PROJECT)
class MaterialComponentsService(project: Project) {

    val content by lazy {
        buildList {
            buttons()

            group("Navigation") {

            }
        }
    }
}

@MaterialComponentDsl
private fun MutableList<MaterialComponentsGroup>.buttons() = group("Buttons") {
    component {
        name = "Elevated Button"
        specUrl = "https://m3.material.io/components/buttons/specs#2a19e853-d5dc-46a2-8ef4-1d954c9dcefa"
        guidelinesUrl = "https://m3.material.io/components/buttons/guidelines#4e89da4d-a8fa-4e20-bb8d-b8a93eff3e3e"
        description = """
            Elevated buttons are essentially filled tonal buttons with a shadow. 
            To prevent shadow creep, only use them when absolutely necessary, 
            such as when the button requires visual separation from a patterned background.
        """.trimIndent()
        screenshot = "btn_elevated"
        import = "import androidx.compose.material3.ElevatedButton"
        code = """
        ElevatedButton(
            onClick = {
                /*TODO: Handle click*/
            }
        ) {
            Text(text = "Text")
        }
        """.trimIndent()
        customCode = """
        ElevatedButton(
            onClick = {
                /*TODO: Handle click*/
            },
            enabled = true,
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.elevatedButtonColors(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        ) {
            Text(text = "Text")
        }
        """.trimIndent()
    }
}


// region DSL
@DslMarker
annotation class MaterialComponentDsl

@MaterialComponentDsl
fun MutableList<MaterialComponentsGroup>.group(
    title: String,
    components: MutableList<MaterialComponent>.() -> Unit
) {
    add(
        MaterialComponentsGroup(
            title = title,
            components = buildList { components() }
        )
    )
}

class ComponentScope {
    var name: String? = null
    var description: String? = null
    var specUrl: String? = null
    var guidelinesUrl: String? = null
    var screenshot: String? = null
    var code: String? = null
    var codeTip: String? = null
    var customCode: String? = null
    var customCodeTip: String? = null
    var import: String? = null
    var imports: List<String> = emptyList()

    fun build(): MaterialComponent {
        return MaterialComponent(
            name = name.required(),
            description = description.required(),
            specUrl = specUrl.required(),
            guidelinesUrl = guidelinesUrl.required(),
            menuScreenshot = screenshot.required(),
            detailsScreenshot = screenshot.required(),
            imports = import?.let(::listOf) ?: imports.takeIf { it.isNotEmpty() }.required(),
            defaultImplementation = code.required(),
            defaultImplementationTip = codeTip,
            customImplementation = customCode,
            customImplementationTip = customCodeTip,
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
    add(ComponentScope().apply(init).build())
}
// endregion