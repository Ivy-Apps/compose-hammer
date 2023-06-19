package com.ivyapps.composehammer.toolwindow.screen.quickcode

import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.components.JBTextField
import com.intellij.ui.dsl.builder.Panel
import com.intellij.ui.dsl.builder.panel
import com.ivyapps.composehammer.domain.data.quickcode.QCProject
import com.ivyapps.composehammer.domain.quickcode.ExportQuickCodeService
import com.ivyapps.composehammer.domain.quickcode.ImportQuickCodeService
import com.ivyapps.composehammer.domain.quickcode.QuickCodeService
import com.ivyapps.composehammer.toolwindow.screen.ToolWindowScreen

class QuickCodeMenu(
    private val project: Project,
    private val navigateToMainMenu: () -> Unit,
    private val refreshUi: () -> Unit,
) : ToolWindowScreen {
    private val service = project.service<QuickCodeService>()

    override val ui: DialogPanel = panel {
        header()
    }

    private fun Panel.header() {
        group(indent = true) {
            row {
                button("Back") {
                    navigateToMainMenu()
                }
                text("⚡ Quick Code").bold()
            }
            importExport()
            addCodeGroupSection()
            projects()
        }
    }

    private fun Panel.importExport(
        indent: Boolean = false,
    ) {
        group(indent = indent) {
            row {
                text("Configurations").bold()
            }
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
                    project.service<ImportQuickCodeService>().import()
                    refreshUi()
                }
                button("Export") {
                    project.service<ExportQuickCodeService>().export()
                }
            }
        }
    }

    private fun Panel.addCodeGroupSection() {
        group(indent = true) {
            row {
                text("Project").bold()
            }
            row {
                label(
                    """
                    Projects contain your custom components and code snippets.
                """.trimIndent()
                )
            }
            row {
                val inputField: JBTextField
                textField().also {
                    inputField = it.component
                }.comment("Project name")
                button("Add new") {
                    val projectName = inputField.text
                    // TODO: Add project
                }
            }
        }
    }

    private fun Panel.projects() {
        val projects = service.projects + listOf(
            QCProject("Company 1", order = 1.0),
            QCProject("Personal", order = 3.0)
        )
        projects.forEachIndexed { index, qcProject ->
            project(index, qcProject, projects.size)
        }
    }

    private fun Panel.project(
        index: Int,
        qcProject: QCProject,
        projectsCount: Int,
    ) {
        group(
            title = qcProject.name,
            indent = true,
        ) {

        }
    }


}