package com.ivyapps.composematerial3helper.content

import com.ivyapps.composematerial3helper.domain.ContentScope
import com.ivyapps.composematerial3helper.domain.component
import com.ivyapps.composematerial3helper.domain.group

fun ContentScope.tooltips() = group("Tooltips") {
    component {
        name = "Plain tooltip"
        specUrl = "https://m3.material.io/components/tooltips/specs#92c84fef-92fe-4662-b837-f70eaa9b64f3"
        guidelinesUrl = "https://m3.material.io/components/tooltips/guidelines"
        docsUrl =
            "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#PlainTooltipBox(kotlin.Function0,androidx.compose.ui.Modifier,androidx.compose.material3.PlainTooltipState,androidx.compose.ui.graphics.Shape,androidx.compose.ui.graphics.Color,androidx.compose.ui.graphics.Color,kotlin.Function1)"
        screenshot = "tooltip_plain"
        description = """
            A tooltip provides additional context for a UI element. 
            Plain tooltips briefly describe a UI element. They're best used for labelling UI elements with no text, like icon-only buttons and fields.
        """.trimIndent()
        imports = listOf(
            "androidx.compose.material.icons.Icons",
            "androidx.compose.material.icons.filled.Favorite",
            "androidx.compose.material3.ExperimentalMaterial3Api",
            "androidx.compose.material3.Icon",
            "androidx.compose.material3.IconButton",
            "androidx.compose.material3.PlainTooltipBox",
            "androidx.compose.material3.PlainTooltipState",
            "androidx.compose.material3.Text",
            "androidx.compose.runtime.Composable",
            "androidx.compose.runtime.LaunchedEffect",
            "androidx.compose.runtime.remember",
            "androidx.compose.ui.Modifier",
        )
        code = """
            val tooltipState = remember { PlainTooltipState() }
            LaunchedEffect(Unit) {
                // this is how you show a tooltip
                // needs a coroutine scope
                tooltipState.show()
            }
    
            PlainTooltipBox(
                tooltip = {
                    Text("Add to favorites")
                },
                tooltipState = tooltipState
            ) {
                IconButton(
                    onClick = {
                        /* Icon button's click event */
                    },
                    modifier = Modifier.tooltipAnchor() // <-- show the tooltip here
                ) {
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = "Favorite"
                    )
                }
            }
        """.trimIndent()
        customCode = """
            val tooltipState = remember { PlainTooltipState() }
            LaunchedEffect(Unit) { tooltipState.show() }
    
            PlainTooltipBox(
                modifier = Modifier,
                tooltip = {
                    Text("Add to favorites")
                },
                tooltipState = tooltipState,
                shape = RoundedCornerShape(12.dp),
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer
            ) {
                IconButton(
                    onClick = {
                        /* Icon button's click event */
                    },
                    modifier = Modifier.tooltipAnchor(),
                    enabled = true,
                    colors = IconButtonDefaults.filledIconButtonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary,
                        disabledContainerColor = MaterialTheme.colorScheme.secondary,
                        disabledContentColor = MaterialTheme.colorScheme.onSecondary
                    )
                ) {
                    Icon(
                        modifier = Modifier,
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = "Favorite",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        """.trimIndent()
    }
}