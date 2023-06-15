package com.ivyapps.composehammer.m3content

import com.ivyapps.composehammer.domain.ContentScope
import com.ivyapps.composehammer.domain.component
import com.ivyapps.composehammer.domain.group

fun ContentScope.switches() = group("Switches") {
    component {
        name = "Switch"
        specUrl = "https://m3.material.io/components/switch/specs"
        guidelinesUrl = "https://m3.material.io/components/switch/guidelines"
        docsUrl =
            "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#Switch(kotlin.Boolean,kotlin.Function1,androidx.compose.ui.Modifier,kotlin.Function0,kotlin.Boolean,androidx.compose.material3.SwitchColors,androidx.compose.foundation.interaction.MutableInteractionSource)"
        screenshot = "switch"
        description = """
            Switches are the preferred way to adjust settings. They're used to control binary options – think On/Off or True/False.
            >Use switches to:
            - Toggle a single item on or off
            - Immediately activate or deactivate something
        """.trimIndent()
        imports = listOf(
            "androidx.compose.material3.Switch",
            
            "androidx.compose.runtime.getValue",
            "androidx.compose.runtime.mutableStateOf",
            "androidx.compose.runtime.remember",
            "androidx.compose.runtime.setValue",
            "androidx.compose.ui.Modifier",
            "androidx.compose.ui.semantics.contentDescription",
            "androidx.compose.ui.semantics.semantics",
        )
        code = """
            var checked by remember { mutableStateOf(true) }
    
            Switch(
                modifier = Modifier.semantics { contentDescription = "Switch" },
                checked = checked,
                onCheckedChange = { checked = it }
            )
        """.trimIndent()
        customCode = """
            var checked by remember { mutableStateOf(true) }
    
            Switch(
                modifier = Modifier.semantics { contentDescription = "Demo with icon" },
                checked = checked,
                onCheckedChange = { checked = it },
                thumbContent = if (checked) {
                    {
                        Icon(
                            imageVector = Icons.Filled.Check,
                            contentDescription = "Check",
                            modifier = Modifier.size(SwitchDefaults.IconSize)
                        )
                    }
                } else {
                    null
                },
                enabled = true,
                colors = SwitchDefaults.colors(
                    // customize colors here
                )
            )
        """.trimIndent()
    }
}