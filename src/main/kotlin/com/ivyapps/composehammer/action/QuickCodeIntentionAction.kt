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
import com.ivyapps.composehammer.domain.action.InsertCodeService
import com.ivyapps.composehammer.domain.quickcode.QuickCodeService
import com.ivyapps.composehammer.persistence.QuickCodePersistence
import javax.swing.Icon


class QuickCodeIntentionAction : IntentionAction, HighPriorityAction, Iconable {
    override fun getText() = "Quick Code"
    override fun getFamilyName() = "Quick Code"
    override fun getIcon(flags: Int): Icon = IconLoader.getIcon(
        "/quick_code.svg",
        this@QuickCodeIntentionAction::class.java.classLoader
    )

    override fun isAvailable(project: Project, editor: Editor, file: PsiFile): Boolean {
        val service = project.service<QuickCodePersistence>()
        return service.state.configuration.groups.isNotEmpty()
    }

    override fun invoke(project: Project, editor: Editor, file: PsiFile) {
        val quickCodeService = project.service<QuickCodeService>()
        val insertService = project.service<InsertCodeService>()

        editor.showOptionsPopup(
            title = "Choose an option",
            backItem = "❌ Close menu",
            backItemLast = true,
            items = quickCodeService.groups.map { it.name },
            onBack = {
                it.closeOk(null)
            }
        ) { groupName ->
            val group = quickCodeService.findGroupByName(groupName)
            editor.showOptionsPopup(
                title = groupName,
                backItem = "⬅ Go back",
                backItemLast = true,
                onBack = {
                    invoke(project, editor, file)
                },
                items = group.codeItems.map { it.name }
            ) { componentName ->
                val component = quickCodeService.findCodeItemByName(group, componentName)
                insertService.addCode(editor, file, component)
            }
        }
    }

    override fun startInWriteAction() = true
}
