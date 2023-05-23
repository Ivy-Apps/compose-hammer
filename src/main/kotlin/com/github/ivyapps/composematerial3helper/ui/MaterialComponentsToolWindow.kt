package com.github.ivyapps.composematerial3helper.ui

import com.github.ivyapps.composematerial3helper.data.MaterialComponent
import com.github.ivyapps.composematerial3helper.services.MaterialComponentsService
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.profile.codeInspection.ui.addScrollPaneIfNecessary
import com.intellij.ui.components.panels.VerticalLayout
import com.intellij.ui.content.ContentFactory
import java.awt.BorderLayout
import java.awt.GridLayout
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.net.URL
import javax.swing.*

class MaterialComponentsWindowFactory : ToolWindowFactory {
    private val contentFactory = ContentFactory.SERVICE.getInstance()

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val materialComponentsWindow = MaterialComponentsWindow(toolWindow)
        val content = contentFactory.createContent(
            materialComponentsWindow.content,
            "Compose Material3 Helper",
            false
        )
        toolWindow.contentManager.addContent(content)
    }

    override fun shouldBeAvailable(project: Project) = true
}

class MaterialComponentsWindow(toolWindow: ToolWindow) {
    private val service = toolWindow.project.service<MaterialComponentsService>()

    val content = addScrollPaneIfNecessary(menuUi())

    private fun menuUi(): JPanel {
        return JPanel(GridLayout(0, 2, 10, 10)).apply {
            for (component in service.materialComponents) {
                add(
                    JPanel(VerticalLayout(0, SwingConstants.CENTER)).apply {
                        add(JLabel(ImageIcon(component.screenshot.toImagePath())))
                        add(JLabel(component.name))

                        addMouseListener(object : MouseAdapter() {
                            override fun mouseClicked(e: MouseEvent?) {
                                content.removeAll()
                                content.add(detailsUi(component))
                                content.updateUI()
                            }
                        })
                    }
                )
            }
        }
    }

    fun detailsUi(component: MaterialComponent): JPanel {
        return JPanel(BorderLayout()).apply {
            add(JLabel(ImageIcon(component.enlargedScreenshot.toImagePath())), BorderLayout.CENTER)
            add(JTextArea(component.codeSample).apply { isEditable = false }, BorderLayout.PAGE_END)
        }
    }
}

fun String.toImagePath(): URL {
    val fullPath = "/images/$this.png"
    val resource = MaterialComponentsWindowFactory::class.java.getResource(fullPath)
    requireNotNull(resource) {
        "Couldn't find an image for '$fullPath'"
    }
    return resource
}