package com.github.ivyapps.composematerial3helper.services

import com.github.ivyapps.composematerial3helper.data.MaterialComponent
import com.github.ivyapps.composematerial3helper.data.MaterialComponentsGroup
import com.intellij.openapi.components.Service
import com.intellij.openapi.project.Project

@Service(Service.Level.PROJECT)
class MaterialComponentsService(project: Project) {

    val content by lazy {
        buildList {
            group("Buttons") {
                component(
                    name = "Filled Button",
                    url = "https://google.com",
                    description = "Button used for primary CTAs.",
                    screenshot = "screenshot1",
                    code = """
                        fun button() {
                            val x = 3
                        }
                    """.trimIndent()
                )

                component(
                    name = "Outlined Button",
                    url = "https://google.com",
                    screenshot = "screenshot1",
                    code = """
                        fun button() {
                            val x = 3
                        }
                    """.trimIndent()
                )
            }

            group("Navigation") {
                component(
                    name = "Filled Button",
                    url = "https://google.com",
                    screenshot = "screenshot1",
                    code = """
                        fun button() {
                            val x = 3
                        }
                    """.trimIndent()
                )

                component(
                    name = "Outlined Button",
                    url = "https://google.com",
                    screenshot = "screenshot1",
                    code = """
                        fun button() {
                            val x = 3
                        }
                    """.trimIndent()
                )
            }
        }
    }

    private fun MutableList<MaterialComponentsGroup>.group(
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

    private fun MutableList<MaterialComponent>.component(
        name: String,
        url: String,
        description: String? = null,
        screenshot: String,
        code: String,
    ) {
        add(
            MaterialComponent(
                name = name,
                description = description,
                url = url,
                screenshot = screenshot,
                enlargedScreenshot = "${screenshot}_large",
                codeSample = code
            )
        )
    }
}
