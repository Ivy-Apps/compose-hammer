package com.github.ivyapps.composematerial3helper.actions

import com.intellij.codeInsight.intention.IntentionAction
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFile


class MaterialComponentsAction : IntentionAction {
    override fun getText() = "M3 Components"
    override fun getFamilyName() = "Compose Material3 UI"

    override fun isAvailable(project: Project, editor: Editor, file: PsiFile): Boolean {
        // Add your condition here
        return true
    }

    override fun invoke(project: Project, editor: Editor, file: PsiFile) {
        // Add your action logic here
    }

    override fun startInWriteAction() = true
}
