package com.ivyapps.composehammer.toolwindow

import com.intellij.ui.dsl.builder.Panel
import com.ivyapps.composehammer.domain.MaterialComponentsService

fun Panel.contentDebugInfo(
    service: MaterialComponentsService,
) {
    codeArea(
        title = "Toolwindow",
        code = service.content.filter { it.showInToolWindow }
            .joinToString(separator = "") { group ->
                buildString {
                    append("- ${group.title}\n")
                    group.components.forEach { component ->
                        append("\t- [${component.name}](${component.specUrl})\n")
                    }
                }
            },
        tip = service.content.filter { it.showInToolWindow }
            .flatMap { it.components }.size.toString()
    )
    codeArea(
        title = "Quick action",
        code = service.content.filter { !it.showInToolWindow }
            .joinToString(separator = "") { group ->
                buildString {
                    append("- ${group.title}\n")
                    group.components.forEach { component ->
                        append("\t- ${component.name}\n")
                    }
                }
            },
        tip = service.content.filter { !it.showInToolWindow }
            .flatMap { it.components }.size.toString()
    )
}