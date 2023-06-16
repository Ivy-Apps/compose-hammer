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
import com.ivyapps.composehammer.toolwindow.component.DeleteButton
import com.ivyapps.composehammer.toolwindow.screen.ToolWindowScreen

class QuickCodeMenu(
    project: Project,
    private val navigateToMainMenu: () -> Unit,
    private val refreshUi: () -> Unit,
    private val navigateToCodeItem: (CodeGroup, CodeItem?) -> Unit,
    private val navigateToCodeGroup: (CodeGroup) -> Unit,
) : ToolWindowScreen {
    private val service = project.service<QuickCodeService>()

    override val ui: DialogPanel = panel {
        header()
        addCodeGroupSection()
        codeGroups()
    }

    private fun Panel.header() {
        group(indent = true) {
            row {
                button("Back") {
                    navigateToMainMenu()
                }
            }
            row {
                text("⚡ Quick Code configuration").bold()
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
                text("Code groups").bold()
            }
            row {
                val inputField: JBTextField
                textField().also {
                    inputField = it.component
                }.comment("Code group name")
                button("Add new") {
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
        collapsibleGroup(
            title = "${group.name} (${group.codeItems.size})",
            indent = true
        ) {
            codeGroupControls(index, group, groupsCount)
            codeGroupItems(group)
            row {
                button("+ Add new code item") {
                    navigateToCodeItem(group, null)
                }
            }
        }
    }

    private fun Panel.codeGroupControls(
        index: Int,
        group: CodeGroup,
        groupsCount: Int,
    ) {
        row {
            button("Rename") {
                navigateToCodeGroup(group)
            }
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
            DeleteButton().ui(
                row = this,
                notConfirmedLabel = "Delete \"${group.name}\" group"
            ) {
                perform { deleteGroup(group) }
            }
        }
    }

    private fun Panel.codeGroupItems(
        group: CodeGroup,
    ) {
        val codeItems = group.codeItems
        group(indent = false) {
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
            text(item.name).also {
                it.component.addOnClickListener {
                    navigateToCodeItem(group, item)
                }
            }.bold()
            button("View") {
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
            DeleteButton().ui(this) {
                perform { deleteCodeItem(group, item) }
            }
        }
    }

    private fun perform(action: QuickCodeService.() -> Unit) {
        action(service)
        refreshUi()
    }
}