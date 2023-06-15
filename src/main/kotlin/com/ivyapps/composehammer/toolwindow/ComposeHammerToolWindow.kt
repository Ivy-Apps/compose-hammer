package com.ivyapps.composehammer.toolwindow

import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.ScrollPaneFactory
import com.intellij.ui.content.Content
import com.intellij.ui.content.ContentFactory
import com.ivyapps.composehammer.domain.MaterialComponentsService
import com.ivyapps.composehammer.domain.data.MaterialComponent
import com.ivyapps.composehammer.toolwindow.screen.MaterialComponentDetails
import com.ivyapps.composehammer.toolwindow.screen.MainMenu

class ComposeHammerToolWindowFactory : ToolWindowFactory {
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val composeHammerToolWindow = ComposeHammerToolWindow(toolWindow)
        composeHammerToolWindow.navigateToMainMenu()
    }

    override fun shouldBeAvailable(project: Project) = true
}


class ComposeHammerToolWindow(private val toolWindow: ToolWindow) {
    private val contentFactory = ContentFactory.SERVICE.getInstance()
    private val service = toolWindow.project.service<MaterialComponentsService>()

    private var menuContent: Content? = null

    fun navigateToMainMenu(recreate: Boolean = false) {
        try {
            val content = menuContent?.takeIf { !recreate } ?: createMainMenuContent()
            menuContent = content // persist for the next iteration
            val contentsToRemove = toolWindow.contentManager.contents
            toolWindow.contentManager.addContent(content)
            contentsToRemove.forEach { toolWindow.contentManager.removeContent(it, true) }
        } catch (e: Exception) {
            if (!recreate) { // check to avoid potential infinite recursion
                navigateToMainMenu(recreate = true)
            }
        }
    }

    private fun createMainMenuContent(): Content = contentFactory.createContent(
        ScrollPaneFactory.createScrollPane(
            MainMenu(
                service = service,
                navigateToMaterialComponent = ::navigateToMaterialComponent
            ).ui()
        ),
        "Components",
        false,
    )

    fun navigateToMaterialComponent(component: MaterialComponent) {
        val contentsToRemove = toolWindow.contentManager.contents
        toolWindow.contentManager.addContent(
            contentFactory.createContent(
                ScrollPaneFactory.createScrollPane(
                    MaterialComponentDetails(
                        navigateToMenu = ::navigateToMainMenu
                    ).ui(component)
                ),
                component.name,
                false
            )
        )
        contentsToRemove.forEach { toolWindow.contentManager.removeContent(it, true) }
    }
}