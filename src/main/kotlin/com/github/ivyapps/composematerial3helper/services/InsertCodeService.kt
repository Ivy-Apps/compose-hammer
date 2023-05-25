package com.github.ivyapps.composematerial3helper.services

import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.components.Service
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiFile
import com.intellij.psi.codeStyle.CodeStyleManager
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtPsiFactory
import org.jetbrains.uast.*


@Service(Service.Level.PROJECT)
class InsertCodeService(private val project: Project) {

    fun insertCodeAtCursor(
            psiFile: PsiFile,
            editor: Editor,
            newCode: String
    ) {
        if (psiFile !is KtFile) {
            return
        }

        val document = editor.document
        val caretModel = editor.caretModel
        val offset = caretModel.offset

        val ktPsiFactory = KtPsiFactory(project)
        val newElement = ktPsiFactory.createFunction(newCode)

        WriteCommandAction.runWriteCommandAction(project) {
            // Inserting the text into the document
            document.insertString(offset, newElement.text)

            // Committing the document to sync with the PSI structure
            PsiDocumentManager.getInstance(project).commitDocument(document)

            // Formatting the code after the change
            val file = PsiDocumentManager.getInstance(project).getPsiFile(document)
            if (file != null) {
                CodeStyleManager.getInstance(project).reformat(file)
            }
        }
    }


}