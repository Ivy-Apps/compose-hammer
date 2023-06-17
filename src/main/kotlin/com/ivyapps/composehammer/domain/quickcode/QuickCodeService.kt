package com.ivyapps.composehammer.domain.quickcode

import com.intellij.openapi.components.Service
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.ivyapps.composehammer.domain.data.Reorderable
import com.ivyapps.composehammer.domain.data.quickcode.CodeGroup
import com.ivyapps.composehammer.domain.data.quickcode.CodeItem
import com.ivyapps.composehammer.domain.data.quickcode.QuickCodeConfiguration
import com.ivyapps.composehammer.persistence.QuickCodePersistence
import com.ivyapps.composehammer.randomBetween

@Service(Service.Level.PROJECT)
class QuickCodeService(project: Project) {
    private val persistence = project.service<QuickCodePersistence>()

    val configuration: QuickCodeConfiguration
        get() = persistence.state.configuration

    val groups: List<CodeGroup>
        get() = configuration.groups.sortedBy { it.order }

    fun hasDefinedComponents() = configuration.groups.isNotEmpty()

    fun findGroupByName(name: String): CodeGroup {
        return requireNotNull(groups.find { it.name == name }) {
            "CodeGroup with name '$name' doesn't exists in $groups."
        }
    }

    fun findCodeItemByName(group: CodeGroup, name: String): CodeItem {
        return requireNotNull(group.codeItems.find { it.name == name }) {
            "CodeItem with name '$name' doesn't exists in ${group.codeItems}."
        }
    }

    // region Group operations
    fun addGroup(rawName: String): CodeGroup? {
        val name = rawName.trim().takeIf { it.isNotBlank() } ?: return null
        val groups = groups

        val existingGroup = groups.find { it.name == name }
        if (existingGroup != null) {
            // group already exists
            return existingGroup
        }

        val newGroup = CodeGroup(
            name = name,
            order = groups.lastOrNull()?.order?.plus(2) ?: 1.0,
            codeItems = emptyList()
        )
        updateState(
            new = newGroup
        )
        return newGroup
    }

    fun renameGroup(group: CodeGroup, rawNewName: String): Boolean {
        val newName = rawNewName.trim().takeIf { it.isNotBlank() } ?: return false
        updateState(
            old = group,
            new = group.copy(
                name = newName
            )
        )
        return true
    }

    fun moveGroupUp(group: CodeGroup): Boolean {
        val newOrder = groups.moveUp(group) ?: return false
        moveGroup(
            targetGroup = group,
            newOrder = newOrder
        )
        return true
    }

    fun moveGroupDown(group: CodeGroup): Boolean {
        val newOrder = groups.moveDown(group) ?: return false
        moveGroup(
            targetGroup = group,
            newOrder = newOrder
        )
        return true
    }

    private fun moveGroup(
        targetGroup: CodeGroup,
        newOrder: Double,
    ) {
        updateState(old = targetGroup, new = targetGroup.copy(order = newOrder))
    }

    fun deleteGroup(group: CodeGroup) {
        configuration.groups.remove(group)
    }
    // endregion

    // region CodeItem operations
    fun addCodeItem(
        group: CodeGroup,
        rawName: String,
        rawImports: String,
        rawCode: String,
    ): Boolean {
        val newItem = codeItemFrom(
            rawName = rawName,
            rawImports = rawImports,
            rawCode = rawCode,
            order = group.codeItems.lastOrNull()?.order?.plus(2) ?: 1.0
        ) ?: return false

        if (group.codeItems.any { it.name == newItem.name }) {
            // item already exists, don't add it!
            return false
        }

        group.executeCodeItemsUpdate(
            newCodeItems = group.codeItems + newItem,
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

        group.executeCodeItemsUpdate(
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
            rawCode = code,
            variables = emptyList(),
            order = order,
        )
    }

    fun moveCodeItemUp(group: CodeGroup, item: CodeItem): Boolean {
        val newOrder = group.codeItems.moveUp(item) ?: return false
        moveCodeItem(group, item, newOrder)
        return true
    }

    fun moveCodeItemDown(group: CodeGroup, item: CodeItem): Boolean {
        val newOrder = group.codeItems.moveDown(item) ?: return false
        moveCodeItem(group, item, newOrder)
        return true
    }

    private fun moveCodeItem(
        group: CodeGroup,
        item: CodeItem,
        newOrder: Double
    ) {
        group.executeCodeItemsUpdate(
            newCodeItems = group.codeItems.filter { it != item } + item.copy(order = newOrder)
        )
    }

    fun deleteCodeItem(group: CodeGroup, item: CodeItem): Boolean {
        group.executeCodeItemsUpdate(
            newCodeItems = group.codeItems.filter { it != item }
        )
        return true
    }

    private fun CodeGroup.executeCodeItemsUpdate(
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
            configuration.groups.remove(old)
        }
        configuration.groups.add(new)
        configuration.groups.sortBy { it.order }
    }

    private fun <T : Reorderable> List<T>.moveUp(target: Reorderable): Double? {
        for ((index, group) in this.withIndex()) {
            if (target.name == group.name) {
                val prev = getOrNull(index - 1)
                    ?: return null // it's already first!
                val prevPrev = getOrNull(index - 2)
                return randomBetween(
                    min = prevPrev?.order ?: prev.order.minus(2),
                    max = prev.order
                )
            }
        }
        return null
    }

    private fun <T : Reorderable> List<T>.moveDown(target: Reorderable): Double? {
        for ((index, item) in this.withIndex()) {
            if (target.name == item.name) {
                val next = getOrNull(index + 1)
                    ?: return null // it's already last!
                val nextNext = getOrNull(index + 2)
                return randomBetween(
                    min = next.order,
                    max = nextNext?.order ?: next.order.plus(2)
                )
            }
        }
        return null
    }
}