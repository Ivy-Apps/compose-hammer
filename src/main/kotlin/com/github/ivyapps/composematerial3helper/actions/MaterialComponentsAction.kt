package com.github.ivyapps.composematerial3helper.actions

import com.github.ivyapps.composematerial3helper.data.MaterialComponent
import com.github.ivyapps.composematerial3helper.services.MaterialComponentsService
import com.intellij.codeInsight.intention.IntentionAction
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.components.service
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.popup.JBPopupFactory
import com.intellij.psi.PsiFile


class MaterialComponentsAction : IntentionAction {
    override fun getText() = "M3 Components"
    override fun getFamilyName() = "Compose Material3 UI"

    override fun isAvailable(project: Project, editor: Editor, file: PsiFile): Boolean {
        // Add your condition here
        return true
    }

    override fun invoke(project: Project, editor: Editor, file: PsiFile) {
        val service = project.service<MaterialComponentsService>()
        val groups = service.content

        editor.showPopup(
            title = "Choose a component type",
            items = groups.map { it.title }
        ) { groupTitle ->
            val group = requireNotNull(groups.find { it.title == groupTitle }) {
                "Couldn't find '$groupTitle' group in $groups!!!"
            }
            editor.showPopup(
                title = "Choose a component",
                items = group.components.map { it.name }
            ) { componentName ->
                val component = requireNotNull(group.components.find { it.name == componentName }) {
                    "Couldn't find '$componentName' component in ${group.components}!!!"
                }
                applyCode(editor, project, file, component)
            }
        }
    }

    private fun applyCode(
        editor: Editor,
        project: Project,
        file: PsiFile,
        component: MaterialComponent
    ) {
        val componentCode = component.defaultImplementation

        // Obtain the document instance from the editor
        val document = editor.document

        // Obtain the CaretModel from the editor. The CaretModel represents the text cursor
        val caretModel = editor.caretModel

        // Get the offset of the current cursor position
        val offset = caretModel.offset

        // Run a write action because document modifications should be run in write actions
        WriteCommandAction.runWriteCommandAction(project) {
            // Insert the text at the cursor position
            document.insertString(offset, componentCode)
        }

        // Optionally, you can move the cursor to the end of the inserted text
        caretModel.moveToOffset(offset + componentCode.length)
    }

    private fun Editor.showPopup(
        title: String,
        items: List<String>,
        onChosen: (String) -> Unit
    ) {
        val popup = JBPopupFactory.getInstance()
            .createPopupChooserBuilder(items)
            .setTitle(title)
            .setItemChosenCallback(onChosen)
            .createPopup()
        popup.showInBestPositionFor(this)
    }

    override fun startInWriteAction() = true
}
