package com.ivyapps.composehammer.toolwindow.screen.quickcode

import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.components.JBTextArea
import com.intellij.ui.components.JBTextField
import com.intellij.ui.dsl.builder.panel
import com.intellij.ui.dsl.builder.text
import com.ivyapps.composehammer.domain.data.quickcode.CodeGroup
import com.ivyapps.composehammer.domain.data.quickcode.CodeItem
import com.ivyapps.composehammer.domain.quickcode.QuickCodeService
import com.ivyapps.composehammer.domain.ui.generateImportsCode
import com.ivyapps.composehammer.toolwindow.component.DeleteButton
import com.ivyapps.composehammer.toolwindow.component.codeArea
import com.ivyapps.composehammer.toolwindow.screen.ToolWindowScreen

class CodeItemDetails(
    project: Project,
    private val codeGroup: CodeGroup,
    private val codeItem: CodeItem?,
    private val navigateToQuickCodeMenu: () -> Unit,
) : ToolWindowScreen {
    private val service = project.service<QuickCodeService>()

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
                    Supports zero or many variables using {{VARIABLE_NAME}} syntax.
                """.trimIndent(),
                code = codeItem?.code ?: "",
                tip = """
                    Paste your code here. Don't worry about formatting.
                """.trimIndent(),
                editable = true,
                hasCopy = false,
                minLines = 6,
            )
            row {
                button(
                    if (codeItem != null) "Save changes" else "+ Add item"
                ) {
                    val name = nameInput?.text ?: error("CodeItem name cannot be null!")
                    val imports = importsInput.text
                    val code = codeInput.text
                    if (codeItem != null) {
                        perform {
                            editCodeItem(
                                group = codeGroup,
                                item = codeItem,
                                rawName = name,
                                rawImports = imports,
                                rawCode = code,
                            )
                        }
                    } else {
                        perform {
                            addCodeItem(
                                group = codeGroup,
                                rawName = name,
                                rawImports = imports,
                                rawCode = code,
                            )
                        }
                    }
                }

                if (codeItem != null) {
                    DeleteButton().ui(this) {
                        perform {
                            deleteCodeItem(codeGroup, codeItem)
                            true
                        }
                    }
                }
            }
        }

    }

    private fun perform(action: QuickCodeService.() -> Boolean) {
        if (action(service)) {
            navigateToQuickCodeMenu()
        }
    }
}