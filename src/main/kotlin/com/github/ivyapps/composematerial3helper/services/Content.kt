package com.github.ivyapps.composematerial3helper.services

import com.github.ivyapps.composematerial3helper.data.MaterialComponentsGroup

fun MutableList<MaterialComponentsGroup>.common() = group("Common", showInToolWindow = false) {
    component {
        showInToolWindow = false
        name = "Text"
        imports = listOf(
            "androidx.compose.material3.MaterialTheme",
            "androidx.compose.material3.Text",
            "androidx.compose.runtime.Composable",
            "androidx.compose.ui.Modifier",
            "androidx.compose.ui.text.style.TextAlign",
        )
        code = """
            Text(
                modifier = Modifier,
                text = "Text",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Start,
            )
        """.trimIndent()
    }

    component {
        showInToolWindow = false
        name = "Column"
        imports = listOf(
            "androidx.compose.ui.Modifier",
            "androidx.compose.foundation.layout.Column"
        )
        code = """
            Column(
                modifier = Modifier,
            ) {
        
            }
        """.trimIndent()
    }

    component {
        showInToolWindow = false
        name = "Row"
        imports = listOf(
            "androidx.compose.ui.Modifier",
            "androidx.compose.foundation.layout.Row",
            "androidx.compose.ui.Alignment",
        )
        code = """
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically,
            ) {
        
            }
        """.trimIndent()
    }

    component {
        showInToolWindow = false
        name = "Box"
        imports = listOf(
            "androidx.compose.ui.Modifier",
            "androidx.compose.foundation.layout.Box",
            "androidx.compose.ui.Alignment",
        )
        code = """
            Box(
                modifier = Modifier,
                contentAlignment = Alignment.Center,
            ) {
        
            }
        """.trimIndent()
    }

    component {
        showInToolWindow = false
        name = "Spacer - Vertical"
        imports = listOf(
            "androidx.compose.foundation.layout.Spacer",
            "androidx.compose.ui.Modifier",
            "androidx.compose.ui.unit.dp",
            "androidx.compose.foundation.layout.height",
        )
        code = """
            Spacer(modifier = Modifier.height(8.dp))
        """.trimIndent()
    }

    component {
        showInToolWindow = false
        name = "Spacer - Horizontal"
        imports = listOf(
            "androidx.compose.foundation.layout.Spacer",
            "androidx.compose.ui.Modifier",
            "androidx.compose.ui.unit.dp",
            "androidx.compose.foundation.layout.width"
        )
        code = """
            Spacer(modifier = Modifier.width(8.dp))
        """.trimIndent()
    }

    component {
        showInToolWindow = false
        name = "Spacer - Weight"
        imports = listOf(
            "androidx.compose.foundation.layout.Spacer",
            "androidx.compose.ui.Modifier",
        )
        code = """
            Spacer(modifier = Modifier.weight(1f))
        """.trimIndent()
    }

    component {
        showInToolWindow = false
        name = "Lazy Column"
        imports = listOf(
            "androidx.compose.ui.Modifier",
            "androidx.compose.foundation.lazy.LazyColumn",
            "androidx.compose.foundation.layout.PaddingValues",
            "androidx.compose.ui.unit.dp",
        )
        code = """
            LazyColumn(
                modifier = Modifier,
                contentPadding = PaddingValues(vertical = 8.dp)
            ) {
        
            }
        """.trimIndent()
    }

    component {
        showInToolWindow = false
        name = "Lazy Row"
        imports = listOf(
            "androidx.compose.ui.Modifier",
            "androidx.compose.foundation.lazy.LazyRow",
            "androidx.compose.foundation.layout.PaddingValues",
            "androidx.compose.ui.unit.dp",
        )
        code = """
            LazyRow(
                modifier = Modifier,
                contentPadding = PaddingValues(horizontal = 8.dp)
            ) {
        
            }
        """.trimIndent()
    }
}

fun MutableList<MaterialComponentsGroup>.buttons() = group("Buttons") {
    component {
        name = "Elevated Button"
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
        import = "androidx.compose.material3.ElevatedButton"
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
        name = "Filled Button"
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
        import = "androidx.compose.material3.Button"
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
        name = "Filled Tonal Button"
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
        import = "androidx.compose.material3.FilledTonalButton"
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
        name = "Outlined Button"
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
        import = "androidx.compose.material3.OutlinedButton"
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
        name = "Text Button"
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
        import = "androidx.compose.material3.TextButton"
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

fun MutableList<MaterialComponentsGroup>.fab() = group("Floating Action Buttons") {
    component {
        name = "Floating Action Button"
        specUrl = "https://m3.material.io/components/floating-action-button/specs#71504201-7bd1-423d-8bb7-07e0291743e5"
        guidelinesUrl =
            "https://m3.material.io/components/floating-action-button/guidelines#dbfbab5d-c3e2-47a4-be6e-c566e9125443"
        docsUrl =
            "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#FloatingActionButton(kotlin.Function0,androidx.compose.ui.Modifier,androidx.compose.ui.graphics.Shape,androidx.compose.ui.graphics.Color,androidx.compose.ui.graphics.Color,androidx.compose.material3.FloatingActionButtonElevation,androidx.compose.foundation.interaction.MutableInteractionSource,kotlin.Function0)"
        description = """
            Use a FAB to represent the screen’s primary action.
        """.trimIndent()
        screenshot = "fab"
        import = "androidx.compose.material3.FloatingActionButton"
        code = """
        FloatingActionButton(
            onClick = {
                /* Do something! */
            }
        ) {
            Icon(
                imageVector = Icons.Default.Create,
                contentDescription = "Create"
            )
        }
        """.trimIndent()
        customCode = """
        FloatingActionButton(
            onClick = {
                /* Do something! */
            },
            modifier = Modifier,
            shape = FloatingActionButtonDefaults.shape,
            containerColor = FloatingActionButtonDefaults.containerColor,
            contentColor = contentColorFor(FloatingActionButtonDefaults.containerColor),
            elevation = FloatingActionButtonDefaults.elevation(4.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Create,
                contentDescription = "Create"
            )
        }
        """.trimIndent()
    }

    component {
        name = "Small Floating Action Button"
        specUrl = "https://m3.material.io/components/floating-action-button/specs#df918e03-5939-4aa4-8d4b-4cdffa52b240"
        guidelinesUrl =
            "https://m3.material.io/components/floating-action-button/guidelines#2f9ec481-b30d-4dc3-89d7-feb2ca7bec4d"
        docsUrl =
            "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#SmallFloatingActionButton(kotlin.Function0,androidx.compose.ui.Modifier,androidx.compose.ui.graphics.Shape,androidx.compose.ui.graphics.Color,androidx.compose.ui.graphics.Color,androidx.compose.material3.FloatingActionButtonElevation,androidx.compose.foundation.interaction.MutableInteractionSource,kotlin.Function0)"
        description = """
            A small FAB is used for a secondary, supporting action, or in place of a default FAB on smaller screens.
             One or more small FABs can be paired with a default FAB or extended FAB.
        """.trimIndent()
        screenshot = "fab_small"
        import = "androidx.compose.material3.SmallFloatingActionButton"
        code = """
        SmallFloatingActionButton(
            onClick = {
                /* do something */
            },
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Add"
            )
        }
        """.trimIndent()
        customCode = """
        SmallFloatingActionButton(
            onClick = {
                /* do something */
            },
            modifier = Modifier,
            shape = FloatingActionButtonDefaults.smallShape,
            containerColor = FloatingActionButtonDefaults.containerColor,
            contentColor = contentColorFor(FloatingActionButtonDefaults.containerColor),
            elevation = FloatingActionButtonDefaults.elevation(4.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Add"
            )
        }
        """.trimIndent()
    }

    component {
        name = "Large Floating Action Button"
        specUrl = "https://m3.material.io/components/floating-action-button/specs#9d7d3d6a-bab7-47cb-be32-5596fbd660fe"
        guidelinesUrl =
            "https://m3.material.io/components/floating-action-button/guidelines#8a2b2f98-c84b-4284-bd31-a54cb77194bc"
        docsUrl =
            "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#LargeFloatingActionButton(kotlin.Function0,androidx.compose.ui.Modifier,androidx.compose.ui.graphics.Shape,androidx.compose.ui.graphics.Color,androidx.compose.ui.graphics.Color,androidx.compose.material3.FloatingActionButtonElevation,androidx.compose.foundation.interaction.MutableInteractionSource,kotlin.Function0)"
        description = """
            A large FAB is useful when the layout calls for a clear and prominent primary action,
             and where a larger footprint would help the user engage. For example,
             when appearing on taller and larger device screens.
        """.trimIndent()
        screenshot = "fab_large"
        import = "androidx.compose.material3.LargeFloatingActionButton"
        code = """
        LargeFloatingActionButton(
            onClick = {
                /* do something */
            }
        ) {
            Icon(
                Icons.Filled.Add,
                contentDescription = "Localized description",
                modifier = Modifier.size(FloatingActionButtonDefaults.LargeIconSize),
            )
        }
        """.trimIndent()
        customCode = """
        LargeFloatingActionButton(
            onClick = {
                /* do something */
            },
            modifier = Modifier,
            shape = FloatingActionButtonDefaults.largeShape,
            containerColor = FloatingActionButtonDefaults.containerColor,
            contentColor = contentColorFor(FloatingActionButtonDefaults.containerColor),
            elevation = FloatingActionButtonDefaults.elevation(4.dp)
        ) {
            Icon(
                Icons.Filled.Add,
                contentDescription = "Localized description",
                modifier = Modifier.size(FloatingActionButtonDefaults.LargeIconSize),
            )
        }
        """.trimIndent()
    }

    component {
        name = "Extended Floating Action Button"
        specUrl = "https://m3.material.io/components/extended-fab/specs#8c06766e-0afc-436f-a695-aa589700be14"
        guidelinesUrl =
            "https://m3.material.io/components/extended-fab/guidelines#c33cceea-4076-4df8-b83c-edf5e644f64d"
        docsUrl =
            "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#ExtendedFloatingActionButton(kotlin.Function0,androidx.compose.ui.Modifier,androidx.compose.ui.graphics.Shape,androidx.compose.ui.graphics.Color,androidx.compose.ui.graphics.Color,androidx.compose.material3.FloatingActionButtonElevation,androidx.compose.foundation.interaction.MutableInteractionSource,kotlin.Function1)"
        description = """
            Use an extended FAB on screens with long, scrolling views that require persistent access to an action,
             such as a check-out screen. Do not use an extended FAB in a view that cannot scroll.
        """.trimIndent()
        screenshot = "fab_extended"
        import = "androidx.compose.material3.ExtendedFloatingActionButton"
        code = """
        ExtendedFloatingActionButton(
            onClick = {
                /* do something */
            }
        ) {
            Icon(
                imageVector = Icons.Default.Create,
                contentDescription = "Create",
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(text = "Compose")
        }
        """.trimIndent()
        customCode = """
        ExtendedFloatingActionButton(
            onClick = {
                /* do something */
            },
            modifier = Modifier,
            shape = FloatingActionButtonDefaults.extendedFabShape,
            containerColor = FloatingActionButtonDefaults.containerColor,
            contentColor = contentColorFor(FloatingActionButtonDefaults.containerColor),
            elevation = FloatingActionButtonDefaults.elevation(4.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Create,
                contentDescription = "Create",
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(text = "Compose")
        }
        """.trimIndent()
    }
}
