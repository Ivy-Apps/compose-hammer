package com.github.ivyapps.composematerial3helper.ui.toolwindow

import com.github.ivyapps.composematerial3helper.copyToClipboard
import com.github.ivyapps.composematerial3helper.data.MaterialComponent
import com.github.ivyapps.composematerial3helper.toImagePath
import com.intellij.ui.components.JBTextArea
import com.intellij.ui.dsl.builder.panel
import com.intellij.ui.dsl.builder.text
import javax.swing.ImageIcon
import javax.swing.JLabel
import javax.swing.JPanel

class ComponentDetailsUi(
    private val navigateToMenu: () -> Unit,
) {
    fun ui(component: MaterialComponent): JPanel = panel {
        row(
            label = JLabel(ImageIcon(component.enlargedScreenshot.toImagePath()))
        ) {}
        row {
            browserLink("Guidelines", component.guidelinesUrl)
            browserLink("Spec", component.specUrl)
        }
        row {
            label("${component.name} implementation").bold()
        }
        var textArea: JBTextArea? = null
        row {
            textArea().applyToComponent {
                textArea = this
            }.text(component.codeSample)
                .resizableColumn()
        }
        row {
            button("Back") {
                navigateToMenu()
            }
            var copyCounter = 0
            var copiedLabel: JLabel? = null
            button("Copy") {
                copyToClipboard(textArea?.text ?: component.codeSample)
                copyCounter++
                copiedLabel?.isVisible = true
                if (copyCounter > 1) {
                    copiedLabel?.text = "Copied to clipboard! ($copyCounter)"
                    copiedLabel?.updateUI()
                }
            }
            label("Copied to clipboard!")
                .applyToComponent {
                    isVisible = false
                    copiedLabel = this
                }
        }
    }
}