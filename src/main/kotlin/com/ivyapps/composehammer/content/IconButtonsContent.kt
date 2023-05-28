package com.ivyapps.composehammer.content

import com.ivyapps.composehammer.domain.ContentScope
import com.ivyapps.composehammer.domain.component
import com.ivyapps.composehammer.domain.group

fun ContentScope.iconButtons() = group("Icon buttons") {
    component {
        name = "Standard icon button"
        shortName = "Standard"
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

    component {
        name = "Filled icon button"
        shortName = "Filled"
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
        name = "Filled tonal icon button"
        shortName = "Filled tonal"
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
        name = "Outlined icon button"
        shortName = "Outlined"
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
}
