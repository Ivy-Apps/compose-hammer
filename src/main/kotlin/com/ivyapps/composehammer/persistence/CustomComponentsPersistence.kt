package com.ivyapps.composehammer.persistence

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.XmlSerializerUtil
import com.ivyapps.composehammer.domain.data.custom.ComponentGroup

@State(
    name = "com.ivyapps.composehammer.persistence.CustomComponentsPersistence",
    storages = [Storage("ComposeHammerCustomComponents.xml")]
)
class CustomComponentsPersistence : PersistentStateComponent<CustomComponentsPersistence.State> {
    data class State(val groups: MutableList<ComponentGroup> = mutableListOf())

    private var myState = State()

    override fun getState(): State {
        return myState
    }

    override fun loadState(state: State) {
        XmlSerializerUtil.copyBean(state, myState)
    }

    fun addGroup(group: ComponentGroup) {
        myState.groups.add(group)
    }
}
