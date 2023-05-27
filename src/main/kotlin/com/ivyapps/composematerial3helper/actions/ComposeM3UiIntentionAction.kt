package com.ivyapps.composematerial3helper.actions

import com.ivyapps.composematerial3helper.domain.MaterialComponentsService
import com.ivyapps.composematerial3helper.domain.RecognizerService
import com.ivyapps.composematerial3helper.domain.legacy.InsertComponentService
import com.intellij.codeInsight.intention.HighPriorityAction
import com.intellij.codeInsight.intention.IntentionAction
import com.intellij.openapi.components.service
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFile


class ComposeM3UiIntentionAction : IntentionAction, HighPriorityAction {
    override fun getText() = "Compose Material3 UI builder"
    override fun getFamilyName() = "Compose Material3 UI"

    override fun isAvailable(project: Project, editor: Editor, file: PsiFile): Boolean {
        val service = project.service<RecognizerService>()
        return service.isComposeFile(editor, file)
    }

    override fun invoke(project: Project, editor: Editor, file: PsiFile) {
        val componentsService = project.service<MaterialComponentsService>()
        val insertService = project.service<InsertComponentService>()

        editor.showM3ComponentsPopup(
                title = "Choose a component type",
                backItem = "(X) Close menu",
                backItemLast = true,
                items = componentsService.content.map { it.title },
                onBack = {
                    it.closeOk(null)
                }
        ) { groupTitle ->
            val group = componentsService.findGroupByTitle(groupTitle)
            editor.showM3ComponentsPopup(
                    title = "Choose a component",
                    backItem = "(<-) Go back",
                    backItemLast = false,
                    onBack = {
                        invoke(project, editor, file)
                    },
                    items = group.components.map { it.name }
            ) { componentName ->
                val component = componentsService.findComponentByNameInGroup(group, componentName)
                insertService.addCode(editor, file, component)
            }
        }
    }

    override fun startInWriteAction() = true
}
