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
import com.ivyapps.composehammer.domain.data.quickcode.CodeGroup
import com.ivyapps.composehammer.domain.data.quickcode.CodeItem
import com.ivyapps.composehammer.toolwindow.screen.MainMenu
import com.ivyapps.composehammer.toolwindow.screen.MaterialComponentDetails
import com.ivyapps.composehammer.toolwindow.screen.ToolWindowScreen
import com.ivyapps.composehammer.toolwindow.screen.quickcode.CodeItemDetails
import com.ivyapps.composehammer.toolwindow.screen.quickcode.QuickCodeMenu

class ComposeHammerToolWindowFactory : ToolWindowFactory {
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val composeHammerToolWindow = ComposeHammerToolWindow(toolWindow)
        composeHammerToolWindow.navigateToMainMenu()
    }

    override fun shouldBeAvailable(project: Project) = true
}


class ComposeHammerToolWindow(private val toolWindow: ToolWindow) {
    private val project = toolWindow.project
    private val contentFactory = ContentFactory.SERVICE.getInstance()
    private val m3service = project.service<MaterialComponentsService>()

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
                navigateToCustomCodeMenu = ::navigateToQuickCode,
            ).ui
        ),
        "Components",
        false,
    )

    private fun navigateToMaterialComponent(component: MaterialComponent) {
        navigateTo(
            screen = MaterialComponentDetails(
                component = component,
                navigateToMenu = ::navigateToMainMenu
            ),
            screenTitle = component.name
        )
    }

    private fun navigateToQuickCode() {
        navigateTo(
            screen = QuickCodeMenu(
                project = project,
                navigateToMainMenu = ::navigateToMainMenu,
                refreshUi = ::navigateToQuickCode,
                navigateToCodeItem = ::navigateToCodeItem,
            ),
            screenTitle = "Quick Code"
        )
    }

    private fun navigateToCodeItem(
        group: CodeGroup,
        item: CodeItem?
    ) {
        navigateTo(
            screen = CodeItemDetails(
                project = project,
                codeGroup = group,
                codeItem = item,
                navigateToQuickCodeMenu = ::navigateToQuickCode,
            ),
            screenTitle = "[${group.name}] ${item?.name ?: "New"}"
        )
    }

    private fun navigateTo(
        screen: ToolWindowScreen,
        screenTitle: String,
    ) {
        val contentsToRemove = toolWindow.contentManager.contents
        toolWindow.contentManager.addContent(
            contentFactory.createContent(
                ScrollPaneFactory.createScrollPane(screen.ui),
                screenTitle,
                false
            )
        )
        contentsToRemove.forEach { toolWindow.contentManager.removeContent(it, true) }
    }
}