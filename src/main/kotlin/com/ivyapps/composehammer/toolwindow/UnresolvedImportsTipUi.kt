package com.ivyapps.composehammer.toolwindow

import com.intellij.icons.AllIcons
import com.intellij.ui.dsl.builder.Panel

fun Panel.unresolvedImportsTip(
    indent: Boolean,
) {
    group(indent = indent) {
        row {
            icon(AllIcons.Actions.QuickfixBulb)
            label("Unresolved imports!? ")
        }
        row {
            label("Add the")
            browserLink(
                "Material3 Gradle dependencies",
                "https://developer.android.com/jetpack/androidx/releases/compose-material3#declaring_dependencies"
            )
        }
        codeArea(
            title = "Material3 dependencies",
            code = """
                // Make sure to use the latest material3 version!
                implementation("androidx.compose.material3:material3:1.1.0")
                implementation("androidx.compose.material3:material3-window-size-class:1.1.0")
            """.trimIndent(),
            tip = "Use the latest version from the link above."
        )
        row {
            icon(AllIcons.General.Warning)
            label("Make sure to use the latest material3 version!")
        }
    }
}