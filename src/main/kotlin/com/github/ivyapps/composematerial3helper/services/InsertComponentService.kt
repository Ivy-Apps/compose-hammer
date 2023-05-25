package com.github.ivyapps.composematerial3helper.services

import com.github.ivyapps.composematerial3helper.data.MaterialComponent
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.service
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFile

// TODO: Doesn't work!
@Service(Service.Level.PROJECT)
class InsertComponentService(private val project: Project) {

    fun insertAtCursor(
            psiFile: PsiFile,
            editor: Editor,
            component: MaterialComponent,
    ) {
        val insertService = project.service<InsertCodeService>()
        insertService.insertCodeAtCursor(psiFile, editor, component.fullyQualifiedCode())
    }

    private fun MaterialComponent.fullyQualifiedCode(): String {
        var fullyQualifiedCode = defaultImplementation

        imports.map {
            it.split(".").last() to it
        }.forEach { (thing, fullyQualified) ->
            fullyQualifiedCode = fullyQualifiedCode.replace(thing, fullyQualified)
        }

        return fullyQualifiedCode
    }

}