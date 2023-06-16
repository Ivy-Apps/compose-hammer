package com.ivyapps.composehammer.domain.quickcode

import com.intellij.openapi.components.Service
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.ivyapps.composehammer.domain.data.quickcode.CodeGroup
import com.ivyapps.composehammer.domain.data.quickcode.CodeItem
import com.ivyapps.composehammer.persistence.QuickCodePersistence

@Service(Service.Level.PROJECT)
class QuickCodeService(project: Project) {
    private val persistence = project.service<QuickCodePersistence>()

    val groups: List<CodeGroup>
        get() = persistence.state.groups

    // region Group operations
    fun addGroup(name: String) {
        // TODO
    }

    fun moveGroupUp(group: CodeGroup) {

    }

    fun moveGroupDown(group: CodeGroup) {

    }

    fun deleteGroup(group: CodeGroup) {

    }
    // endregion

    // region CodeItem operations
    fun addItem(group: CodeGroup, item: CodeItem) {

    }

    fun moveItemUp(group: CodeGroup, item: CodeItem) {

    }

    fun moveItemDown(group: CodeGroup, item: CodeItem) {

    }

    fun deleteItem(group: CodeGroup, item: CodeItem) {

    }
    // endregion

}