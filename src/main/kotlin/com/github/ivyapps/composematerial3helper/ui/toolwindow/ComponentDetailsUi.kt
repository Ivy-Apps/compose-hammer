package com.github.ivyapps.composematerial3helper.ui.toolwindow

import com.github.ivyapps.composematerial3helper.copyToClipboard
import com.github.ivyapps.composematerial3helper.data.MaterialComponent
import com.github.ivyapps.composematerial3helper.ui.common.image
import com.intellij.icons.AllIcons
import com.intellij.ui.dsl.builder.Panel
import com.intellij.ui.dsl.builder.Row
import com.intellij.ui.dsl.builder.panel
import com.intellij.ui.dsl.gridLayout.HorizontalAlign
import java.awt.Dimension
import javax.swing.JLabel
import javax.swing.JPanel

class ComponentDetailsUi(
    private val navigateToMenu: () -> Unit,
) {
    fun ui(component: MaterialComponent): JPanel = panel {
        group {
            row {
                label(component.name).bold()
            }
            image(component.detailsScreenshot)
            row {
                browserLink("Guidelines", component.guidelinesUrl)
                browserLink("Spec", component.specUrl)
            }
            codeArea(
                title = "Implementation",
                code = component.implementation,
                backButton = {
                    button("Back") {
                        navigateToMenu()
                    }
                },
                tip = "Recommended. Inherits the colors of from Material theme."
            )
        }
    }

    private fun Panel.codeArea(
        title: String,
        code: String,
        backButton: Row.() -> Unit = {},
        tip: String? = null,
    ) {
        row {
            label(title).bold()
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
            backButton()
            copyButton(code)
        }
        gradleDependency()
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