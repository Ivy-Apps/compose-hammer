package com.ivyapps.composehammer.toolwindow.screen

import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.dsl.builder.Panel
import com.intellij.ui.dsl.builder.panel
import com.ivyapps.composehammer.domain.MaterialComponentsService
import com.ivyapps.composehammer.domain.data.material3.MaterialComponent
import com.ivyapps.composehammer.domain.ui.formatText
import com.ivyapps.composehammer.toolwindow.component.altEnterTip
import com.ivyapps.composehammer.toolwindow.component.unresolvedImportsTip
import com.ivyapps.composehammer.addOnClickListener
import com.ivyapps.composehammer.toolwindow.component.image

class MainMenu(
    private val service: MaterialComponentsService,
    private val navigateToMaterialComponent: (MaterialComponent) -> Unit,
    private val navigateToCustomComponentsMenu: () -> Unit,
) {
    fun ui(): DialogPanel = panel {
        row {
            button("Custom components") {
                navigateToCustomComponentsMenu()
            }
        }
        altEnterTip(indent = true)
//        contentDebugInfo(service)
        for (group in service.content.filter { it.showInToolWindow }) {
            collapsibleGroup(
                title = group.title,
                indent = true
            ) {
                group.components.forEach { component ->
                    componentUi(component)
                }
            }.apply {
                expanded = false
            }
        }
        unresolvedImportsTip(indent = true)
    }

    private fun Panel.componentUi(component: MaterialComponent) {
        group(indent = false) {
            row {
                text(component.name).applyToComponent {
                    addOnClickListener {
                        navigateToMaterialComponent(component)
                    }
                }.bold()
                button("View") {
                    navigateToMaterialComponent(component)
                }
            }
            image(
                imageFileName = component.menuScreenshot,
                onClick = {
                    navigateToMaterialComponent(component)
                }
            )
            if (component.description != null) {
                row {
                    label(text = component.description.formatText())
                }
            }
        }
    }
}