package com.ivyapps.composehammer.persistence

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.Converter
import com.intellij.util.xmlb.annotations.OptionTag
import com.ivyapps.composehammer.domain.data.custom.ComponentGroup
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

data class CustomComponentsState(
    @OptionTag(converter = GroupsConverter::class)
    var groups: MutableList<ComponentGroup> = mutableListOf()
)

@State(
    name = "com.ivyapps.composehammer.persistence.CustomComponentsPersistence",
    storages = [Storage("ComposeHammerCustomComponents.xml")]
)
class CustomComponentsPersistence : PersistentStateComponent<CustomComponentsState> {

    private var internalState = CustomComponentsState()

    override fun getState(): CustomComponentsState = internalState

    override fun loadState(state: CustomComponentsState) {
        internalState = state
    }
}

class GroupsConverter : Converter<List<ComponentGroup>>() {
    override fun toString(value: List<ComponentGroup>): String {
        return Json.encodeToString(value)
    }

    override fun fromString(value: String): List<ComponentGroup>? {
        return Json.decodeFromString(value)
    }
}

