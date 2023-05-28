package com.ivyapps.composematerial3helper.toolwindow

import com.intellij.icons.AllIcons
import com.intellij.ui.dsl.builder.Panel


fun Panel.altEnterTip(
    indent: Boolean,
) {
    group(indent = indent) {
        row {
            icon(AllIcons.Actions.QuickfixOffBulb)
            label("Did you know?")
        }
        row {
            label(
                """
                You can quickly add Compose components and more by pressing 
                "⌘ Cmd + ⤶ Enter" on Mac or "CTRL+ENTER" on Windows/Linux.
                Try it out! It's worth it.
            """.trimIndent()
            )
        }
    }

}