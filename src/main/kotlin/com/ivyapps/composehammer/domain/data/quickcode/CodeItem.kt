package com.ivyapps.composehammer.domain.data.quickcode

import com.ivyapps.composehammer.domain.data.Code
import com.ivyapps.composehammer.domain.data.Reorderable
import kotlinx.serialization.Serializable

@Serializable
data class CodeItem(
    override val name: String,
    override val imports: List<String>,
    override val code: String,
    override val order: Double,
) : Code, Reorderable {
    override val menuName: String
        get() = name
}