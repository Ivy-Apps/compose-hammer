package com.ivyapps.composehammer.domain.data.quickcode

import kotlinx.serialization.Serializable

@Serializable
data class QuickCodeConfiguration(
    val projects: MutableList<QCProject> = mutableListOf()
)