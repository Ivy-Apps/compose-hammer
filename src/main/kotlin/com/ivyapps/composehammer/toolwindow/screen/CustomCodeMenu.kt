package com.ivyapps.composehammer.toolwindow.screen

import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.ui.dsl.builder.panel
import com.ivyapps.composehammer.domain.data.custom.CodeGroup
import com.ivyapps.composehammer.persistence.CustomCodePersistence

class CustomCodeMenu(
    project: Project,
    private val navigateToMainMenu: () -> Unit,
    private val refreshUi: () -> Unit,
) {
    private val persistence = project.service<CustomCodePersistence>()

    fun ui() = panel {
        row {
            button("Back") {
                navigateToMainMenu()
            }
        }
        row {
            val field = textField()
            button("Add") {
                persistence.state.groups.add(
                    CodeGroup(
                        name = field.component.text,
                        order = 0.0,
                        components = emptyList()
                    )
                )
                refreshUi()
            }
        }

        persistence.state.groups.forEach { group ->
            collapsibleGroup(
                title = group.name,
                indent = true
            ) {

            }
        }
    }
}