package com.ivyapps.composehammer.toolwindow

import com.ivyapps.composehammer.domain.data.MaterialComponent
import com.ivyapps.composehammer.domain.MaterialComponentsService
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.ScrollPaneFactory
import com.intellij.ui.content.ContentFactory

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

    private val menuContent by lazy {
        contentFactory.createContent(
            ScrollPaneFactory.createScrollPane(
                ComponentsMenuUi(
                    service = service,
                    navigateToComponent = ::navigateToComponent
                ).ui()
            ),
            "Components",
            false,
        )
    }

    fun navigateToMenu() {
        val contentsToRemove = toolWindow.contentManager.contents
        toolWindow.contentManager.addContent(menuContent)
        contentsToRemove.forEach { toolWindow.contentManager.removeContent(it, true) }
    }

    fun navigateToComponent(component: MaterialComponent) {
        val contentsToRemove = toolWindow.contentManager.contents
        toolWindow.contentManager.addContent(
            contentFactory.createContent(
                ScrollPaneFactory.createScrollPane(
                    ComponentDetailsUi(
                        navigateToMenu = ::navigateToMenu
                    ).ui(component)
                ),
                component.name,
                false
            )
        )
        contentsToRemove.forEach { toolWindow.contentManager.removeContent(it, true) }
    }
}