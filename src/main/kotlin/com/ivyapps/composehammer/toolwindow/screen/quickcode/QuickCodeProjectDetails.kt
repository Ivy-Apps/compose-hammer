package com.ivyapps.composehammer.toolwindow.screen.quickcode

import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.components.JBTextField
import com.intellij.ui.dsl.builder.Panel
import com.intellij.ui.dsl.builder.bindText
import com.intellij.ui.dsl.builder.panel
import com.intellij.ui.dsl.builder.text
import com.ivyapps.composehammer.addOnClickListener
import com.ivyapps.composehammer.domain.data.mapRight
import com.ivyapps.composehammer.domain.data.quickcode.CodeGroup
import com.ivyapps.composehammer.domain.data.quickcode.CodeItem
import com.ivyapps.composehammer.domain.data.quickcode.QCProject
import com.ivyapps.composehammer.domain.quickcode.service.QuickCodeService.CodeGroupInput
import com.ivyapps.composehammer.domain.quickcode.service.QuickCodeService.ProjectInput
import com.ivyapps.composehammer.domain.quickcode.service.toResultEither
import com.ivyapps.composehammer.toolwindow.screen.quickcode.component.itemControls

class QuickCodeProjectDetails(
    pluginProject: Project,
    private val project: QCProject,
    private val navigateToQuickCodeMenu: () -> Unit,
    private val navigateToCodeItem: (CodeGroup, CodeItem?) -> Unit,
    private val navigateToCodeGroup: (CodeGroup) -> Unit,
    private val refreshUi: (QCProject) -> Unit,
) : QuickCodeToolWindow<QCProject?>(pluginProject) {

    override fun onRefreshUi(updatedItem: QCProject?) {
        if (updatedItem != null) {
            refreshUi(updatedItem)
        } else {
            // project deleted or something wrong happened
            navigateToQuickCodeMenu()
        }
    }

    override val ui: DialogPanel = panel {
        group(indent = true) {
            row {
                button("Back") {
                    navigateToQuickCodeMenu()
                }
            }
            projectName()
            addCodeGroupSection()
        }
        codeGroups()
    }

    private fun Panel.projectName() {
        row {
            text("Project name").bold()
        }
        row {
            textField()
                .text(project.name)
                .bindText(
                    getter = { project.name },
                    setter = { newName ->
                        perform {
                            ProjectOps().editItem(
                                item = project,
                                input = ProjectInput(
                                    rawName = newName,
                                )
                            ).toResultEither()
                        }
                    }
                )
                .comment("Project name")
        }
    }

    private fun Panel.addCodeGroupSection() {
        group(indent = false) {
            row {
                text("Code groups").bold()
            }
            row {
                label(
                    """
                        Code groups are groups of code snippets.
                        They appear in the "Quick Code" quick action.
                    """.trimIndent()
                )
            }
            row {
                val inputField: JBTextField
                textField().also {
                    inputField = it.component
                }.comment("Code group name")
                button("Add new") {
                    perform {
                        CodeGroupOps(project).addItem(
                            CodeGroupInput(rawName = inputField.text)
                        ).toResultEither()
                            .mapRight {
                                service.findProjectByName(project.name)
                            }
                    }
                }
            }
        }
    }

    private fun Panel.codeGroups() {
        val groups = project.groups
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
        itemControls(
            index = index,
            item = group,
            itemsCount = groupsCount,
            viewCta = "Rename",
            itemLabel = "group",
            onNavigateToItemDetails = navigateToCodeGroup,
            onMoveUp = {
                perform {
                    CodeGroupOps(project).moveItemUp(it)
                        .toResultEither()
                        .mapRight {
                            service.findProjectByName(project.name)
                        }
                }
            },
            onMoveDown = {
                perform {
                    CodeGroupOps(project).moveItemDown(it)
                        .toResultEither()
                        .mapRight {
                            service.findProjectByName(project.name)
                        }
                }
            },
            onDelete = {
                perform {
                    CodeGroupOps(project).deleteItem(it)
                        .mapRight {
                            service.findProjectByName(project.name)
                        }
                }
            }
        )
    }

    private fun Panel.codeGroupItems(
        group: CodeGroup,
    ) {
        val codeItems = group.codeItems
        group(indent = false) {
            row {
                label("Code items").bold()
            }
            codeItems.sortedBy {
                it.order
            }.forEachIndexed { index, codeItem ->
                codeItemUi(index, group, codeItem, codeItems.size)
            }
            if (codeItems.isEmpty()) {
                row {
                    text("No code items, yet.")
                }
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
                    perform {
                        CodeItemOps(project, group).moveItemUp(item)
                            .toResultEither()
                            .mapRight {
                                service.findProjectByName(project.name)
                            }
                    }
                }
            }
            if (index < itemsCount - 1) {
                button("Move down") {
                    perform {
                        CodeItemOps(project, group).moveItemDown(item)
                            .toResultEither()
                            .mapRight {
                                service.findProjectByName(project.name)
                            }
                    }
                }
            }
        }
    }

}