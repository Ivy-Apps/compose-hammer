package com.ivyapps.composehammer.domain.data.quickcode

import com.ivyapps.composehammer.domain.data.Code
import kotlinx.serialization.Serializable

@Serializable
data class CodeItem(
    val name: String,
    override val imports: List<String>,
    override val code: String,
    val order: Double,
) : Code {
    override val menuName: String
        get() = name
}