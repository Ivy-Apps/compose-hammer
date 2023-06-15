package com.ivyapps.composehammer.toolwindow

import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.ScrollPaneFactory
import com.intellij.ui.content.Content
import com.intellij.ui.content.ContentFactory
import com.ivyapps.composehammer.domain.MaterialComponentsService
import com.ivyapps.composehammer.domain.data.material3.MaterialComponent
import com.ivyapps.composehammer.toolwindow.screen.CustomComponentsMenu
import com.ivyapps.composehammer.toolwindow.screen.MainMenu
import com.ivyapps.composehammer.toolwindow.screen.MaterialComponentDetails
import javax.swing.JComponent

class ComposeHammerToolWindowFactory : ToolWindowFactory {
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val composeHammerToolWindow = ComposeHammerToolWindow(toolWindow)
        composeHammerToolWindow.navigateToMainMenu()
    }

    override fun shouldBeAvailable(project: Project) = true
}


class ComposeHammerToolWindow(private val toolWindow: ToolWindow) {
    private val contentFactory = ContentFactory.SERVICE.getInstance()
    private val m3service = toolWindow.project.service<MaterialComponentsService>()

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
                service = m3service,
                navigateToMaterialComponent = ::navigateToMaterialComponent,
                navigateToCustomComponentsMenu = ::navigateToCustomComponents,
            ).ui()
        ),
        "Components",
        false,
    )

    fun navigateToMaterialComponent(component: MaterialComponent) {
        navigateTo(
            screen = MaterialComponentDetails(
                navigateToMenu = ::navigateToMainMenu
            ).ui(component),
            screenTitle = component.name
        )
    }

    fun navigateToCustomComponents() {
        navigateTo(
            screen = CustomComponentsMenu(
                project = toolWindow.project,
                navigateToMainMenu = ::navigateToMainMenu,
                refreshUi = ::navigateToCustomComponents,
            ).ui(),
            screenTitle = "Custom"
        )
    }

    private fun navigateTo(
        screen: JComponent,
        screenTitle: String,
    ) {
        val contentsToRemove = toolWindow.contentManager.contents
        toolWindow.contentManager.addContent(
            contentFactory.createContent(
                ScrollPaneFactory.createScrollPane(screen),
                screenTitle,
                false
            )
        )
        contentsToRemove.forEach { toolWindow.contentManager.removeContent(it, true) }
    }
}