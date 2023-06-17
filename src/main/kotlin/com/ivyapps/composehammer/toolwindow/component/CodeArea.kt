package com.ivyapps.composehammer.toolwindow.component

import com.intellij.ui.components.JBTextArea
import com.intellij.ui.dsl.builder.Panel
import com.intellij.ui.dsl.builder.Row
import com.intellij.ui.dsl.gridLayout.HorizontalAlign
import com.ivyapps.composehammer.copyToClipboard
import java.awt.Dimension
import javax.swing.JLabel

fun Panel.codeArea(
    title: String?,
    code: String,
    description: String? = null,
    tip: String? = null,
    editable: Boolean = false,
    hasCopy: Boolean = true,
    minLines: Int? = null,
): JBTextArea {
    var inputField: JBTextArea? = null
    if (title != null) {
        row {
            label(title).bold()
        }
    }
    if (description != null) {
        row {
            label(description)
        }
    }
    row {
        textArea().applyToComponent {
            text = code.let {
                if (minLines != null && it.isBlank()) {
                    "\n".repeat(minLines)
                } else it
            }
            isEditable = editable
            size = Dimension(Int.MAX_VALUE, height)
            autoscrolls = true
            updateUI()
        }.also {
            inputField = it.component
        }.horizontalAlign(HorizontalAlign.FILL) // TODO: Fix this FILL deprecation
            .comment(tip)
    }
    if (hasCopy) {
        row {
            copyButton(code)
        }
    }
    return inputField!!
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