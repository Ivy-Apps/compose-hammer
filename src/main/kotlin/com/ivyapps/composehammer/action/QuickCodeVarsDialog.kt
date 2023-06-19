package com.ivyapps.composehammer.action

import com.intellij.openapi.components.service
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.openapi.ui.ValidationInfo
import com.intellij.psi.PsiFile
import com.intellij.ui.ScrollPaneFactory
import com.intellij.ui.dsl.builder.panel
import com.ivyapps.composehammer.domain.action.InsertCodeService
import com.ivyapps.composehammer.domain.data.quickcode.CodeItem
import com.ivyapps.composehammer.domain.data.quickcode.QCVariable
import com.ivyapps.composehammer.domain.quickcode.compiler.QuickCodeCompiler
import com.ivyapps.composehammer.domain.quickcode.compiler.data.QCVariableValue
import javax.swing.JComponent

class QuickCodeVarsDialog(
    private val pluginProject: Project,
    private val editor: Editor,
    private val file: PsiFile,
    private val codeItem: CodeItem,
) : DialogWrapper(true) {
    init {
        init()
        title = codeItem.name
    }

    private lateinit var inputs: List<Pair<String, () -> QCVariableValue>>

    override fun createCenterPanel(): JComponent {
        return ScrollPaneFactory.createScrollPane(ui())
    }

    private fun ui() = panel {
        group(indent = true) {
            inputs = codeItem.variables.mapNotNull { variable ->
                var res: Pair<String, () -> QCVariableValue>? = null
                row {
                    val variableName = variable.name
                    res = when (variable) {
                        is QCVariable.Bool -> {
                            val checkbox = checkBox(variableName)
                            variableName to {
                                QCVariableValue.Bool(checkbox.component.isSelected)
                            }
                        }

                        is QCVariable.Str -> {
                            text(variableName)
                            val component = textField().component
                            variableName to {
                                QCVariableValue.Str(component.text)
                            }
                        }
                    }
                }
                res
            }
        }
    }

    override fun doValidate(): ValidationInfo? {
        insetCode(inputs)
        return null
    }

    private fun insetCode(inputs: List<Pair<String, () -> QCVariableValue>>) {
        val variablesValues = inputs.associate { (varName, getValue) ->
            varName to getValue()
        }
        val compiler = QuickCodeCompiler()
        val evaluatedTemplate = compiler.execute(
            codeTemplate = codeItem.codeTemplate,
            vars = variablesValues,
        )
        pluginProject.service<InsertCodeService>()
            .addCode(
                editor, file, codeItem.copy(
                    codeTemplate = evaluatedTemplate
                )
            )
    }
}
