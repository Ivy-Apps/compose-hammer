package com.github.ivyapps.composematerial3helper.services

import com.intellij.openapi.components.Service
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFile

@Service(Service.Level.PROJECT)
class RecognizerService(private val project: Project) {

    fun isComposeFile(editor: Editor, file: PsiFile): Boolean {
        return file.text.contains("@Composable")
    }
}