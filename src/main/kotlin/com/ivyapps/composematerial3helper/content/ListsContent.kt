package com.ivyapps.composematerial3helper.content

import com.ivyapps.composematerial3helper.domain.ContentScope
import com.ivyapps.composematerial3helper.domain.component
import com.ivyapps.composematerial3helper.domain.group

fun ContentScope.lists() = group("Lists") {
    component {
        name = "List item"
        specUrl = "https://m3.material.io/components/lists/specs"
        guidelinesUrl = "https://m3.material.io/components/lists/guidelines"
        docsUrl =
            "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#ListItem(kotlin.Function0,androidx.compose.ui.Modifier,kotlin.Function0,kotlin.Function0,kotlin.Function0,kotlin.Function0,androidx.compose.material3.ListItemColors,androidx.compose.ui.unit.Dp,androidx.compose.ui.unit.Dp)"
        screenshot = "list"
        description = """
            Lists are vertically organized groups of text and images. 
             
            Optimized for reading comprehension, a list consists of a single continuous column of rows, with each row representing a list item.
             
            List items can contain primary and supplemental actions represented by icons and text.
        """.trimIndent()
        imports = listOf(
            "androidx.compose.material.icons.Icons",
            "androidx.compose.material.icons.filled.Info",
            "androidx.compose.material3.Icon",
            "androidx.compose.material3.ListItem",
            "androidx.compose.material3.Text",
        )
        code = """
            ListItem(
                headlineContent = { Text("One line list item with 24x24 icon") },
                leadingContent = {
                    Icon(
                        imageVector = Icons.Filled.Info,
                        contentDescription = "Info"
                    )
                }
            )
        """.trimIndent()
        customCode = """
            Column {
                ListItem(
                    modifier = Modifier,
                    headlineContent = { Text("One line list item with 24x24 icon") },
                    leadingContent = {
                        Icon(
                            imageVector = Icons.Filled.Info,
                            contentDescription = "Info"
                        )
                    },
                    colors = ListItemDefaults.colors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        headlineColor = MaterialTheme.colorScheme.onSecondaryContainer,
                        leadingIconColor = MaterialTheme.colorScheme.onSecondaryContainer,
                        overlineColor = MaterialTheme.colorScheme.onSecondaryContainer,
                        supportingColor = MaterialTheme.colorScheme.onTertiary,
                        disabledHeadlineColor = MaterialTheme.colorScheme.onTertiaryContainer,
                        disabledLeadingIconColor = MaterialTheme.colorScheme.onTertiary
                    ),
                    tonalElevation = 4.dp,
                    shadowElevation = 4.dp
                )
    
                ListItem(
                    modifier = Modifier,
                    headlineContent = { Text("One line list item with 24x24 icon") },
                    leadingContent = {
                        Icon(
                            imageVector = Icons.Filled.AccountCircle,
                            contentDescription = "Account"
                        )
                    },
                    colors = ListItemDefaults.colors(
                        containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                        headlineColor = MaterialTheme.colorScheme.onTertiaryContainer,
                        leadingIconColor = MaterialTheme.colorScheme.onTertiaryContainer,
                        overlineColor = MaterialTheme.colorScheme.onTertiaryContainer,
                        supportingColor = MaterialTheme.colorScheme.onTertiary,
                        disabledHeadlineColor = MaterialTheme.colorScheme.onTertiaryContainer,
                        disabledLeadingIconColor = MaterialTheme.colorScheme.onTertiary
                    ),
                    tonalElevation = 4.dp,
                    shadowElevation = 4.dp
                )
    
                Divider()
            }
        """.trimIndent()
    }
}