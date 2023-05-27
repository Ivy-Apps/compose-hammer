package com.ivyapps.composematerial3helper

import com.ivyapps.composematerial3helper.services.ContentScope
import com.ivyapps.composematerial3helper.services.component
import com.ivyapps.composematerial3helper.services.group

fun ContentScope.common() = group("Common", showInToolWindow = false) {
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
        name = "modifier: Modifier = Modifier"
        import = "androidx.compose.ui.Modifier"
        code = """
            modifier: Modifier = Modifier,
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

fun ContentScope.composeRuntime() = group(
    title = "Compose runtime",
    showInToolWindow = false
) {
    component {
        showInToolWindow = false
        name = "Compose State"
        imports = listOf(
            "androidx.compose.runtime.getValue",
            "androidx.compose.runtime.mutableStateOf",
            "androidx.compose.runtime.remember",
            "androidx.compose.runtime.setValue",
        )
        code = """
            var state by remember { mutableStateOf<String?>(null) }
        """.trimIndent()
    }

    component {
        showInToolWindow = false
        name = "Local Context"
        imports = listOf(
            "androidx.compose.ui.platform.LocalContext",
        )
        code = """
            val context = LocalContext.current
        """.trimIndent()
    }

    component {
        showInToolWindow = false
        name = "Coroutine scope"
        import = "androidx.compose.runtime.rememberCoroutineScope"
        code = """
            val coroutineScope = rememberCoroutineScope()
        """.trimIndent()
    }

    component {
        showInToolWindow = false
        name = "Launched Effect"
        import = "androidx.compose.runtime.LaunchedEffect"
        code = """
            LaunchedEffect(Unit) {
    
            }
        """.trimIndent()
    }
}

fun ContentScope.animations() = group("Animations") {

}

fun ContentScope.buttons() = group("Buttons") {
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

fun ContentScope.fab() = group("Floating Action Buttons") {
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
        imports = listOf(
            "androidx.compose.material3.FloatingActionButton",
            "androidx.compose.material.icons.Icons",
            "androidx.compose.material.icons.filled.Create",
            "androidx.compose.material3.Icon"
        )
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
        imports = listOf(
            "androidx.compose.material3.SmallFloatingActionButton",
            "androidx.compose.material3.Icon",
            "androidx.compose.material.icons.Icons",
            "androidx.compose.material.icons.filled.Add"
        )
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
        imports = listOf(
            "androidx.compose.material3.LargeFloatingActionButton",
            "androidx.compose.material3.Icon",
            "androidx.compose.ui.Modifier",
            "androidx.compose.foundation.layout.size",
            "androidx.compose.material.icons.Icons",
            "androidx.compose.material.icons.filled.Add",
            "androidx.compose.material3.FloatingActionButtonDefaults"
        )
        code = """
        LargeFloatingActionButton(
            onClick = {
                /* do something */
            }
        ) {
            Icon(
                Icons.Filled.Add,
                contentDescription = "Add",
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
                contentDescription = "Add",
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
        imports = listOf(
            "androidx.compose.material3.ExtendedFloatingActionButton",
            "androidx.compose.foundation.layout.Spacer",
            "androidx.compose.foundation.layout.width",
            "androidx.compose.material.icons.Icons",
            "androidx.compose.material.icons.filled.Create",
            "androidx.compose.material3.Icon",
            "androidx.compose.ui.Modifier",
            "androidx.compose.ui.unit.dp"
        )
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

fun ContentScope.iconButtons() = group("Icon Buttons") {
    component {
        name = "Filled Icon Button"
        specUrl = "https://m3.material.io/components/icon-buttons/specs#d4169fb5-4cf8-40b6-9ec3-4044f09cca1f"
        guidelinesUrl =
            "https://m3.material.io/components/icon-buttons/guidelines#a78dd95b-c0a6-4d18-ad3d-57d4a173f37c"
        docsUrl =
            "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#FilledIconButton(kotlin.Function0,androidx.compose.ui.Modifier,kotlin.Boolean,androidx.compose.ui.graphics.Shape,androidx.compose.material3.IconButtonColors,androidx.compose.foundation.interaction.MutableInteractionSource,kotlin.Function0)"
        description = """
            Filled icon buttons have higher visual impact and are best for high emphasis actions.
        """.trimIndent()
        screenshot = "iconbtn_filled"
        imports = listOf(
            "androidx.compose.material3.FilledIconButton",
            "androidx.compose.material3.Icon",
            "androidx.compose.material.icons.Icons",
            "androidx.compose.material.icons.outlined.Lock"
        )
        code = """
        FilledIconButton(
            onClick = {
                /* doSomething() */
            }
        ) {
            Icon(
                imageVector = Icons.Outlined.Lock,
                contentDescription = "Lock"
            )
        }
        """.trimIndent()
        customCode = """
        FilledIconButton(
            onClick = {
                /* doSomething() */
            },
            modifier = Modifier,
            enabled = true,
            shape = RoundedCornerShape(12.dp),
            colors = IconButtonDefaults.filledIconButtonColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                disabledContainerColor = MaterialTheme.colorScheme.primaryContainer,
                disabledContentColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        ) {
            Icon(
                imageVector = Icons.Outlined.Lock,
                contentDescription = "Lock"
            )
        }
        """.trimIndent()
    }

    component {
        name = "Filled Tonal Icon Button"
        specUrl = "https://m3.material.io/components/icon-buttons/specs#c2ca424b-2ad7-40e6-8946-47fb1918060a"
        guidelinesUrl =
            "https://m3.material.io/components/icon-buttons/guidelines#a78dd95b-c0a6-4d18-ad3d-57d4a173f37c"
        docsUrl =
            "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#FilledTonalIconButton(kotlin.Function0,androidx.compose.ui.Modifier,kotlin.Boolean,androidx.compose.ui.graphics.Shape,androidx.compose.material3.IconButtonColors,androidx.compose.foundation.interaction.MutableInteractionSource,kotlin.Function0)"
        description = """
            Filled tonal icon buttons are a middle ground between filled and outlined icon buttons.
             They’re useful in contexts where the button requires slightly more emphasis than an outline would give,
             such as a secondary action paired with a high emphasis action.
        """.trimIndent()
        screenshot = "iconbtn_filled_tonal"
        imports = listOf(
            "androidx.compose.material3.FilledTonalIconButton",
            "androidx.compose.material3.Icon",
            "androidx.compose.material.icons.Icons",
            "androidx.compose.material.icons.outlined.Lock"
        )
        code = """
        FilledTonalIconButton(
            onClick = {
                /* doSomething() */
            }
        ) {
            Icon(
                imageVector = Icons.Outlined.Lock,
                contentDescription = "Lock"
            )
        }
        """.trimIndent()
        customCode = """
        FilledTonalIconButton(
            onClick = {
                /* doSomething() */
            },
            modifier = Modifier,
            enabled = true,
            shape = RoundedCornerShape(12.dp),
            colors = IconButtonDefaults.filledTonalIconButtonColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                disabledContainerColor = MaterialTheme.colorScheme.primaryContainer,
                disabledContentColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        ) {
            Icon(
                imageVector = Icons.Outlined.Lock,
                contentDescription = "Lock"
            )
        }
        """.trimIndent()
    }

    component {
        name = "Outlined Icon Button"
        specUrl = "https://m3.material.io/components/icon-buttons/specs#632e1356-8002-4ae1-ae36-48c1f9b17ef2"
        guidelinesUrl =
            "https://m3.material.io/components/icon-buttons/guidelines#a78dd95b-c0a6-4d18-ad3d-57d4a173f37c"
        docsUrl =
            "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#OutlinedIconButton(kotlin.Function0,androidx.compose.ui.Modifier,kotlin.Boolean,androidx.compose.ui.graphics.Shape,androidx.compose.material3.IconButtonColors,androidx.compose.foundation.BorderStroke,androidx.compose.foundation.interaction.MutableInteractionSource,kotlin.Function0)"
        description = """
            Outlined icon buttons are medium-emphasis buttons. They’re useful when an icon button needs more emphasis
             than a standard icon button but less than a filled or filled tonal icon button.
        """.trimIndent()
        screenshot = "iconbtn_outlined"
        imports = listOf(
            "androidx.compose.material3.OutlinedIconButton",
            "androidx.compose.material3.Icon",
            "androidx.compose.material.icons.Icons",
            "androidx.compose.material.icons.outlined.Lock"
        )
        code = """
        OutlinedIconButton(
            onClick = {
                /* doSomething() */
            }
        ) {
            Icon(
                imageVector = Icons.Outlined.Lock,
                contentDescription = "Lock"
            )
        }
        """.trimIndent()
        customCode = """
        OutlinedIconButton(
            onClick = {
                /* doSomething() */
            },
            modifier = Modifier,
            enabled = true,
            shape = RoundedCornerShape(12.dp),
            colors = IconButtonDefaults.filledTonalIconButtonColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                disabledContainerColor = MaterialTheme.colorScheme.primaryContainer,
                disabledContentColor = MaterialTheme.colorScheme.onPrimaryContainer
            ),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.primaryContainer)
        ) {
            Icon(
                imageVector = Icons.Outlined.Lock,
                contentDescription = "Lock"
            )
        }
        """.trimIndent()
    }

    component {
        name = "Standard Icon Button"
        specUrl = "https://m3.material.io/components/icon-buttons/specs#eca0451e-430b-41e1-bea3-a31cb7ccda76"
        guidelinesUrl =
            "https://m3.material.io/components/icon-buttons/guidelines#a78dd95b-c0a6-4d18-ad3d-57d4a173f37c"
        docsUrl =
            "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#IconButton(kotlin.Function0,androidx.compose.ui.Modifier,kotlin.Boolean,androidx.compose.material3.IconButtonColors,androidx.compose.foundation.interaction.MutableInteractionSource,kotlin.Function0)"
        description = """
            Use icon buttons to display actions in a compact layout.
             Icon buttons can represent opening actions such as opening an overflow menu or search,
             or represent binary actions that can be toggled on and off, such as favorite or bookmark.

             Icon buttons can be grouped together or they can stand alone.
        """.trimIndent()
        screenshot = "iconbtn_standard"
        imports = listOf(
            "androidx.compose.material.icons.Icons",
            "androidx.compose.material.icons.filled.Menu",
            "androidx.compose.material3.Icon",
            "androidx.compose.material3.IconButton"
        )
        code = """
        IconButton(
            onClick = {
                /* doSomething() */
            }
        ) {
            Icon(
                imageVector = Icons.Filled.Menu,
                contentDescription = "Menu"
            )
        }
        """.trimIndent()
        customCode = """
        IconButton(
            onClick = {
                /* doSomething() */
            },
            modifier = Modifier,
            enabled = true,
            colors = IconButtonDefaults.iconButtonColors()
        ) {
            Icon(
                imageVector = Icons.Filled.Menu,
                contentDescription = "Menu"
            )
        }
        """.trimIndent()
    }
}

fun ContentScope.datePickers() = group("Date Pickers") {
    component {
        name = "Date Picker (input)"
        specUrl = "https://m3.material.io/components/date-pickers/specs#ccd8cb55-4c20-4832-9db2-7c14c49b6e8f"
        guidelinesUrl = "https://m3.material.io/components/date-pickers/guidelines#07d8ef51-1085-4838-b20f-2a0aa62dabe0"
        docsUrl =
            "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#DatePicker(androidx.compose.material3.DatePickerState,androidx.compose.ui.Modifier,androidx.compose.material3.DatePickerFormatter,kotlin.Function0,kotlin.Function0,kotlin.Boolean,androidx.compose.material3.DatePickerColors)"
        description = """
            Mobile date input pickers allow the manual entry of dates using the numbers on a keyboard. 
            Users can input a date or a range of dates in a dialog.
        """.trimIndent()
        screenshot = "datepicker_input"
        imports = listOf(
            "androidx.compose.material3.DatePicker",
            "androidx.compose.material3.rememberDatePickerState",
            "androidx.compose.material3.DisplayMode",
        )
        code = """
            val datePickerState = rememberDatePickerState(
                initialSelectedDateMillis = 1685112333816, // epoch/unix timestamp
                initialDisplayMode = DisplayMode.Input,
            )

            DatePicker(
                state = datePickerState,
                showModeToggle = false,
                headline = null,
                title = null,
            )
        """.trimIndent()
        customCode = """
            val datePickerState = rememberDatePickerState(
                initialSelectedDateMillis = 1685112333816, // epoch/unix timestamp
                initialDisplayMode = DisplayMode.Input,
            )

            DatePicker(
                state = datePickerState,
                showModeToggle = true,
                headline = {
                    // <Your Compose UI here>
                },
                title = {
                    // <Your Compose UI here>
                },
                colors = DatePickerDefaults.colors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    headlineContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    weekdayContentColor = MaterialTheme.colorScheme.primary,
                    subheadContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    yearContentColor = MaterialTheme.colorScheme.tertiary,
                    currentYearContentColor = MaterialTheme.colorScheme.tertiary,
                    selectedYearContentColor = MaterialTheme.colorScheme.primary,
                    selectedYearContainerColor = MaterialTheme.colorScheme.onPrimary,
                    dayContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    disabledDayContentColor = MaterialTheme.colorScheme.onTertiary,
                    selectedDayContentColor = MaterialTheme.colorScheme.onPrimary,
                    disabledSelectedDayContentColor = MaterialTheme.colorScheme.onTertiary,
                    selectedDayContainerColor = MaterialTheme.colorScheme.primary,
                    disabledSelectedDayContainerColor = MaterialTheme.colorScheme.tertiary,
                    todayContentColor = MaterialTheme.colorScheme.primary,
                    todayDateBorderColor = MaterialTheme.colorScheme.primary,
                    dayInSelectionRangeContentColor = MaterialTheme.colorScheme.primary,
                    dayInSelectionRangeContainerColor = MaterialTheme.colorScheme.onPrimary
                ),
            )
        """.trimIndent()
    }

    component {
        name = "Date Picker (calendar)"
        specUrl = "https://m3.material.io/components/date-pickers/specs#d58626b9-ed69-4963-a75c-18d00cae5a06"
        guidelinesUrl = "https://m3.material.io/components/date-pickers/guidelines#c5c0471f-aa8a-4205-ab4b-1ab8cb893c5c"
        docsUrl =
            "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#DatePicker(androidx.compose.material3.DatePickerState,androidx.compose.ui.Modifier,androidx.compose.material3.DatePickerFormatter,kotlin.Function0,kotlin.Function0,kotlin.Boolean,androidx.compose.material3.DatePickerColors)"
        description = """
            Mobile calendar pickers navigate across dates in several ways:
            - To navigate across months, swipe horizontally
            - To navigate across years, scroll vertically
            - To access the year picker, tap the year
            Don’t use a modal date picker to prompt for dates in the distant past or future, 
            such as a date of birth. In these cases, use a modal input picker or a docked date picker instead.
        """.trimIndent()
        screenshot = "datepicker_calendar"
        imports = listOf(
            "androidx.compose.material3.DatePicker",
            "androidx.compose.material3.rememberDatePickerState",
            "androidx.compose.material3.DisplayMode",
        )
        code = """
            val datePickerState = rememberDatePickerState(
                initialSelectedDateMillis = 1685112333816, // epoch/unix timestamp
                initialDisplayMode = DisplayMode.Picker,
            )

            DatePicker(
                state = datePickerState,
                showModeToggle = false,
                headline = null,
                title = null,
            )
        """.trimIndent()
        customCode = """
            val datePickerState = rememberDatePickerState(
                initialSelectedDateMillis = 1685112333816, // epoch/unix timestamp
                initialDisplayMode = DisplayMode.Picker,
            )

            DatePicker(
                state = datePickerState,
                showModeToggle = true,
                headline = {
                    // <Your Compose UI here>
                },
                title = {
                    // <Your Compose UI here>
                },
                colors = DatePickerDefaults.colors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    headlineContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    weekdayContentColor = MaterialTheme.colorScheme.primary,
                    subheadContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    yearContentColor = MaterialTheme.colorScheme.tertiary,
                    currentYearContentColor = MaterialTheme.colorScheme.tertiary,
                    selectedYearContentColor = MaterialTheme.colorScheme.primary,
                    selectedYearContainerColor = MaterialTheme.colorScheme.onPrimary,
                    dayContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    disabledDayContentColor = MaterialTheme.colorScheme.onTertiary,
                    selectedDayContentColor = MaterialTheme.colorScheme.onPrimary,
                    disabledSelectedDayContentColor = MaterialTheme.colorScheme.onTertiary,
                    selectedDayContainerColor = MaterialTheme.colorScheme.primary,
                    disabledSelectedDayContainerColor = MaterialTheme.colorScheme.tertiary,
                    todayContentColor = MaterialTheme.colorScheme.primary,
                    todayDateBorderColor = MaterialTheme.colorScheme.primary,
                    dayInSelectionRangeContentColor = MaterialTheme.colorScheme.primary,
                    dayInSelectionRangeContainerColor = MaterialTheme.colorScheme.onPrimary
                ),
            )
        """.trimIndent()
    }

    component {
        name = "Date Picker dialog"
        specUrl = "https://m3.material.io/components/date-pickers/specs#d58626b9-ed69-4963-a75c-18d00cae5a06"
        guidelinesUrl = "https://m3.material.io/components/date-pickers/guidelines#c5c0471f-aa8a-4205-ab4b-1ab8cb893c5c"
        docsUrl =
            "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#DatePickerDialog(kotlin.Function0,kotlin.Function0,androidx.compose.ui.Modifier,kotlin.Function0,androidx.compose.ui.graphics.Shape,androidx.compose.ui.unit.Dp,androidx.compose.material3.DatePickerColors,androidx.compose.ui.window.DialogProperties,kotlin.Function1)"
        description = """
            A dialog wrapper over the the DatePicker component allowing you to show it as a dialog.
        """.trimIndent()
        screenshot = "datepicker_dialog"
        imports = listOf(
            "androidx.compose.material3.DatePicker",
            "androidx.compose.material3.DatePickerDialog",
            "androidx.compose.material3.ExperimentalMaterial3Api",
            "androidx.compose.material3.Text",
            "androidx.compose.material3.TextButton",
            "androidx.compose.material3.rememberDatePickerState",
            "androidx.compose.runtime.Composable",
            "androidx.compose.runtime.derivedStateOf",
            "androidx.compose.runtime.getValue",
            "androidx.compose.runtime.mutableStateOf",
            "androidx.compose.runtime.remember",
            "androidx.compose.runtime.setValue",
        )
        code = """
            var openDialog by remember { mutableStateOf(false) }
        
            if (openDialog) {
                val datePickerState = rememberDatePickerState()
                val confirmEnabled by derivedStateOf { datePickerState.selectedDateMillis != null }
                DatePickerDialog(
                    onDismissRequest = {
                        openDialog = false
                    },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                openDialog = false
                            },
                            enabled = confirmEnabled
                        ) {
                            Text("OK")
                        }
                    },
                    dismissButton = {
                        TextButton(
                            onClick = {
                                openDialog = false
                            }
                        ) {
                            Text("Cancel")
                        }
                    }
                ) {
                    DatePicker(state = datePickerState)
                }
            }
        """.trimIndent()
    }

    component {
        name = "Date Range Picker"
        specUrl = "https://m3.material.io/components/date-pickers/specs#d3189372-1b73-49d2-977e-e766f43a2774"
        guidelinesUrl = "https://m3.material.io/components/date-pickers/guidelines#b4c77e58-e85e-40da-8a6b-7d921832a7ad"
        docsUrl =
            "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#DateRangePicker(androidx.compose.material3.DateRangePickerState,androidx.compose.ui.Modifier,androidx.compose.material3.DatePickerFormatter,kotlin.Function0,kotlin.Function0,kotlin.Boolean,androidx.compose.material3.DatePickerColors)"
        description = """
            Date range selection provides a start and end date.
            Common use cases include:
            - Booking a flight
            - Reserving a hotel
            
            Mobile date range pickers navigate across dates in several ways:
            - To select a range of dates, tap the start and end dates on the calendar
            - To navigate across months, scroll vertically
        """.trimIndent()
        screenshot = "datepicker_range"
        imports = listOf(
            "androidx.compose.material3.rememberDateRangePickerState",
            "androidx.compose.material3.DateRangePicker",
        )
        code = """
            val state = rememberDateRangePickerState()
            DateRangePicker(state = state)
        """.trimIndent()
        customCode = """
            val state = rememberDateRangePickerState()
            DateRangePicker(
                modifier = Modifier.padding(horizontal = 16.dp),
                state = state,
                colors = DatePickerDefaults.colors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface,
                    headlineContentColor = MaterialTheme.colorScheme.onSurface,
                    weekdayContentColor = MaterialTheme.colorScheme.onSurface,
                    subheadContentColor = MaterialTheme.colorScheme.onSurface,
                    yearContentColor = MaterialTheme.colorScheme.onSurface,
                    currentYearContentColor = MaterialTheme.colorScheme.onSurface,
                    selectedYearContentColor = MaterialTheme.colorScheme.onSurface,
                    selectedYearContainerColor = MaterialTheme.colorScheme.surface,
                    dayContentColor = MaterialTheme.colorScheme.onSurface,
                    disabledDayContentColor = MaterialTheme.colorScheme.onSurface,
                    selectedDayContentColor = MaterialTheme.colorScheme.onPrimary,
                    disabledSelectedDayContentColor = MaterialTheme.colorScheme.onSurface,
                    selectedDayContainerColor = MaterialTheme.colorScheme.primary,
                    disabledSelectedDayContainerColor = MaterialTheme.colorScheme.surface,
                    todayContentColor = MaterialTheme.colorScheme.onSurface,
                    todayDateBorderColor = MaterialTheme.colorScheme.primary,
                    dayInSelectionRangeContentColor = MaterialTheme.colorScheme.onPrimary,
                    dayInSelectionRangeContainerColor = MaterialTheme.colorScheme.primary
                )
            )
        """.trimIndent()
    }
}