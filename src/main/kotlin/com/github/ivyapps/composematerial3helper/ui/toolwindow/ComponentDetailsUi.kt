package com.github.ivyapps.composematerial3helper.ui.toolwindow

import com.github.ivyapps.composematerial3helper.copyToClipboard
import com.github.ivyapps.composematerial3helper.data.MaterialComponent
import com.github.ivyapps.composematerial3helper.ui.common.image
import com.intellij.ui.dsl.builder.Panel
import com.intellij.ui.dsl.builder.Row
import com.intellij.ui.dsl.builder.panel
import java.awt.Dimension
import javax.swing.JLabel
import javax.swing.JPanel

class ComponentDetailsUi(
    private val navigateToMenu: () -> Unit,
) {
    fun ui(component: MaterialComponent): JPanel = panel {
        group {
            image(component.enlargedScreenshot)
            row {
                browserLink("Guidelines", component.guidelinesUrl)
                browserLink("Spec", component.specUrl)
            }
            codeArea(
                title = "${component.name} implementation",
                code = component.codeSample,
                backButton = {
                    button("Back") {
                        navigateToMenu()
                    }
                }
            )
        }
    }

    private fun Panel.codeArea(
        title: String,
        code: String,
        backButton: Row.() -> Unit = {}
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
            }
        }
        row {
            backButton()
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
}