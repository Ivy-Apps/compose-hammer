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
import com.ivyapps.composehammer.toolwindow.component.codeArea
import com.ivyapps.composehammer.toolwindow.component.deleteButton
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
            val importsInput: JBTextArea
            val codeInput: JBTextArea

            row {
                button("Back") {
                    navigateToQuickCodeMenu()
                }
            }

            row {
                textField()
                    .also {
                        nameInput = it.component
                    }
                    .text(codeItem?.name ?: "")
                    .bold()
            }
            importsInput = codeArea(
                title = "Imports",
                code = generateImportsCode(codeItem?.imports ?: emptyList()) ?: "",
                tip = """
                    Paste the imports are "import x.y.z"
                """.trimIndent()
            )
            codeInput = codeArea(
                title = "Code",
                code = codeItem?.code ?: "",
                tip = """
                    Paste your code here. Don't worry about formatting.
                """.trimIndent()
            )
            row {
                button("Save") {
                    val name = nameInput?.text ?: error("CodeItem name cannot be null!")
                    val imports = importsInput.text
                    val code = codeInput.text
                    if (codeItem != null) {
                        perform {
                            editCodeItem(
                                group = codeGroup,
                                item = codeItem,
                                name = name,
                                imports = imports,
                                code = code,
                            )
                        }
                    } else {
                        perform {
                            addItem(
                                group = codeGroup,
                                name = name,
                                imports = imports,
                                code = code,
                            )
                        }
                    }
                }

                if (codeItem != null) {
                    deleteButton {
                        perform {
                            deleteItem(codeGroup, codeItem)
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