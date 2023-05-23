package com.github.ivyapps.composematerial3helper.services

import com.github.ivyapps.composematerial3helper.data.MaterialComponent
import com.intellij.openapi.components.Service
import com.intellij.openapi.project.Project

@Service(Service.Level.PROJECT)
class MaterialComponentsService(project: Project) {

    val materialComponents by lazy {
        List(size = 20) {
            MaterialComponent(
                name = "Component $it",
                screenshot = "screenshot1",
                enlargedScreenshot = "screenshot1",
                codeSample = """
            fun main() {
                println("Hello, world!")
            }
        """.trimIndent()
            )
        }
    }

    fun getRandomNumber() = (1..100).random()
}
