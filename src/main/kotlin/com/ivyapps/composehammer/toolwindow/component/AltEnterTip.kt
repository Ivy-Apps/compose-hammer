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
            text(
                """
                You can quickly add Compose components and more by pressing<br>
                "⌥ Option + ⤶ Enter" on Mac or "ALT+ENTER" on Windows/Linux.<br>
                Try it out! It's worth it.
            """.trimIndent(),
            )
        }
    }
}