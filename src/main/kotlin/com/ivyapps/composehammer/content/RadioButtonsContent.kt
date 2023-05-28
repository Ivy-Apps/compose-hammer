package com.ivyapps.composehammer.content

import com.ivyapps.composehammer.domain.ContentScope
import com.ivyapps.composehammer.domain.component
import com.ivyapps.composehammer.domain.group

fun ContentScope.radioButtons() = group("Radio buttons") {
    component {
        name = "Radio button"
        specUrl = "https://m3.material.io/components/radio-button/specs"
        guidelinesUrl = "https://m3.material.io/components/radio-button/guidelines"
        docsUrl =
            "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#RadioButton(kotlin.Boolean,kotlin.Function0,androidx.compose.ui.Modifier,kotlin.Boolean,androidx.compose.material3.RadioButtonColors,androidx.compose.foundation.interaction.MutableInteractionSource)"
        screenshot = "radiobtn"
        description = """
            Radio buttons are the recommended way to allow users to make a single selection from a list of options. 

            Only one radio button can be selected at a time.
            >Use radio buttons to:
            - Select a single option from a set
            - Expose all available options
        """.trimIndent()
        imports = listOf(
            "androidx.compose.material3.RadioButton",
            "androidx.compose.runtime.Composable",
            "androidx.compose.ui.Modifier",
            "androidx.compose.ui.semantics.contentDescription",
            "androidx.compose.ui.semantics.semantics",
        )
        code = """
            RadioButton(
                selected = false,
                onClick = {
                    // change selected here
                },
                modifier = Modifier.semantics { contentDescription = "Option 1" }
            )
        """.trimIndent()
        customCode = """
            var state by remember { mutableStateOf(true) }
    
            Row(modifier = Modifier.selectableGroup()) {
                RadioButton(
                    selected = state,
                    onClick = { state = true },
                    modifier = Modifier.semantics { contentDescription = "Option 1" },
                    enabled = true,
                    colors = RadioButtonDefaults.colors(
                        selectedColor = MaterialTheme.colorScheme.primary,
                        unselectedColor = MaterialTheme.colorScheme.secondary,
                        disabledSelectedColor = MaterialTheme.colorScheme.tertiary,
                        disabledUnselectedColor = MaterialTheme.colorScheme.tertiary
                    )
                )
    
                RadioButton(
                    selected = !state,
                    onClick = { state = false },
                    modifier = Modifier.semantics { contentDescription = "Option 2" },
                    enabled = true,
                    colors = RadioButtonDefaults.colors(
                        selectedColor = MaterialTheme.colorScheme.primary,
                        unselectedColor = MaterialTheme.colorScheme.secondary,
                        disabledSelectedColor = MaterialTheme.colorScheme.tertiary,
                        disabledUnselectedColor = MaterialTheme.colorScheme.tertiary
                    )
                )
            }
        """.trimIndent()
    }
}