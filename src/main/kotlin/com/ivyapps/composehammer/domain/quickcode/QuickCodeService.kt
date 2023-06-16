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
    fun addGroup(rawName: String): Boolean {
        val name = rawName.trim().takeIf { it.isNotBlank() } ?: return false
        updateState(
            new = CodeGroup(
                name = name,
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
        updateState(old = targetGroup, new = targetGroup.copy(order = newOrder))
    }

    fun deleteGroup(group: CodeGroup) {
        persistence.state.groups.remove(group)
    }
    // endregion

    // region CodeItem operations
    fun addItem(
        group: CodeGroup,
        rawName: String,
        rawImports: String,
        rawCode: String,
    ): Boolean {
        val item = codeItemFrom(
            rawName = rawName,
            rawImports = rawImports,
            rawCode = rawCode,
            order = group.codeItems.lastOrNull()?.order?.plus(2) ?: 1.0
        ) ?: return false

        group.updateCodeItems(
            newCodeItems = group.codeItems + item,
        )
        return true
    }

    fun editCodeItem(
        group: CodeGroup,
        item: CodeItem,
        rawName: String,
        rawImports: String,
        rawCode: String,
    ): Boolean {
        val updatedItem = codeItemFrom(
            rawName = rawName,
            rawImports = rawImports,
            rawCode = rawCode,
            order = item.order
        ) ?: return false

        group.updateCodeItems(
            newCodeItems = group.codeItems.filter { it != item } + updatedItem
        )
        return true
    }

    private fun codeItemFrom(
        rawName: String,
        rawImports: String,
        rawCode: String,
        order: Double,
    ): CodeItem? {
        val name = rawName.trim().takeIf(String::isNotBlank) ?: return null
        val code = rawCode.trim().takeIf(String::isNotBlank) ?: return null
        val imports = rawImports.replace("import", "")
            .split("\n")
            .mapNotNull {
                it.trim().takeIf(String::isNotBlank)
            }

        return CodeItem(
            name = name,
            imports = imports,
            code = code,
            order = order,
        )
    }

    fun moveItemUp(group: CodeGroup, item: CodeItem): Boolean {

        return false
    }

    fun moveItemDown(group: CodeGroup, item: CodeItem): Boolean {

        return false
    }

    fun deleteItem(group: CodeGroup, item: CodeItem): Boolean {
        group.updateCodeItems(
            newCodeItems = group.codeItems.filter { it != item }
        )
        return true
    }

    private fun CodeGroup.updateCodeItems(
        newCodeItems: List<CodeItem>
    ) {
        updateState(
            old = this,
            new = this.copy(
                codeItems = newCodeItems.sortedBy { it.order }
            )
        )
    }
    // endregion

    private fun updateState(
        new: CodeGroup,
        old: CodeGroup? = null,
    ) {
        if (old != null) {
            persistence.state.groups.remove(old)
        }
        persistence.state.groups.add(new)
        persistence.state.groups.sortBy { it.order }
    }
}