package com.ivyapps.composehammer.action

import com.intellij.codeInsight.intention.HighPriorityAction
import com.intellij.codeInsight.intention.IntentionAction
import com.intellij.openapi.components.service
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.IconLoader
import com.intellij.openapi.util.Iconable
import com.intellij.psi.PsiFile
import com.ivyapps.composehammer.action.component.showOptionsPopup
import com.ivyapps.composehammer.domain.MaterialComponentsService
import com.ivyapps.composehammer.domain.core.InsertComponentService
import com.ivyapps.composehammer.persistence.CustomCodePersistence
import javax.swing.Icon


class QuickCodeIntentionAction : IntentionAction, HighPriorityAction, Iconable {
    override fun getText() = "Quick Code"
    override fun getFamilyName() = "Quick Code"
    override fun getIcon(flags: Int): Icon = IconLoader.getIcon(
        "/quick_code.svg",
        this@QuickCodeIntentionAction::class.java.classLoader
    )

    override fun isAvailable(project: Project, editor: Editor, file: PsiFile): Boolean {
        val service = project.service<CustomCodePersistence>()
        return service.state.groups.isNotEmpty()
    }

    override fun invoke(project: Project, editor: Editor, file: PsiFile) {
        val componentsService = project.service<MaterialComponentsService>()
        val insertService = project.service<InsertComponentService>()

        editor.showOptionsPopup(
            title = "Choose an option",
            backItem = "❌ Close menu",
            backItemLast = true,
            items = componentsService.content.map { it.shortTitle ?: it.title },
            onBack = {
                it.closeOk(null)
            }
        ) { groupTitle ->
            val group = componentsService.findGroupByTitle(groupTitle)
            editor.showOptionsPopup(
                title = groupTitle,
                backItem = "⬅ Go back",
                backItemLast = true,
                onBack = {
                    invoke(project, editor, file)
                },
                items = group.components.map { it.shortName ?: it.name }
            ) { componentName ->
                val component = componentsService.findComponentByNameInGroup(group, componentName)
                insertService.addCode(editor, file, component)
            }
        }
    }

    override fun startInWriteAction() = true
}
