package com.ivyapps.composematerial3helper.toolwindow

import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.dsl.builder.panel
import com.ivyapps.composematerial3helper.domain.data.MaterialComponent
import com.ivyapps.composematerial3helper.domain.formatText
import com.ivyapps.composematerial3helper.domain.generateImportsCode
import com.ivyapps.composematerial3helper.util.image

class ComponentDetailsUi(
    private val navigateToMenu: () -> Unit,
) {
    fun ui(component: MaterialComponent): DialogPanel = panel {
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
            unresolvedImportsTip(indent = false)
            altEnterTip(indent = false)
        }
    }

}