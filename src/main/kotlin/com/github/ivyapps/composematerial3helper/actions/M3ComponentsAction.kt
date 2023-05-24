package com.github.ivyapps.composematerial3helper.actions

import com.github.ivyapps.composematerial3helper.data.MaterialComponent
import com.github.ivyapps.composematerial3helper.services.MaterialComponentsService
import com.intellij.codeInsight.intention.IntentionAction
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.components.service
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiFile
import com.intellij.psi.codeStyle.CodeStyleManager


class M3ComponentsAction : IntentionAction {
    override fun getText() = "M3 Components"
    override fun getFamilyName() = "Compose Material3 UI"

    override fun isAvailable(project: Project, editor: Editor, file: PsiFile): Boolean {
        // Add your condition here
        return true
    }

    override fun invoke(project: Project, editor: Editor, file: PsiFile) {
        val service = project.service<MaterialComponentsService>()

        editor.showM3ComponentsPopup(
            title = "Choose a component type",
            backItem = "Close menu",
            backItemLast = true,
            items = service.content.map { it.title },
            onBack = {
                it.closeOk(null)
            }
        ) { groupTitle ->
            val group = service.findGroupByTitle(groupTitle)
            editor.showM3ComponentsPopup(
                title = "Choose a component",
                backItem = "Go back",
                backItemLast = false,
                onBack = {
                    invoke(project, editor, file)
                },
                items = group.components.map { it.name }
            ) { componentName ->
                val component = service.findComponentByNameInGroup(group, componentName)
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
            addMissingImports(project, file, document, component.imports)
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
            val codeStyleManager = CodeStyleManager.getInstance(project)
            codeStyleManager.reformatText(psiFile, range.startOffset, range.endOffset)
        }
    }

    private fun addMissingImports(
        project: Project,
        file: PsiFile,
        document: Document,
        imports: List<String>
    ) {
        for (import in imports) {
            addImportStatementIfNecessary(project, file, document, import)
        }
    }

    private fun addImportStatementIfNecessary(
        project: Project,
        file: PsiFile,
        document: Document,
        importTarget: String
    ) {
        val text = file.originalFile.text
        if (importTarget !in text) {
            // add import
            val offset = text.findPackageEndOffset()
            WriteCommandAction.runWriteCommandAction(project) {
                // Insert the text at the cursor position
                val importStatement = "\nimport $importTarget"
                document.insertString(offset, importStatement)
            }
        }
    }

    private fun String.findPackageEndOffset(): Int {
        var offset = 0

        for (c in this) {
            if (c != '\n') {
                offset++
            } else {
                break
            }
        }
        offset++ // to leave one empty line

        return offset
    }

    override fun startInWriteAction() = true
}
