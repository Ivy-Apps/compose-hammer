package com.github.ivyapps.composematerial3helper.ui.toolwindow

import com.github.ivyapps.composematerial3helper.addOnClickListener
import com.github.ivyapps.composematerial3helper.data.MaterialComponent
import com.github.ivyapps.composematerial3helper.services.MaterialComponentsService
import com.github.ivyapps.composematerial3helper.ui.common.image
import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.dsl.builder.Panel
import com.intellij.ui.dsl.builder.panel

class ComponentsMenuUi(
    private val service: MaterialComponentsService,
    private val navigateToComponent: (MaterialComponent) -> Unit,
) {
    fun ui(): DialogPanel = panel {
        for (group in service.content) {
            collapsibleGroup(
                title = group.title,
                indent = true
            ) {
                group.components.forEach { component ->
                    componentUi(component)
                }
            }.apply {
                expanded = true
            }
        }
    }

    private fun Panel.componentUi(component: MaterialComponent) {
        group(indent = false) {
            image(
                imageFileName = component.menuScreenshot,
                onClick = {
                    navigateToComponent(component)
                }
            )
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
            if (component.description != null) {
                row {
                    label(component.description)
                }
            }
        }
    }
}