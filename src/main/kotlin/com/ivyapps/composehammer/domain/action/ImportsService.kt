package com.ivyapps.composehammer.domain.action

import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.components.Service
import com.intellij.openapi.editor.Document
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFile

@Service(Service.Level.PROJECT)
class ImportsService(private val project: Project) {

    fun addMissingImports(
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
            val offset = text.findImportsOffset()
            WriteCommandAction.runWriteCommandAction(project) {
                // Insert the text at the cursor position
                val importStatement = "\nimport $importTarget"
                charsAdded = importStatement.length
                document.insertString(offset, importStatement)
            }
        }

        return charsAdded
    }
}

fun String.findImportsOffset(): Int {
    fun initKeyword() = mutableListOf('p', 'a', 'c', 'k', 'a', 'g', 'e', ' ')

    var index = 0
    var offset = 0

    // offset until the "package " keyword is fulfilled
    var keyword = initKeyword()
    while (index < this.length) {
        if (keyword.isEmpty()) break
        val char = this[index]
        if (char == keyword.first()) {
            keyword.removeAt(0)
        } else {
            keyword = initKeyword()
        }
        offset++
        index++
    }

    // offset to the end of the line
    while (index < this.length) {
        val char = this[index]
        if (char != '\n') {
            offset++
        } else {
            break
        }
        index++
    }
    offset++ // to leave one empty line

    return offset
}