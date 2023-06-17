package com.ivyapps.composehammer.persistence

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.Converter
import com.intellij.util.xmlb.annotations.OptionTag
import com.ivyapps.composehammer.domain.data.quickcode.QuickCodeConfiguration
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

data class QuickCodeState(
    @OptionTag(converter = QuickCodeConfigurationJson::class)
    var configuration: QuickCodeConfiguration
)

@State(
    name = "com.ivyapps.composehammer.persistence.CustomComponentsPersistence",
    storages = [Storage("ComposeHammerQuickCode_v1.xml")]
)
class QuickCodePersistence : PersistentStateComponent<QuickCodeState> {

    private var internalState = QuickCodeState(
        configuration = QuickCodeConfiguration(
            groups = mutableListOf(),
        )
    )

    override fun getState(): QuickCodeState = internalState

    override fun loadState(state: QuickCodeState) {
        internalState = state.copy(
            configuration = state.configuration.copy(
                groups = state.configuration.groups
                    .sortedBy { it.order }
                    .toMutableList()
            ),
        )
    }
}

class QuickCodeConfigurationJson : Converter<QuickCodeConfiguration>() {
    override fun toString(value: QuickCodeConfiguration): String {
        return Json.encodeToString(value)
    }

    override fun fromString(value: String): QuickCodeConfiguration? {
        return Json.decodeFromString(value)
    }
}

