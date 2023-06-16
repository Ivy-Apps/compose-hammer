package com.ivyapps.composehammer.toolwindow.screen.quickcode

import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.components.JBTextField
import com.intellij.ui.dsl.builder.Panel
import com.intellij.ui.dsl.builder.panel
import com.ivyapps.composehammer.addOnClickListener
import com.ivyapps.composehammer.domain.data.quickcode.CodeGroup
import com.ivyapps.composehammer.domain.data.quickcode.CodeItem
import com.ivyapps.composehammer.domain.quickcode.QuickCodeService
import com.ivyapps.composehammer.toolwindow.component.deleteButton
import com.ivyapps.composehammer.toolwindow.screen.ToolWindowScreen

class QuickCodeMenu(
    project: Project,
    private val navigateToMainMenu: () -> Unit,
    private val refreshUi: () -> Unit,
    private val navigateToCodeItem: (CodeGroup, CodeItem?) -> Unit,
) : ToolWindowScreen {
    private val service = project.service<QuickCodeService>()

    override val ui: DialogPanel = panel {
        row {
            button("Back") {
                navigateToMainMenu()
            }
            text("⚡ Quick Code").bold()
        }
        importExportSection()
        addCodeGroupSection()
        codeGroups()
    }

    private fun Panel.importExportSection() {
        group(indent = true) {
            row {
                text("File configuration")
            }
            row {
                button("Import") {
                    // TODO: Implement
                }
                button("Export") {
                    // TODO: Implement
                }
            }
        }
    }

    private fun Panel.addCodeGroupSection() {
        group(indent = true) {
            row {
                val inputField: JBTextField
                textField().also {
                    inputField = it.component
                }.comment("Code group name")
                button("Add") {
                    perform { addGroup(inputField.text) }
                }
            }
        }
    }

    private fun Panel.codeGroups() {
        val groups = service.groups
        groups.forEachIndexed { index, group ->
            codeGroup(index, group, groups.size)
        }
    }

    private fun Panel.codeGroup(
        index: Int,
        group: CodeGroup,
        groupsCount: Int,
    ) {
        val codeItems = group.codeItems
        collapsibleGroup(
            title = "${group.name} (${codeItems.size})",
            indent = true
        ) {
            row {
                if (index > 0) {
                    button("Move up") {
                        perform { moveGroupUp(group) }
                    }
                }
                if (index < groupsCount - 1) {
                    button("Move down") {
                        perform { moveGroupDown(group) }
                    }
                }
                deleteButton(
                    notConfirmedLabel = "Delete \"${group.name}\" group"
                ) {
                    perform { deleteGroup(group) }
                }
            }
            row {
                text("Code item:")
                button("+ Add new code item") {
                    navigateToCodeItem(group, null)
                }
            }
            codeItems.sortedBy {
                it.order
            }.forEachIndexed { index, codeItem ->
                codeItemUi(index, group, codeItem, codeItems.size)
            }
        }
    }

    private fun Panel.codeItemUi(
        index: Int,
        group: CodeGroup,
        item: CodeItem,
        itemsCount: Int,
    ) {
        row {
            text(item.name).component.addOnClickListener {
                navigateToCodeItem(group, item)
            }
            button("View/Edit") {
                navigateToCodeItem(group, item)
            }
            if (index > 0) {
                button("Move up") {
                    perform { moveCodeItemUp(group, item) }
                }
            }
            if (index < itemsCount - 1) {
                button("Move down") {
                    perform { moveCodeItemDown(group, item) }
                }
            }
            deleteButton {
                perform { deleteCodeItem(group, item) }
            }
        }
    }

    private fun perform(action: QuickCodeService.() -> Unit) {
        action(service)
        refreshUi()
    }
}