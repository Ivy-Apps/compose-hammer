package com.ivyapps.composehammer.persistence

import com.intellij.util.xmlb.Converter
import com.ivyapps.composehammer.domain.data.quickcode.QuickCodeConfiguration
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class QuickCodeConfigurationJson : Converter<QuickCodeConfiguration>() {
    override fun toString(value: QuickCodeConfiguration): String {
        return Json.encodeToString(value)
    }

    override fun fromString(value: String): QuickCodeConfiguration? {
        return Json.decodeFromString(value)
    }
}