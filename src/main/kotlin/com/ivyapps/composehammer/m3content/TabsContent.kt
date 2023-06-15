package com.ivyapps.composehammer.m3content

import com.ivyapps.composehammer.domain.ContentScope
import com.ivyapps.composehammer.domain.component
import com.ivyapps.composehammer.domain.group

fun ContentScope.tabs() = group("Tabs") {
    component {
        name = "Tabs"
        specUrl = "https://m3.material.io/components/tabs/specs"
        guidelinesUrl = "https://m3.material.io/components/tabs/guidelines"
        docsUrl =
            "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#Tab(kotlin.Boolean,kotlin.Function0,androidx.compose.ui.Modifier,kotlin.Boolean,androidx.compose.ui.graphics.Color,androidx.compose.ui.graphics.Color,androidx.compose.foundation.interaction.MutableInteractionSource,kotlin.Function1)"
        screenshot = "tabrow"
        description = """
            Tabs organize groups of related content that are at the same level of hierarchy.
            There are two types of tabs.
            >Primary tabs are placed at the top of the content pane under a top app bar. They display the main content destinations.
            >Secondary tabs are used within a content area to further separate related content and establish hierarchy.
        """.trimIndent()
        imports = listOf(
            "androidx.compose.material3.Tab",
            "androidx.compose.material3.TabRow",
            "androidx.compose.material3.Text",
            
            "androidx.compose.runtime.getValue",
            "androidx.compose.runtime.mutableStateOf",
            "androidx.compose.runtime.remember",
            "androidx.compose.runtime.setValue",
            "androidx.compose.ui.text.style.TextOverflow",
        )
        code = """
            var state by remember { mutableStateOf(0) }
            val titles = listOf("Tab 1", "Tab 2", "Tab 3")
    
            TabRow(selectedTabIndex = state) {
                titles.forEachIndexed { index, title ->
                    Tab(
                        selected = state == index,
                        onClick = { state = index },
                        text = {
                            Text(
                                text = title,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    )
                }
            }
        """.trimIndent()
        customCode = """
            var state by remember { mutableStateOf(0) }
            val titles = listOf("Tab 1", "Tab 2", "Tab 3")
    
            Column {
                TabRow(selectedTabIndex = state) {
                    titles.forEachIndexed { index, title ->
                        Tab(
                            modifier = Modifier,
                            selected = state == index,
                            onClick = { state = index },
                            text = {
                                Text(
                                    text = title,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis
                                )
                            },
                            icon = {
                                Icon(
                                    imageVector = when (index) {
                                        0 -> Icons.Default.Home
                                        1 -> Icons.Default.ShoppingCart
                                        else -> Icons.Default.Favorite
                                    },
                                    contentDescription = null
                                )
                            },
                            enabled = true,
                            selectedContentColor = MaterialTheme.colorScheme.primary,
                            unselectedContentColor = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
            }
        """.trimIndent()
    }
}