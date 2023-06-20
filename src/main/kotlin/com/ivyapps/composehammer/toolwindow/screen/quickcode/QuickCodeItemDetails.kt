package com.ivyapps.composehammer.toolwindow.screen.quickcode

import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.components.JBTextArea
import com.intellij.ui.components.JBTextField
import com.intellij.ui.dsl.builder.panel
import com.intellij.ui.dsl.builder.text
import com.ivyapps.composehammer.domain.data.mapRight
import com.ivyapps.composehammer.domain.data.quickcode.CodeGroup
import com.ivyapps.composehammer.domain.data.quickcode.CodeItem
import com.ivyapps.composehammer.domain.data.quickcode.QCProject
import com.ivyapps.composehammer.domain.quickcode.service.QuickCodeService.CodeItemInput
import com.ivyapps.composehammer.domain.quickcode.service.toResultEither
import com.ivyapps.composehammer.domain.ui.generateImportsCode
import com.ivyapps.composehammer.toolwindow.component.DeleteButton
import com.ivyapps.composehammer.toolwindow.component.codeArea

class QuickCodeItemDetails(
    pluginProject: Project,
    private val project: QCProject,
    private val codeGroup: CodeGroup,
    private val codeItem: CodeItem?,
    private val navigateToProjectDetails: (QCProject) -> Unit,
) : QuickCodeToolWindow<CodeItem?>(pluginProject) {

    override fun onRefreshUi(updatedItem: CodeItem?) {
        navigateToProjectDetails(service.findProjectByName(project.name))
    }

    override val ui: DialogPanel = panel {
        group(
            indent = true
        ) {
            var nameInput: JBTextField? = null

            row {
                button("Back") {
                    navigateToProjectDetails(project)
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
                    .text(codeItem?.name ?: "")
                    .comment("The name of the code item.")
                    .bold()
            }
            val importsInput = codeArea(
                title = "Imports",
                code = generateImportsCode(codeItem?.imports ?: emptyList()) ?: "",
                tip = """
                    Paste your imports as they are. Like "import x.y.z".
                """.trimIndent(),
                editable = true,
                hasCopy = false,
                minLines = 3,
            )
            val codeInput: JBTextArea = codeArea(
                title = "Code",
                description = """
                    Paste your code as it is. Don't worry about formatting.
                """.trimIndent(),
                code = codeItem?.code ?: "",
                tip = """
                    Pro-tip: Use {{variableName}} to introduce variables.
                """.trimIndent(),
                editable = true,
                hasCopy = false,
                minLines = 6,
            )
            row {
                label("The code input supports")
                browserLink(
                    "QuickCode",
                    "https://github.com/Ivy-Apps/quickcode/blob/main/README.md"
                )
                    .comment("Try it out! It's worth it.")
            }
            row {
                button(
                    if (codeItem != null) "Save changes" else "+ Add item"
                ) {
                    val name = nameInput?.text ?: error("CodeItem name cannot be null!")
                    val imports = importsInput.text
                    val code = codeInput.text

                    val input = CodeItemInput(
                        rawName = name,
                        rawImports = imports,
                        rawCode = code,
                    )
                    if (codeItem != null) {
                        perform {
                            CodeItemOps(project, codeGroup).editItem(
                                item = codeItem,
                                input
                            ).toResultEither()
                        }
                    } else {
                        perform {
                            CodeItemOps(project, codeGroup).addItem(
                                input = input
                            ).toResultEither()
                        }
                    }
                }

                if (codeItem != null) {
                    DeleteButton().ui(this) {
                        perform {
                            CodeItemOps(project, codeGroup).deleteItem(
                                item = codeItem,
                            ).mapRight { null }
                        }
                    }
                }
            }
        }
    }
}