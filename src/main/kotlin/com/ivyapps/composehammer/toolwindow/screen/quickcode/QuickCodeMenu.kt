package com.ivyapps.composehammer.toolwindow.screen.quickcode

import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.components.JBTextField
import com.intellij.ui.dsl.builder.BottomGap
import com.intellij.ui.dsl.builder.Panel
import com.intellij.ui.dsl.builder.TopGap
import com.intellij.ui.dsl.builder.panel
import com.ivyapps.composehammer.domain.data.quickcode.QCProject
import com.ivyapps.composehammer.domain.quickcode.ExportQuickCodeService
import com.ivyapps.composehammer.domain.quickcode.ImportQuickCodeService
import com.ivyapps.composehammer.domain.quickcode.service.QuickCodeService.ProjectInput
import com.ivyapps.composehammer.domain.quickcode.service.toResultEither
import com.ivyapps.composehammer.toolwindow.screen.quickcode.component.itemControls
import java.awt.event.ItemEvent

class QuickCodeMenu(
    private val pluginProject: Project,
    private val navigateToMainMenu: () -> Unit,
    private val refreshUi: () -> Unit,
    private val navigateToProjectDetails: (QCProject) -> Unit,
) : QuickCodeToolWindow<QCProject?>(pluginProject) {

    override fun onRefreshUi(updatedItem: QCProject?) {
        refreshUi()
    }

    override val ui: DialogPanel = panel {
        group(indent = true) {
            row {
                button("Back") {
                    navigateToMainMenu()
                }
                text("⚡ Quick Code").bold()
            }
            importExport()
            addProjectSection()
            projects()
        }
    }

    private fun Panel.importExport(
    ) {
        group(
            indent = false,
            title = "Configurations"
        ) {
            row {
                label(
                    "Import an existing Quick Code configuration" +
                            "\nor export your current one."
                ).comment(
                    """
                        Tip: You can import more than one configurations.
                    """.trimIndent()
                )
            }
            row {
                button("Import") {
                    pluginProject.service<ImportQuickCodeService>().import()
                    refreshUi()
                }
                button("Export") {
                    pluginProject.service<ExportQuickCodeService>().export()
                }
            }
        }
    }

    private fun Panel.addProjectSection() {
        group(
            title = "Projects",
            indent = false,
        ) {
            row {
                label(
                    """
                    Projects contain your custom components and code snippets.
                """.trimIndent()
                )
            }
            row {
                text("Add new project")
            }
            row {
                val inputField: JBTextField
                textField().also {
                    inputField = it.component
                }.comment("Project name")
                button("Add new") {
                    val projectName = inputField.text
                    perform {
                        ProjectOps().addItem(
                            ProjectInput(
                                rawName = projectName
                            )
                        ).toResultEither()
                    }
                }
            }
        }
    }

    private fun Panel.projects() {
        val projects = service.projects
        projects.forEachIndexed { index, qcProject ->
            project(index, qcProject, projects.size)
        }
    }

    private fun Panel.project(
        index: Int,
        project: QCProject,
        projectsCount: Int,
    ) {
        group(
            title = "Project: ${project.name}",
            indent = true,
        ) {
            row {
                checkBox("Enabled")
                    .apply {
                        // Get the underlying Swing component (JCheckBox)
                        val jCheckBox = component
                        // Add an ItemListener
                        jCheckBox.addItemListener { event ->
                            // Do something when the checkbox state changes
                            val checked = event.stateChange == ItemEvent.SELECTED
                            if (checked != project.enabled) {
                                perform {
                                    ProjectOps().editItem(
                                        item = project,
                                        input = ProjectInput(
                                            rawName = project.name,
                                            enabled = checked,
                                        )
                                    ).toResultEither()
                                }
                            }
                        }
                        jCheckBox.isSelected = project.enabled

                    }
                    .comment("Whether the project to appear in the quick action.")
            }
            itemControls(
                index = index,
                item = project,
                itemsCount = projectsCount,
                viewCta = "View",
                itemLabel = "project",
                onDelete = {
                    perform {
                        ProjectOps().deleteItem(it)
                    }
                },
                onMoveUp = {
                    perform {
                        ProjectOps().moveItemUp(it).toResultEither()
                    }
                },
                onMoveDown = {
                    perform {
                        ProjectOps().moveItemUp(it).toResultEither()
                    }
                },
                onNavigateToItemDetails = {
                    navigateToProjectDetails(project)
                }
            )
            row {
                text("${project.name}:").bold()
                label("${project.groups.size} groups")
                label("${project.groups.sumOf { it.codeItems.size }} snippets")
            }
        }.topGap(TopGap.SMALL)
            .bottomGap(BottomGap.SMALL)
    }
}