package com.ivyapps.composehammer.domain.data.quickcode

import com.ivyapps.composehammer.domain.data.Enableable
import com.ivyapps.composehammer.domain.data.Reorderable
import kotlinx.serialization.Serializable

@Serializable
data class QCProject(
    override val name: String,
    override val order: Double,
    val groups: List<CodeGroup> = emptyList(),
    override val enabled: Boolean = true,
) : Reorderable, Enableable