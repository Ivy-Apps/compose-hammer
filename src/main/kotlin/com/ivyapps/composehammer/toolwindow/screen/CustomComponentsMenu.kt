package com.ivyapps.composehammer.toolwindow.screen

import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.ui.dsl.builder.panel
import com.ivyapps.composehammer.domain.data.custom.ComponentGroup
import com.ivyapps.composehammer.persistence.CustomComponentsPersistence

class CustomComponentsMenu(
    project: Project,
    private val navigateToMainMenu: () -> Unit,
    private val refreshUi: () -> Unit,
) {
    private val persistence = project.service<CustomComponentsPersistence>()

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
                    ComponentGroup(
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