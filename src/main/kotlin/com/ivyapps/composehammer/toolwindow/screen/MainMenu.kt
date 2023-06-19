package com.ivyapps.composehammer.toolwindow.screen

import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.dsl.builder.Panel
import com.intellij.ui.dsl.builder.panel
import com.ivyapps.composehammer.addOnClickListener
import com.ivyapps.composehammer.domain.MaterialComponentsService
import com.ivyapps.composehammer.domain.data.material3.MaterialComponent
import com.ivyapps.composehammer.domain.ui.formatText
import com.ivyapps.composehammer.toolwindow.component.altEnterTip
import com.ivyapps.composehammer.toolwindow.component.image
import com.ivyapps.composehammer.toolwindow.component.unresolvedImportsTip

class MainMenu(
    private val service: MaterialComponentsService,
    private val navigateToMaterialComponent: (MaterialComponent) -> Unit,
    private val navigateToCustomCodeMenu: () -> Unit,
) : ToolWindowScreen {
    override val ui: DialogPanel = panel {
        customSection()
//        contentDebugInfo(service)
        for (group in service.content.filter { it.showInToolWindow }) {
            collapsibleGroup(
                title = group.title,
                indent = true
            ) {
                group.components.filter {
                    it.showInToolWindow
                }.forEach { component ->
                    componentUi(component)
                }
            }.apply {
                expanded = false
            }
        }
        altEnterTip(indent = true)
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

    private fun Panel.customSection() {
        group(indent = true) {
            row {
                text("Custom code snippets").bold()
                    .component.addOnClickListener(navigateToCustomCodeMenu)
            }
            row {
                label(
                    "Create and manage your custom components\n" +
                            "for the \"⚡ Quick Code\" alt+enter action."
                ).component.addOnClickListener(navigateToCustomCodeMenu)
            }
            row {
                button("⚡ Quick code") {
                    navigateToCustomCodeMenu()
                }.comment("Coding done fast!")
            }
        }
    }
}