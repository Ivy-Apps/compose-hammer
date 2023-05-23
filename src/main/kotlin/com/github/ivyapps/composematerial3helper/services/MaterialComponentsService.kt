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
                    name = "Elevated Button",
                    url = "https://m3.material.io/components/buttons/guidelines#4e89da4d-a8fa-4e20-bb8d-b8a93eff3e3e",
                    description = """
                        Elevated buttons are essentially filled tonal buttons with a shadow. 
                        To prevent shadow creep, only use them when absolutely necessary, 
                        such as when the button requires visual separation from a patterned background.
                    """.trimIndent(),
                    screenshot = "btn_elevated",
                    code = """
                        //import androidx.compose.material3.ElevatedButton
                        //import androidx.compose.material3.Text

                        ElevatedButton(
                            onClick = { /* Do something! */ }
                        ) { 
                            Text("Elevated Button") 
                        }
                    """.trimIndent()
                )

                component(
                    name = "Filled Button",
                    url = "https://m3.material.io/components/buttons/guidelines#9ecffdb3-ef29-47e7-8d5d-f78b404fcafe",
                    description = """
                        Filled buttons have the most visual impact after the FAB, 
                        and should be used for important, final actions that complete a flow, 
                        like Save, Join now, or Confirm.
                    """.trimIndent(),
                    screenshot = "btn_filled",
                    code = """
                        //import androidx.compose.material3.Button
                        //import androidx.compose.material3.Text

                        Button(
                            onClick = { /* Do something! */ }
                        ) { 
                            Text("Button") 
                        }
                    """.trimIndent()
                )

                component(
                    name = "Filled Tonal Button",
                    url = "https://m3.material.io/components/buttons/guidelines#07a1577b-aaf5-4824-a698-03526421058b",
                    description = """
                        A filled tonal button is an alternative middle ground between filled and outlined
                         buttons. They’re useful in contexts where a lower-priority button 
                         requires slightly more emphasis than an outline would give, 
                         such as "Next" in an onboarding flow. Tonal buttons use the secondary color mapping.
                    """.trimIndent(),
                    screenshot = "btn_tonal",
                    code = """
                        //import androidx.compose.material3.FilledTonalButton
                        //import androidx.compose.material3.Text

                        FilledTonalButton(
                            onClick = { /* Do something! */ }
                        ) { 
                            Text("Filled Tonal Button") 
                        }
                    """.trimIndent()
                )

                component(
                    name = "Outlined Button",
                    url = "https://m3.material.io/components/buttons/guidelines#3742b09f-c224-43e0-a83e-541bd29d0f05",
                    description = """
                        Outlined buttons are medium-emphasis buttons. They contain actions that are important, but aren’t the primary action in an app.

                        Outlined buttons pair well with filled buttons to indicate an alternative, secondary action.
                    """.trimIndent(),
                    screenshot = "btn_outlined",
                    code = """
                        //import androidx.compose.material3.OutlinedButton
                        //import androidx.compose.material3.Text

                        OutlinedButton(
                            onClick = { /* Do something! */ }
                        ) { 
                            Text("Outlined Button") 
                        }
                    """.trimIndent()
                )

                component(
                    name = "Text Button",
                    url = "https://m3.material.io/components/buttons/guidelines#c9bcbc0b-ee05-45ad-8e80-e814ae919fbb",
                    description = """
                        Text buttons are used for the lowest priority actions, especially when presenting multiple options.

                        Text buttons can be placed on a variety of backgrounds. Until the button is interacted with, its container isn’t visible.
                    """.trimIndent(),
                    screenshot = "btn_text",
                    code = """
                        //import androidx.compose.material3.Text
                        //import androidx.compose.material3.TextButton

                        TextButton(
                            onClick = { /* Do something! */ }
                        ) { 
                            Text("Text Button")
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
                enlargedScreenshot = screenshot, //"${screenshot}_large",
                codeSample = code
            )
        )
    }
}
