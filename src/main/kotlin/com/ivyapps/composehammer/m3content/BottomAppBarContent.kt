package com.ivyapps.composehammer.m3content

import com.ivyapps.composehammer.domain.ContentScope
import com.ivyapps.composehammer.domain.component
import com.ivyapps.composehammer.domain.group

fun ContentScope.bottomAppBar() = group("Bottom app bars") {
    component {
        name = "Bottom app bar"
        specUrl = "https://m3.material.io/components/bottom-app-bar/specs"
        guidelinesUrl = "https://m3.material.io/components/bottom-app-bar/guidelines"
        docsUrl =
            "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#BottomAppBar(androidx.compose.ui.Modifier,androidx.compose.ui.graphics.Color,androidx.compose.ui.graphics.Color,androidx.compose.ui.unit.Dp,androidx.compose.foundation.layout.PaddingValues,androidx.compose.foundation.layout.WindowInsets,kotlin.Function1)"
        screenshot = "bottom_app_bar"
        description = """
            Bottom app bars provide access to up to four actions, including the floating action button (FAB).
            Bottom app bars should be used for:
            + Mobile devices only
            + Screens with two to five actions
            
            >Bottom app bars shouldn't be used for:
            - Apps with a navigation bar
            - Screens with one or no actions
        """.trimIndent()
        imports = listOf(
            "androidx.compose.material3.BottomAppBar",
            "androidx.compose.material3.FloatingActionButton",
            "androidx.compose.material3.Icon",
            "androidx.compose.material3.IconButton",
            "androidx.compose.material.icons.Icons",
            "androidx.compose.material.icons.filled.Edit",
            "androidx.compose.material.icons.filled.Search",
            "androidx.compose.material.icons.filled.Star",
        )
        code = """
            BottomAppBar(
                actions = {
                    IconButton(
                        onClick = {
                            /* doSomething() */
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Search"
                        )
                    }
                    IconButton(
                        onClick = {
                            /* doSomething() */
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = "Star"
                        )
                    }
                },
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = {
                            /* Do something! */
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Compose"
                        )
                    }
                }
            )
        """.trimIndent()
        customCode = """
            BottomAppBar(
                modifier = Modifier,
                containerColor = MaterialTheme.colorScheme.tertiary,
                contentColor = MaterialTheme.colorScheme.onTertiary,
                tonalElevation = 8.dp,
                contentPadding = PaddingValues(all = 4.dp),
                windowInsets = WindowInsets.navigationBars,
                actions = {
                    IconButton(
                        onClick = {
                            /* doSomething() */
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Search"
                        )
                    }
                    IconButton(
                        onClick = {
                            /* doSomething() */
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = "Star"
                        )
                    }
                },
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = {
                            /* Do something! */
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add"
                        )
                    }
                }
            )
        """.trimIndent()
    }
}