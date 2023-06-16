package com.ivyapps.composehammer.toolwindow.screen.quickcode

import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.ui.components.JBTextField
import com.intellij.ui.dsl.builder.Panel
import com.intellij.ui.dsl.builder.panel
import com.ivyapps.composehammer.domain.data.quickcode.CodeGroup
import com.ivyapps.composehammer.domain.data.quickcode.CodeItem
import com.ivyapps.composehammer.domain.quickcode.QuickCodeService
import com.ivyapps.composehammer.toolwindow.component.deleteButton

class QuickCodeMenu(
    project: Project,
    private val navigateToMainMenu: () -> Unit,
    private val refreshUi: () -> Unit,
    private val navigateToCodeItemDetails: (CodeItem?) -> Unit,
) {
    private val service = project.service<QuickCodeService>()

    fun ui() = panel {
        row {
            button("Back") {
                navigateToMainMenu()
            }
            text("Quick Code!").bold()
        }
        row {
            button("Import") {

            }
            button("Export") {

            }
        }
        row {
            val inputField: JBTextField
            textField().also {
                inputField = it.component
            }.comment("Code group name")
            button("Add") {
                perform { addGroup(inputField.name) }
            }
        }
        codeGroups()
    }

    private fun Panel.codeGroups() {
        service.groups.forEach { group ->
            codeGroup(group)
        }
    }

    private fun Panel.codeGroup(group: CodeGroup) {
        collapsibleGroup(
            title = "${group.name} (${group.codeItems.size})",
            indent = true
        ) {
            row {
                button("Move up") {
                    perform { moveGroupUp(group) }
                }
                button("Move down") {
                    perform { moveGroupDown(group) }
                }
                deleteButton {
                    perform { deleteGroup(group) }
                }
            }
            row {
                text("Code item")
                button("Add new") {

                }
            }
            group.codeItems.forEach { codeItem ->
                codeItemUi(group, codeItem)
            }
        }
    }

    private fun Panel.codeItemUi(group: CodeGroup, item: CodeItem) {
        row {
            text(item.name)
            button("Move up") {
                perform { moveItemUp(group, item) }
            }
            button("Move down") {
                perform { moveItemDown(group, item) }
            }
            deleteButton {
                perform { deleteItem(group, item) }
            }
        }
    }

    private fun perform(action: QuickCodeService.() -> Unit) {
        action(service)
        refreshUi()
    }
}