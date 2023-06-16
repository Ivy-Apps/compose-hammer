package com.ivyapps.composehammer.domain.quickcode

import com.intellij.openapi.components.Service
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.ivyapps.composehammer.domain.data.quickcode.CodeGroup
import com.ivyapps.composehammer.domain.data.quickcode.CodeItem
import com.ivyapps.composehammer.persistence.QuickCodePersistence
import com.ivyapps.composehammer.randomBetween

@Service(Service.Level.PROJECT)
class QuickCodeService(project: Project) {
    private val persistence = project.service<QuickCodePersistence>()

    val groups: List<CodeGroup>
        get() = persistence.state.groups.sortedBy { it.order }

    // region Group operations
    fun addGroup(name: String): Boolean {
        val finalName = name.trim().takeIf { it.isNotBlank() } ?: return false
        persistence.state.groups.add(
            CodeGroup(
                name = finalName,
                order = groups.lastOrNull()?.order?.plus(2) ?: 1.0,
                codeItems = emptyList()
            )
        )
        return true
    }

    fun moveGroupUp(targetGroup: CodeGroup): Boolean {
        val sortedGroups = groups
        for ((i, group) in sortedGroups.withIndex()) {
            if (targetGroup.name == group.name) {
                val next = sortedGroups.getOrNull(i + 1)
                    ?: return false // it's already first!
                val prev = sortedGroups.getOrNull(i - 1)
                moveGroup(
                    targetGroup = targetGroup,
                    newOrder = randomBetween(
                        min = prev?.order ?: next.order.minus(2),
                        max = next.order
                    )
                )
                return true
            }
        }
        return false
    }

    fun moveGroupDown(targetGroup: CodeGroup): Boolean {
        val sortedGroups = groups
        for ((i, group) in sortedGroups.withIndex()) {
            if (targetGroup.name == group.name) {
                val prev = sortedGroups.getOrNull(i - 1)
                    ?: return false // it's already last!
                val next = sortedGroups.getOrNull(i + 1)
                moveGroup(
                    targetGroup = targetGroup,
                    newOrder = randomBetween(
                        min = prev.order,
                        max = next?.order ?: prev.order.plus(2)
                    )
                )
                return true
            }
        }
        return false
    }

    private fun moveGroup(
        targetGroup: CodeGroup,
        newOrder: Double,
    ) {
        persistence.state.groups.remove(targetGroup)
        persistence.state.groups.add(targetGroup.copy(order = newOrder))
        persistence.state.groups.sortBy { it.order }
    }

    fun deleteGroup(group: CodeGroup) {
        persistence.state.groups.remove(group)
    }
    // endregion

    // region CodeItem operations
    fun addItem(
        group: CodeGroup,
        name: String,
        imports: String,
        code: String,
    ): Boolean {
        return true
    }

    fun editCodeItem(
        group: CodeGroup,
        item: CodeItem,
        name: String,
        imports: String,
        code: String,
    ): Boolean {
        return true
    }

    fun moveItemUp(group: CodeGroup, item: CodeItem) {

    }

    fun moveItemDown(group: CodeGroup, item: CodeItem) {

    }

    fun deleteItem(group: CodeGroup, item: CodeItem) {

    }
    // endregion

}