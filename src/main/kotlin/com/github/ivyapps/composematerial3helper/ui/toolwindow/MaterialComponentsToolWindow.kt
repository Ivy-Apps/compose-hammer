package com.github.ivyapps.composematerial3helper.ui.toolwindow

import com.github.ivyapps.composematerial3helper.data.MaterialComponent
import com.github.ivyapps.composematerial3helper.services.MaterialComponentsService
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.profile.codeInspection.ui.addScrollPaneIfNecessary
import com.intellij.ui.content.ContentFactory
import java.awt.Component
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import java.net.URL

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
                addScrollPaneIfNecessary(
                    ComponentsMenuUi(
                        toolWindow = toolWindow,
                        service = service,
                        navigateToComponent = ::navigateToComponent
                    ).ui()
                ),
                "M3 Components",
                false,
            )
        )
    }

    private fun navigateToComponent(component: MaterialComponent) {
        toolWindow.contentManager.removeAllContents(false)
        toolWindow.contentManager.addContent(
            contentFactory.createContent(
                addScrollPaneIfNecessary(
                    ComponentDetailsUi(
                        toolWindow = toolWindow,
                        navigateToMenu = ::navigateToMenu
                    ).ui(component)
                ),
                component.name,
                false
            )
        )
    }
}