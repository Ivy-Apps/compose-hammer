package com.ivyapps.composematerial3helper.domain.legacy

import com.intellij.openapi.components.Service
import com.intellij.openapi.editor.Document
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.codeStyle.CodeStyleManager

@Service(Service.Level.PROJECT)
class ReformatCodeService(private val project: Project) {

    fun reformatCode(
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

}