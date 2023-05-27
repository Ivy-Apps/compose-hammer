package com.ivyapps.composematerial3helper.ui.toolwindow

import com.ivyapps.composematerial3helper.copyToClipboard
import com.ivyapps.composematerial3helper.data.MaterialComponent
import com.ivyapps.composematerial3helper.services.formatText
import com.ivyapps.composematerial3helper.services.generateImportsCode
import com.ivyapps.composematerial3helper.ui.common.image
import com.intellij.icons.AllIcons
import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.dsl.builder.Panel
import com.intellij.ui.dsl.builder.Row
import com.intellij.ui.dsl.builder.panel
import com.intellij.ui.dsl.gridLayout.HorizontalAlign
import java.awt.Dimension
import javax.swing.JLabel

class ComponentDetailsUi(
    private val navigateToMenu: () -> Unit,
) {
    fun ui(component: MaterialComponent): DialogPanel = panel {
        group(indent = true) {
            row {
                button("Back") {
                    navigateToMenu()
                }
                label(component.name).bold()
            }
            image(component.detailsScreenshot)
            row {
                browserLink("Spec", component.specUrl)
                browserLink("Guidelines", component.guidelinesUrl)
                browserLink("Docs", component.docsUrl)
            }
            if (component.description != null) {
                row {
                    label(component.description.formatText())
                }
            }
            val importsCode = generateImportsCode(component.imports)
            if (importsCode != null) {
                codeArea(
                    title = null,
                    code = importsCode
                )
            }
            codeArea(
                title = "Code",
                code = component.defaultImplementation,
                tip = component.defaultImplementationTip,
            )
            if (component.customImplementation != null) {
                codeArea(
                    title = "Customization",
                    code = component.customImplementation,
                    tip = component.customImplementationTip,
                )
            }
            gradleDependency()
        }
    }

    private fun Panel.codeArea(
        title: String?,
        code: String,
        tip: String? = null,
    ) {
        if (title != null) {
            row {
                label(title).bold()
            }
        }
        row {
            textArea().applyToComponent {
                text = code
                isEditable = false
                size = Dimension(Int.MAX_VALUE, height)
                autoscrolls = true
                updateUI()
            }.horizontalAlign(HorizontalAlign.FILL)
                .comment(tip)
        }
        row {
            copyButton(code)
        }
    }

    private fun Row.copyButton(text: String) {
        var copyCounter = 0
        var copiedLabel: JLabel? = null
        button("Copy") {
            copyToClipboard(text)
            copyCounter++
            copiedLabel?.isVisible = true
            if (copyCounter > 1) {
                copiedLabel?.text = "Copied to clipboard! ($copyCounter)"
                copiedLabel?.updateUI()
            }
        }
        label("Copied to clipboard!").applyToComponent {
            isVisible = false
            copiedLabel = this
        }
    }

    private fun Panel.gradleDependency() {
        row {
            icon(AllIcons.Actions.QuickfixOffBulb)
            label("If it doesn't work: ")
            browserLink(
                "Add the Gradle dependency",
                "https://developer.android.com/jetpack/androidx/releases/compose-material3#declaring_dependencies"
            )
        }
    }
}