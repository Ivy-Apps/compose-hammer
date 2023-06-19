package com.ivyapps.composehammer.persistence

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.annotations.OptionTag
import com.ivyapps.composehammer.domain.data.quickcode.QuickCodeConfiguration

data class QuickCodeState(
    @OptionTag(converter = QuickCodeConfigurationJson::class)
    var configuration: QuickCodeConfiguration = QuickCodeConfiguration()
)

@State(
    name = "com.ivyapps.composehammer.persistence.CustomComponentsPersistence",
    storages = [Storage("ComposeHammerQuickCode_v3.xml")]
)
class QuickCodePersistence : PersistentStateComponent<QuickCodeState> {

    private var internalState = QuickCodeState()

    override fun getState(): QuickCodeState = internalState

    override fun loadState(state: QuickCodeState) {
        internalState = state.copy(
            configuration = state.configuration.copy(
                projects = state.configuration.projects
                    .map {
                        it.copy(
                            groups = it.groups.sortedBy { it.order }
                        )
                    }
                    .sortedBy { it.order }
                    .toMutableList(),
            ),
        )
    }
}