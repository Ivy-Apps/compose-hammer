package com.github.ivyapps.composematerial3helper.actions

import com.github.ivyapps.composematerial3helper.data.MaterialComponent
import com.github.ivyapps.composematerial3helper.services.MaterialComponentsService
import com.intellij.codeInsight.intention.IntentionAction
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.components.service
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.popup.JBPopup
import com.intellij.openapi.ui.popup.JBPopupFactory
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiFile
import com.intellij.psi.codeStyle.CodeStyleManager


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
            backItem = "Close menu",
            backItemLast = true,
            items = groups.map { it.title },
            onBack = {
                it.closeOk(null)
            }
        ) { groupTitle ->
            val group = requireNotNull(groups.find { it.title == groupTitle }) {
                "Couldn't find '$groupTitle' group in $groups!!!"
            }
            editor.showPopup(
                title = "Choose a component",
                backItem = "Go back",
                backItemLast = false,
                onBack = {
                    invoke(project, editor, file)
                },
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

            reformatCode(project, document, offset, componentCode)
        }

        // Optionally, you can move the cursor to the end of the inserted text
        caretModel.moveToOffset(offset + componentCode.length)
    }

    private fun reformatCode(
        project: Project,
        document: Document,
        offset: Int,
        componentCode: String,
    ) {
        // Reformat the code to fix indentation
        val psiFile = PsiDocumentManager.getInstance(project).getPsiFile(document)
        val startOffset = offset
        val endOffset = offset + componentCode.length
        val range = TextRange(startOffset, endOffset)

        if (psiFile != null) {
            CodeStyleManager.getInstance(project).reformatText(psiFile, range.startOffset, range.endOffset)
        }
    }

    private fun Editor.showPopup(
        title: String,
        backItem: String,
        backItemLast: Boolean,
        items: List<String>,
        onBack: (JBPopup) -> Unit,
        onChosen: (String) -> Unit
    ) {
        var popup: JBPopup? = null
        popup = JBPopupFactory.getInstance()
            .createPopupChooserBuilder(
                if (backItemLast) {
                    items + backItem
                } else {
                    listOf(backItem) + items
                }
            )
            .setTitle(title)
            .setItemChosenCallback {
                if (it != backItem) {
                    onChosen(it)
                } else {
                    onBack(popup!!)
                }
            }
            .createPopup()
        popup.showInBestPositionFor(this)
    }

    override fun startInWriteAction() = true
}
