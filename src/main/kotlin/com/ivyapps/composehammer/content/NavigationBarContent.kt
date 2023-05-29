package com.ivyapps.composehammer.content

import com.ivyapps.composehammer.domain.ContentScope
import com.ivyapps.composehammer.domain.component
import com.ivyapps.composehammer.domain.group

fun ContentScope.navigationBars() = group("Navigation bars") {
    component {
        name = "Navigation bar"
        specUrl = "https://m3.material.io/components/navigation-bar/specs"
        guidelinesUrl = "https://m3.material.io/components/navigation-bar/guidelines"
        docsUrl =
            "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#NavigationBar(androidx.compose.ui.Modifier,androidx.compose.ui.graphics.Color,androidx.compose.ui.graphics.Color,androidx.compose.ui.unit.Dp,androidx.compose.foundation.layout.WindowInsets,kotlin.Function1)"
        screenshot = "navigationbar"
        description = """
            Navigation bars (nav bars) provide access to three to five destinations.

            The nav bar is positioned at the bottom of screens for convenient access. Each destination is represented by an icon and optional text label.

            When a navigation bar icon is tapped or focused, the user is taken to the navigation destination associated with that icon.
        """.trimIndent()
        imports = listOf(
            "androidx.compose.material.icons.Icons",
            "androidx.compose.material.icons.filled.Favorite",
            "androidx.compose.material3.Icon",
            "androidx.compose.material3.NavigationBar",
            "androidx.compose.material3.NavigationBarItem",
            "androidx.compose.material3.Text",
            
            "androidx.compose.runtime.getValue",
            "androidx.compose.runtime.mutableStateOf",
            "androidx.compose.runtime.remember",
            "androidx.compose.runtime.setValue",
        )
        code = """
            var selectedItem by remember { mutableStateOf(0) }
            val items = listOf("Songs", "Artists", "Playlists")
    
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = { Icon(imageVector = Icons.Filled.Favorite, contentDescription = item) },
                        label = { Text(item) },
                        selected = selectedItem == index,
                        onClick = { selectedItem = index }
                    )
                }
            }
        """.trimIndent()
        customCode = """
            var selectedItem by remember { mutableStateOf(0) }
            val items = listOf("Songs", "Artists", "Playlists")
    
            NavigationBar(
                modifier = Modifier,
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                tonalElevation = 4.dp
            ) {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        modifier = Modifier,
                        alwaysShowLabel = false,
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                            indicatorColor = MaterialTheme.colorScheme.primary,
                            unselectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                            unselectedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
                            disabledIconColor = MaterialTheme.colorScheme.scrim,
                            disabledTextColor = MaterialTheme.colorScheme.scrim,
                        ),
                        icon = { Icon(imageVector = Icons.Filled.Favorite, contentDescription = item) },
                        label = { Text(item) },
                        selected = selectedItem == index,
                        onClick = { selectedItem = index }
                    )
                }
            }
        """.trimIndent()
    }
}