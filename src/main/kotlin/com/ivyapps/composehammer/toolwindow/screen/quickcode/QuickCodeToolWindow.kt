package com.ivyapps.composehammer.toolwindow.screen.quickcode

import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.ivyapps.composehammer.domain.data.Either
import com.ivyapps.composehammer.domain.quickcode.service.QuickCodeService
import com.ivyapps.composehammer.showErrorToast
import com.ivyapps.composehammer.toolwindow.screen.ToolWindowScreen

abstract class QuickCodeToolWindow<T>(
    pluginProject: Project,
) : ToolWindowScreen {
    protected val service = pluginProject.service<QuickCodeService>()

    protected abstract fun onRefreshUi(updatedItem: T)

    protected fun perform(
        action: QuickCodeService.() -> Either<String, T>
    ) {
        when (val res = service.action()) {
            is Either.Left -> showErrorToast(res.error)
            is Either.Right -> onRefreshUi(res.value)
        }
    }
}