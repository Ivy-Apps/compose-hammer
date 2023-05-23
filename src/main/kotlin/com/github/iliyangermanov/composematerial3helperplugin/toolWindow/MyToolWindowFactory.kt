package com.github.iliyangermanov.composematerial3helperplugin.toolWindow

import com.github.iliyangermanov.composematerial3helperplugin.data.MaterialComponent
import com.github.iliyangermanov.composematerial3helperplugin.services.MyProjectService
import com.intellij.openapi.components.service
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.components.JBScrollPane
import com.intellij.ui.components.panels.VerticalLayout
import com.intellij.ui.content.ContentFactory
import java.awt.BorderLayout
import java.awt.GridLayout
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.net.URL
import javax.swing.ImageIcon
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextArea
import javax.swing.SwingConstants


fun materialComponents() = List(size = 20) {
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

class MyToolWindowFactory : ToolWindowFactory {

    init {
        thisLogger().warn("Don't forget to remove all non-needed sample code files with their corresponding registration entries in `plugin.xml`.")
    }

    private val contentFactory = ContentFactory.SERVICE.getInstance()

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val myToolWindow = MyToolWindow(toolWindow)
        val content = contentFactory.createContent(myToolWindow.getContent(), "Iliyan's Plugin", false)
        toolWindow.contentManager.addContent(content)
    }

    override fun shouldBeAvailable(project: Project) = true

    class MyToolWindow(toolWindow: ToolWindow) {
        private val service = toolWindow.project.service<MyProjectService>()

        fun getContent() = mainUi(materialComponents())

        private fun mainUi(
            materialComponents: List<MaterialComponent>
        ): JBScrollPane {
            val mainPanel = JPanel(GridLayout(0, 2, 10, 10)).apply {
                for (component in materialComponents) {
                    add(
                        JPanel(VerticalLayout(0, SwingConstants.CENTER)).apply {
                            add(JLabel(ImageIcon(component.screenshot.toImagePath())))
                            add(JLabel(component.name))

                            addMouseListener(object : MouseAdapter() {
                                override fun mouseClicked(e: MouseEvent?) {
                                    showComponentDetails(component)
                                }
                            })
                        }
                    )
                }
            }

            return JBScrollPane(mainPanel)
        }

        fun showComponentDetails(component: MaterialComponent) {
            val detailsPanel = JPanel(BorderLayout()).apply {
                add(JLabel(ImageIcon(component.enlargedScreenshot.toImagePath())), BorderLayout.CENTER)
                add(JTextArea(component.codeSample).apply { isEditable = false }, BorderLayout.PAGE_END)

                // Code to show detailsPanel goes here
            }
        }

        private fun String.toImagePath(): URL {
            val fullPath = "/images/$this.png"
            val resource = MyToolWindowFactory::class.java.getResource(fullPath)
            requireNotNull(resource) {
                "Couldn't find an image for '$fullPath'"
            }
            return resource
        }

    }
}