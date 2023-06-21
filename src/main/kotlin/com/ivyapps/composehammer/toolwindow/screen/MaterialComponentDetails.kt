package com.ivyapps.composehammer.toolwindow.screen

import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.dsl.builder.panel
import com.ivyapps.composehammer.domain.data.material3.MaterialComponent
import com.ivyapps.composehammer.domain.ui.formatText
import com.ivyapps.composehammer.domain.ui.generateImportsCode
import com.ivyapps.composehammer.toolwindow.component.*

class MaterialComponentDetails(
    private val component: MaterialComponent,
    private val navigateToMenu: () -> Unit,
) : ToolWindowScreen {
    override val ui: DialogPanel = panel {
        group(indent = true) {
            row {
                button("Back") {
                    navigateToMenu()
                }
                label(component.name).bold()
            }
            image(component.detailsScreenshot)
            row {
                browserLink("Spec", component.specUrl)
                browserLink("Guidelines", component.guidelinesUrl)
                browserLink("Docs", component.docsUrl)
            }
            if (component.description != null) {
                row {
                    label(component.description.formatText())
                }
            }
            val importsCode = generateImportsCode(component.imports)
            if (importsCode != null) {
                codeArea(
                    title = null,
                    code = importsCode
                )
            }
            codeArea(
                title = "Code",
                code = component.defaultImplementation,
                tip = component.defaultImplementationTip,
            )
            if (component.customImplementation != null) {
                codeArea(
                    title = "Customization",
                    code = component.customImplementation,
                    tip = component.customImplementationTip,
                )
            }
            reviewAndTelegramPrompt(indent = false)
            unresolvedImportsTip(indent = false)
            altEnterTip(indent = false)
        }
    }

}