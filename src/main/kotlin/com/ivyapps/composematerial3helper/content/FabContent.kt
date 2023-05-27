package com.ivyapps.composematerial3helper.content

import com.ivyapps.composematerial3helper.services.ContentScope
import com.ivyapps.composematerial3helper.services.component
import com.ivyapps.composematerial3helper.services.group

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
