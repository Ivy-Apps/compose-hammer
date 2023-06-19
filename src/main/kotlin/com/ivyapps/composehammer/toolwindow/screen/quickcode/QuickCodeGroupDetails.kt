package com.ivyapps.composehammer.toolwindow.screen.quickcode

import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.components.JBTextField
import com.intellij.ui.dsl.builder.panel
import com.intellij.ui.dsl.builder.text
import com.ivyapps.composehammer.domain.data.quickcode.CodeGroup
import com.ivyapps.composehammer.domain.data.quickcode.QCProject
import com.ivyapps.composehammer.domain.quickcode.service.QuickCodeService.CodeGroupInput
import com.ivyapps.composehammer.domain.quickcode.service.toResultEither

class QuickCodeGroupDetails(
    pluginProject: Project,
    private val project: QCProject,
    private val codeGroup: CodeGroup,
    private val navigateToQuickCodeMenu: () -> Unit,
) : QuickCodeToolWindow<CodeGroup?>(pluginProject) {

    override fun onRefreshUi(updatedItem: CodeGroup?) {
        navigateToQuickCodeMenu()
    }

    override val ui: DialogPanel = panel {
        group(
            indent = true
        ) {
            var nameInput: JBTextField? = null

            row {
                button("Back") {
                    navigateToQuickCodeMenu()
                }
            }
            row {
                text("Name").bold()
            }
            row {
                textField()
                    .also {
                        nameInput = it.component
                    }
                    .text(codeGroup.name)
                    .comment("The name of the code group.")
                    .bold()
            }
            row {
                button("Rename") {
                    perform {
                        CodeGroupOps(project).editItem(
                            item = codeGroup,
                            input = CodeGroupInput(
                                rawName = nameInput!!.text,
                            )
                        ).toResultEither()
                    }
                }
            }
        }
    }
}