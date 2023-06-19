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
import com.ivyapps.composehammer.domain.data.quickcode.QCProject
import com.ivyapps.composehammer.toolwindow.screen.MainMenu
import com.ivyapps.composehammer.toolwindow.screen.MaterialComponentDetails
import com.ivyapps.composehammer.toolwindow.screen.ToolWindowScreen
import com.ivyapps.composehammer.toolwindow.screen.quickcode.QuickCodeGroupDetails
import com.ivyapps.composehammer.toolwindow.screen.quickcode.QuickCodeItemDetails
import com.ivyapps.composehammer.toolwindow.screen.quickcode.QuickCodeMenu
import com.ivyapps.composehammer.toolwindow.screen.quickcode.QuickCodeProjectDetails

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

    private var contentCache = mutableMapOf<String, Content>()

    fun navigateToMainMenu() {
        navigateWithPersistence(
            key = "mainMenu",
            createScreen = {
                MainMenu(
                    service = m3service,
                    navigateToMaterialComponent = ::navigateToMaterialComponent,
                    navigateToCustomCodeMenu = ::navigateToQuickCode,
                ) to "Components"
            }
        )
    }

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
                pluginProject = project,
                navigateToMainMenu = ::navigateToMainMenu,
                refreshUi = ::navigateToQuickCode,
                navigateToProjectDetails = ::navigateToProjectDetails,
            ),
            screenTitle = "Quick Code",
        )
    }

    private fun navigateToProjectDetails(
        project: QCProject
    ) {
        navigateTo(
            screen = QuickCodeProjectDetails(
                project = project,
                pluginProject = this@ComposeHammerToolWindow.project,
                navigateToQuickCodeMenu = ::navigateToQuickCode,
                navigateToCodeItem = ::navigateToCodeItem,
                navigateToCodeGroup = ::navigateToCodeGroup,
                refreshUi = ::navigateToProjectDetails,
            ),
            screenTitle = "Project ${project.name}"
        )
    }

    private fun navigateToCodeItem(
        group: CodeGroup,
        item: CodeItem?
    ) {
        navigateTo(
            screen = QuickCodeItemDetails(
                pluginProject = project,
                codeGroup = group,
                codeItem = item,
                navigateToQuickCodeMenu = ::navigateToQuickCode,
            ),
            screenTitle = "[${group.name}] ${item?.name ?: "New"}"
        )
    }

    private fun navigateToCodeGroup(group: CodeGroup) {
        navigateTo(
            screen = QuickCodeGroupDetails(
                project = project,
                codeGroup = group,
                navigateToQuickCodeMenu = ::navigateToQuickCode,
            ),
            screenTitle = group.name
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

    private fun navigateWithPersistence(
        key: String,
        createScreen: () -> Pair<ToolWindowScreen, String>,
        recreate: Boolean = false
    ) {
        try {
            val content = contentCache[key]?.takeIf { !recreate } ?: createContent(createScreen())
            contentCache[key] = content // persist for the next iteration
            val contentsToRemove = toolWindow.contentManager.contents
            toolWindow.contentManager.addContent(content)
            contentsToRemove.forEach { toolWindow.contentManager.removeContent(it, true) }
        } catch (e: Exception) {
            if (!recreate) { // check to avoid potential infinite recursion
                navigateWithPersistence(
                    key = key,
                    createScreen = createScreen,
                    recreate = true
                )
            }
        }
    }

    private fun createContent(
        screenTitle: Pair<ToolWindowScreen, String>
    ): Content {
        return contentFactory.createContent(
            ScrollPaneFactory.createScrollPane(screenTitle.first.ui),
            screenTitle.second,
            false,
        )
    }
}