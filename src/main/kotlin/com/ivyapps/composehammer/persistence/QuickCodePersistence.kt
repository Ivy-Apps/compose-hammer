package com.ivyapps.composehammer.persistence

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.Converter
import com.intellij.util.xmlb.annotations.OptionTag
import com.ivyapps.composehammer.domain.data.quickcode.CodeGroup
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

data class QuickCodeState(
    @OptionTag(converter = GroupsConverter::class)
    var groups: MutableList<CodeGroup> = mutableListOf()
)

@State(
    name = "com.ivyapps.composehammer.persistence.CustomComponentsPersistence",
    storages = [Storage("ComposeHammerQuickCode.xml")]
)
class QuickCodePersistence : PersistentStateComponent<QuickCodeState> {

    private var internalState = QuickCodeState()

    override fun getState(): QuickCodeState = internalState

    override fun loadState(state: QuickCodeState) {
        internalState = state.copy(
            groups = state.groups.sortedBy { it.order }.toMutableList()
        )
    }
}

class GroupsConverter : Converter<List<CodeGroup>>() {
    override fun toString(value: List<CodeGroup>): String {
        return Json.encodeToString(value)
    }

    override fun fromString(value: String): List<CodeGroup>? {
        return Json.decodeFromString(value)
    }
}

