package com.github.ivyapps.composematerial3helper.ui.toolwindow

import com.github.ivyapps.composematerial3helper.addOnClickListener
import com.github.ivyapps.composematerial3helper.data.MaterialComponent
import com.github.ivyapps.composematerial3helper.services.MaterialComponentsService
import com.github.ivyapps.composematerial3helper.toImagePath
import com.intellij.openapi.wm.ToolWindow
import com.intellij.ui.dsl.builder.Panel
import com.intellij.ui.dsl.builder.panel
import javax.swing.ImageIcon
import javax.swing.JLabel

class ComponentsMenuUi(
    private val toolWindow: ToolWindow,
    private val service: MaterialComponentsService,
    private val navigateToComponent: (MaterialComponent) -> Unit,
) {
    fun ui() = panel {
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
            row(
                label = JLabel(ImageIcon(component.screenshot.toImagePath())).apply {
                    addOnClickListener {
                        navigateToComponent(component)
                    }
                }
            ) {}
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