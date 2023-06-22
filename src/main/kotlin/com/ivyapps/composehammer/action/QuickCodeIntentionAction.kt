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
import com.ivyapps.composehammer.domain.data.quickcode.CodeItem
import com.ivyapps.composehammer.domain.quickcode.service.QuickCodeService
import javax.swing.Icon


class QuickCodeIntentionAction : IntentionAction, HighPriorityAction, Iconable {
    override fun getText() = "Quick Code"
    override fun getFamilyName() = "Quick Code"
    override fun getIcon(flags: Int): Icon = IconLoader.getIcon(
        "/quick_code.svg",
        this@QuickCodeIntentionAction::class.java.classLoader
    )

    override fun isAvailable(project: Project, editor: Editor, file: PsiFile): Boolean {
        val quickCodeService = project.service<QuickCodeService>()
        return quickCodeService.hasDefinedCustomTemplates()
    }

    override fun invoke(project: Project, editor: Editor, file: PsiFile) {
        val quickCodeService = project.service<QuickCodeService>()

        editor.showOptionsPopup(
            title = "Choose an option",
            backItem = "❌ Close menu",
            backItemLast = true,
            items = quickCodeService.enabledGroups.map { it.name },
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
                val codeItem = quickCodeService.findCodeItemByName(group, componentName)
                insertCodeItem(project, editor, file, codeItem)
            }
        }
    }

    private fun insertCodeItem(
        project: Project,
        editor: Editor,
        file: PsiFile,
        codeItem: CodeItem,
    ) {
        val insertService = project.service<InsertCodeService>()

        if (codeItem.variables.isNotEmpty()) {
            QuickCodeVarsDialog(
                pluginProject = project,
                editor = editor,
                file = file,
                codeItem = codeItem,
            ).show()
        } else {
            insertService.addCode(editor, file, codeItem)
        }

    }

    override fun startInWriteAction() = true
}
