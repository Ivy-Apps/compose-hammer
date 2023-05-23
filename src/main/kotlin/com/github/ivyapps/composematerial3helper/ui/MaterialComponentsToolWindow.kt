package com.github.ivyapps.composematerial3helper.ui

import com.github.ivyapps.composematerial3helper.data.MaterialComponent
import com.github.ivyapps.composematerial3helper.services.MaterialComponentsService
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.profile.codeInspection.ui.addScrollPaneIfNecessary
import com.intellij.ui.components.JBTextArea
import com.intellij.ui.content.ContentFactory
import com.intellij.ui.dsl.builder.Panel
import com.intellij.ui.dsl.builder.panel
import com.intellij.ui.dsl.builder.text
import java.awt.Component
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import java.net.URL
import javax.swing.ImageIcon
import javax.swing.JLabel
import javax.swing.JPanel

class MaterialComponentsWindowFactory : ToolWindowFactory {
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val ui = MaterialComponentsUi(toolWindow)
        ui.navigateToMenu()
    }

    override fun shouldBeAvailable(project: Project) = true
}


class MaterialComponentsUi(private val toolWindow: ToolWindow) {
    private val contentFactory = ContentFactory.SERVICE.getInstance()
    private val service = toolWindow.project.service<MaterialComponentsService>()

    fun navigateToMenu() {
        toolWindow.contentManager.removeAllContents(true)
        toolWindow.contentManager.addContent(
            contentFactory.createContent(
                addScrollPaneIfNecessary(menuUi()),
                "M3 Components",
                false,
            )
        )
    }

    fun navigateToComponent(component: MaterialComponent) {
        toolWindow.contentManager.removeAllContents(false)
        toolWindow.contentManager.addContent(
            contentFactory.createContent(detailsUi(component), component.name, false)
        )
    }

    private fun menuUi() = panel {
        for (group in service.content) {
            collapsibleGroup(
                title = group.title,
                indent = true
            ) {
                group.components.forEach { component ->
                    componentUi(component)
                }
            }.apply {
                expanded = true
            }
        }
    }

    private fun Panel.componentUi(component: MaterialComponent) {
        group(indent = false) {
            row(
                label = JLabel(ImageIcon(component.screenshot.toImagePath())).apply {
                    addOnClickListener {
                        navigateToComponent(component)
                    }
                }
            ) {}
            row {
                text(component.name).applyToComponent {
                    addOnClickListener {
                        navigateToComponent(component)
                    }
                }.bold()
                button("View") {
                    navigateToComponent(component)
                }
            }
            if (component.description != null) {
                row {
                    label(component.description)
                }
            }
        }
    }

    private fun detailsUi(component: MaterialComponent): JPanel = panel {
        row(
            label = JLabel(ImageIcon(component.enlargedScreenshot.toImagePath()))
        ) {}
        row {
            label("\"${component.name}\" implementation").bold()
        }
        var textArea: JBTextArea? = null
        row {
            textArea().applyToComponent {
                textArea = this
            }.text(component.codeSample)
        }
        row {
            button("Back") {
                navigateToMenu()
            }
            var copyCounter = 1
            var copiedLabel: JLabel? = null
            button("Copy") {
                copyToClipboard(textArea?.text ?: component.codeSample)
                copyCounter++
                copiedLabel?.isVisible = true
                copiedLabel?.updateUI()
            }
            label("Copied to clipboard! ${if (copyCounter > 1) "(version $copyCounter)" else ""}")
                .applyToComponent {
                    isVisible = false
                    copiedLabel = this
                }
        }
    }
}

fun copyToClipboard(content: String) {
    val selection = StringSelection(content)
    val clipboard = Toolkit.getDefaultToolkit().systemClipboard
    clipboard.setContents(selection, selection)
}


fun String.toImagePath(): URL {
    val fullPath = "/images/$this.png"
    val resource = MaterialComponentsWindowFactory::class.java.getResource(fullPath)
    requireNotNull(resource) {
        "Couldn't find an image for '$fullPath'"
    }
    return resource
}

fun Component.addOnClickListener(onClick: () -> Unit) {
    addMouseListener(object : MouseListener {
        override fun mouseClicked(p0: MouseEvent?) {
            onClick()
        }

        override fun mousePressed(p0: MouseEvent?) {}

        override fun mouseReleased(p0: MouseEvent?) {}

        override fun mouseEntered(p0: MouseEvent?) {}

        override fun mouseExited(p0: MouseEvent?) {}
    })
}