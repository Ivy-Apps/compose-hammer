package com.ivyapps.composehammer.domain.core

import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.components.Service
import com.intellij.openapi.editor.Document
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFile

@Service(Service.Level.PROJECT)
class ImportsService(private val project: Project) {

    fun addMissingImports(
            project: Project,
            file: PsiFile,
            document: Document,
            imports: List<String>
    ): Int {
        var charsAdded = 0
        for (import in imports) {
            charsAdded += addImportStatementIfNecessary(project, file, document, import)
        }
        return charsAdded
    }

    private fun addImportStatementIfNecessary(
            project: Project,
            file: PsiFile,
            document: Document,
            importTarget: String
    ): Int {
        var charsAdded = 0

        val text = file.originalFile.text
        if (importTarget !in text) {
            // add import
            val offset = text.findPackageEndOffset()
            WriteCommandAction.runWriteCommandAction(project) {
                // Insert the text at the cursor position
                val importStatement = "\nimport $importTarget"
                charsAdded = importStatement.length
                document.insertString(offset, importStatement)
            }
        }

        return charsAdded
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

}