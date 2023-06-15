package com.ivyapps.composehammer.toolwindow.component

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
                "⌥ Option + ⤶ Enter" on Mac or "ALT+ENTER" on Windows/Linux.
                Try it out! It's worth it.
            """.trimIndent()
            )
        }
    }

}