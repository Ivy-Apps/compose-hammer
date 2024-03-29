package com.ivyapps.composehammer.m3content

import com.ivyapps.composehammer.domain.ContentScope
import com.ivyapps.composehammer.domain.component
import com.ivyapps.composehammer.domain.group

fun ContentScope.dividers() = group("Dividers") {
    component {
        name = "Divider"
        specUrl = "https://m3.material.io/components/divider/specs"
        guidelinesUrl = "https://m3.material.io/components/divider/guidelines"
        docsUrl = "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#Divider(androidx.compose.ui.Modifier,androidx.compose.ui.unit.Dp,androidx.compose.ui.graphics.Color)"
        screenshot = "divider"
        description = """
            Dividers are one way to visually group components and create hierarchy. They can also be used to imply nested parent/child relationships.
        """.trimIndent()
        imports = listOf(
            "androidx.compose.material3.Divider"
        )
        code = """
            Divider()
        """.trimIndent()
        customCode = """
            Divider(
                modifier = Modifier,
                thickness = 2.dp,
                color = MaterialTheme.colorScheme.primary
            )
        """.trimIndent()
    }
}