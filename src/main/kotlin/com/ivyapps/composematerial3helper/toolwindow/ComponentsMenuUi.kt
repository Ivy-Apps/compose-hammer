package com.ivyapps.composematerial3helper.toolwindow

import com.ivyapps.composematerial3helper.util.addOnClickListener
import com.ivyapps.composematerial3helper.domain.data.MaterialComponent
import com.ivyapps.composematerial3helper.domain.MaterialComponentsService
import com.ivyapps.composematerial3helper.domain.formatText
import com.ivyapps.composematerial3helper.util.image
import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.dsl.builder.Panel
import com.intellij.ui.dsl.builder.panel

class ComponentsMenuUi(
    private val service: MaterialComponentsService,
    private val navigateToComponent: (MaterialComponent) -> Unit,
) {
    fun ui(): DialogPanel = panel {
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
    }

    private fun Panel.componentUi(component: MaterialComponent) {
        group(indent = false) {
            row {
                text(component.name).applyToComponent {
                    addOnClickListener {
                        navigateToComponent(component)
                    }
                }.bold()
                button("View") {
                    navigateToComponent(component)
                }
            }
            image(
                imageFileName = component.menuScreenshot,
                onClick = {
                    navigateToComponent(component)
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