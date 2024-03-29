package com.ivyapps.composehammer.domain.action

import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.service
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiFile
import com.ivyapps.composehammer.domain.data.Code

@Service(Service.Level.PROJECT)
class InsertCodeService(private val project: Project) {
    private val reformatService by lazy { project.service<ReformatCodeService>() }
    private val importsService by lazy { project.service<ImportsService>() }

    fun addCode(
        editor: Editor,
        file: PsiFile,
        code: Code
    ) {
        val componentCode = code.code

        // Obtain the CaretModel from the editor. The CaretModel represents the text cursor
        val caretModel = editor.caretModel

        // Get the offset of the current cursor position
        val offset = caretModel.offset
        var finalOffset = offset

        // Run a write action because document modifications should be run in write actions
        WriteCommandAction.runWriteCommandAction(project) {
            // Obtain the document instance from the editor
            val document = editor.document

            // PsiDocumentManager instance
            val psiDocumentManager = PsiDocumentManager.getInstance(project)

            // commit the document
            psiDocumentManager.commitDocument(document)

            // Insert the text at the cursor position
            document.insertString(offset, componentCode)
            reformatService.reformatCode(project, document, offset, componentCode)
            val charsAdded = importsService.addMissingImports(file, document, code.imports)
            finalOffset = offset + charsAdded + componentCode.length

            // commit the document after making changes
            psiDocumentManager.commitDocument(document)
        }

        // Optionally, you can move the cursor to the end of the inserted text
        caretModel.moveToOffset(finalOffset)
    }
}
