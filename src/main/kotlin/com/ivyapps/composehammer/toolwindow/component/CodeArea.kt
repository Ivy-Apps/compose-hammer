package com.ivyapps.composehammer.toolwindow.component

import com.intellij.ui.dsl.builder.Panel
import com.intellij.ui.dsl.builder.Row
import com.intellij.ui.dsl.gridLayout.HorizontalAlign
import com.ivyapps.composehammer.util.copyToClipboard
import java.awt.Dimension
import javax.swing.JLabel

fun Panel.codeArea(
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