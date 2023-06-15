package com.ivyapps.composehammer.m3content

import com.ivyapps.composehammer.domain.ContentScope
import com.ivyapps.composehammer.domain.component
import com.ivyapps.composehammer.domain.group

fun ContentScope.buttons() = group("Buttons") {
    component {
        name = "Elevated button"
        shortName = "Elevated"
        specUrl = "https://m3.material.io/components/buttons/specs#2a19e853-d5dc-46a2-8ef4-1d954c9dcefa"
        guidelinesUrl = "https://m3.material.io/components/buttons/guidelines#4e89da4d-a8fa-4e20-bb8d-b8a93eff3e3e"
        docsUrl =
            "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#ElevatedButton(kotlin.Function0,androidx.compose.ui.Modifier,kotlin.Boolean,androidx.compose.ui.graphics.Shape,androidx.compose.material3.ButtonColors,androidx.compose.material3.ButtonElevation,androidx.compose.foundation.BorderStroke,androidx.compose.foundation.layout.PaddingValues,androidx.compose.foundation.interaction.MutableInteractionSource,kotlin.Function1)"
        description = """
            Elevated buttons are essentially filled tonal buttons with a shadow. 
            To prevent shadow creep, only use them when absolutely necessary, 
            such as when the button requires visual separation from a patterned background.
        """.trimIndent()
        screenshot = "btn_elevated"
        imports = listOf(
            "androidx.compose.material3.ElevatedButton",
            "androidx.compose.material3.Text"
        )
        code = """
        ElevatedButton(
            onClick = {
                /* Do something! */
            }
        ) {
            Text(text = "Text")
        }
        """.trimIndent()
        customCode = """
        ElevatedButton(
            onClick = {
                /* Do something! */
            },
            modifier = Modifier,
            enabled = true,
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.elevatedButtonColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                disabledContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
                disabledContentColor = MaterialTheme.colorScheme.onTertiaryContainer
            ),
            elevation = ButtonDefaults.elevatedButtonElevation(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text(text = "Text")
        }
        """.trimIndent()
    }

    component {
        name = "Filled button"
        shortName = "Filled"
        specUrl = "https://m3.material.io/components/buttons/specs#0b1b7bd2-3de8-431a-afa1-d692e2e18b0d"
        guidelinesUrl = "https://m3.material.io/components/buttons/guidelines#9ecffdb3-ef29-47e7-8d5d-f78b404fcafe"
        docsUrl =
            "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#Button(kotlin.Function0,androidx.compose.ui.Modifier,kotlin.Boolean,androidx.compose.ui.graphics.Shape,androidx.compose.material3.ButtonColors,androidx.compose.material3.ButtonElevation,androidx.compose.foundation.BorderStroke,androidx.compose.foundation.layout.PaddingValues,androidx.compose.foundation.interaction.MutableInteractionSource,kotlin.Function1)"
        description = """
            Filled buttons have the most visual impact after the FAB,
             and should be used for important, final actions
             that complete a flow, like Save, Join now, or Confirm.
        """.trimIndent()
        screenshot = "btn_filled"
        imports = listOf(
            "androidx.compose.material3.Button",
            "androidx.compose.material3.Text",
        )
        code = """
        Button(
            onClick = {
                /* Do something! */
            }
        ) {
            Text("Text")
        }
        """.trimIndent()
        customCode = """
        Button(
            onClick = {
                /* Do something! */
            },
            modifier = Modifier,
            enabled = true,
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                disabledContentColor = MaterialTheme.colorScheme.onSecondaryContainer
            ),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text("Text")
        }
        """.trimIndent()
    }

    component {
        name = "Filled tonal button"
        shortName = "Filled tonal"
        specUrl = "https://m3.material.io/components/buttons/specs#158f0a18-67fb-4ac4-9d22-cc4d1adc4579"
        guidelinesUrl = "https://m3.material.io/components/buttons/guidelines#07a1577b-aaf5-4824-a698-03526421058b"
        docsUrl =
            "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#FilledTonalButton(kotlin.Function0,androidx.compose.ui.Modifier,kotlin.Boolean,androidx.compose.ui.graphics.Shape,androidx.compose.material3.ButtonColors,androidx.compose.material3.ButtonElevation,androidx.compose.foundation.BorderStroke,androidx.compose.foundation.layout.PaddingValues,androidx.compose.foundation.interaction.MutableInteractionSource,kotlin.Function1)"
        description = """
            A filled tonal button is an alternative middle ground between filled and outlined buttons.
             They’re useful in contexts where a lower-priority button requires slightly more emphasis
             than an outline would give, such as "Next" in an onboarding flow.
             Tonal buttons use the secondary color mapping.
        """.trimIndent()
        screenshot = "btn_filled_tonal"
        imports = listOf(
            "androidx.compose.material3.FilledTonalButton",
            "androidx.compose.material3.Text",
        )
        code = """
        FilledTonalButton(
            onClick = {
                /* Do something! */
            }
        ) {
            Text("Text")
        }
        """.trimIndent()
        customCode = """
        FilledTonalButton(
            onClick = {
                /* Do something! */
            },
            modifier = Modifier,
            enabled = true,
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.filledTonalButtonColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                disabledContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
                disabledContentColor = MaterialTheme.colorScheme.onTertiaryContainer
            ),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text("Text")
        }
        """.trimIndent()
    }

    component {
        name = "Outlined button"
        shortName = "Outlined"
        specUrl = "https://m3.material.io/components/buttons/specs#de72d8b1-ba16-4cd7-989e-e2ad3293cf63"
        guidelinesUrl = "https://m3.material.io/components/buttons/guidelines#3742b09f-c224-43e0-a83e-541bd29d0f05"
        docsUrl =
            "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#OutlinedButton(kotlin.Function0,androidx.compose.ui.Modifier,kotlin.Boolean,androidx.compose.ui.graphics.Shape,androidx.compose.material3.ButtonColors,androidx.compose.material3.ButtonElevation,androidx.compose.foundation.BorderStroke,androidx.compose.foundation.layout.PaddingValues,androidx.compose.foundation.interaction.MutableInteractionSource,kotlin.Function1)"
        description = """
            Outlined buttons are medium-emphasis buttons. They contain actions that are important,
             but aren’t the primary action in an app.

             Outlined buttons pair well with filled buttons to indicate an alternative, secondary action.
        """.trimIndent()
        screenshot = "btn_outlined"
        imports = listOf(
            "androidx.compose.material3.OutlinedButton",
            "androidx.compose.material3.Text",
        )
        code = """
        OutlinedButton(
            onClick = {
                /* Do something! */
            }
        ) {
            Text("Text")
        }
        """.trimIndent()
        customCode = """
        OutlinedButton(
            onClick = {
                /* Do something! */
            },
            modifier = Modifier,
            enabled = true,
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                disabledContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
                disabledContentColor = MaterialTheme.colorScheme.onTertiaryContainer
            ),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text("Text")
        }
        """.trimIndent()
    }

    component {
        name = "Text button"
        shortName = "Text"
        specUrl = "https://m3.material.io/components/buttons/specs#899b9107-0127-4a01-8f4c-87f19323a1b4"
        guidelinesUrl = "https://m3.material.io/components/buttons/guidelines#c9bcbc0b-ee05-45ad-8e80-e814ae919fbb"
        docsUrl =
            "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#TextButton(kotlin.Function0,androidx.compose.ui.Modifier,kotlin.Boolean,androidx.compose.ui.graphics.Shape,androidx.compose.material3.ButtonColors,androidx.compose.material3.ButtonElevation,androidx.compose.foundation.BorderStroke,androidx.compose.foundation.layout.PaddingValues,androidx.compose.foundation.interaction.MutableInteractionSource,kotlin.Function1)"
        description = """
            Text buttons are used for the lowest priority actions, especially when presenting multiple options.

             Text buttons can be placed on a variety of backgrounds.
             Until the button is interacted with, its container isn’t visible.
        """.trimIndent()
        screenshot = "btn_text"
        imports = listOf(
            "androidx.compose.material3.TextButton",
            "androidx.compose.material3.Text",
        )
        code = """
        TextButton(
            onClick = {
                /* Do something! */
            }
        ) {
            Text("Text")
        }
        """.trimIndent()
        customCode = """
        TextButton(
            onClick = {
                /* Do something! */
            },
            modifier = Modifier,
            enabled = true,
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.textButtonColors(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text("Text")
        }
        """.trimIndent()
    }
}
