package com.ivyapps.composehammer.domain.data.quickcode

import com.ivyapps.composehammer.domain.data.Code
import com.ivyapps.composehammer.domain.data.Reorderable
import kotlinx.serialization.Serializable

@Serializable
data class CodeItem(
    override val name: String,
    override val imports: List<String> = emptyList(),
    val rawCode: String,
    val variables: List<String> = emptyList(),
    override val order: Double,
) : Code, Reorderable {
    override val menuName: String
        get() = name

    override val code: String
        get() = rawCode
}