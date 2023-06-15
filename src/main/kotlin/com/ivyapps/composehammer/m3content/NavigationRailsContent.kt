package com.ivyapps.composehammer.m3content

import com.ivyapps.composehammer.domain.ContentScope
import com.ivyapps.composehammer.domain.component
import com.ivyapps.composehammer.domain.group

fun ContentScope.navigationRails() = group("Navigation rails") {
    component {
        name = "Navigation rail"
        specUrl = "https://m3.material.io/components/navigation-rail/specs"
        guidelinesUrl = "https://m3.material.io/components/navigation-rail/guidelines"
        docsUrl =
            "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#NavigationRail(androidx.compose.ui.Modifier,androidx.compose.ui.graphics.Color,androidx.compose.ui.graphics.Color,kotlin.Function1,androidx.compose.foundation.layout.WindowInsets,kotlin.Function1)"
        screenshot = "navigationrail"
        description = """
            The rail is a side navigation component that displays three to seven app destinations and, optionally, a floating action button (FAB). Each destination is represented by an icon and label text.

            The rail can serve as the sole navigation component on larger screen sizes, such as desktops and tablets.

            When your product is used on small screens, the navigation rail should be swapped for a navigation bar. When your product is used on desktop screens, the navigation rail can be swapped for a navigation drawer.
        """.trimIndent()
        imports = listOf(
            "androidx.compose.material.icons.Icons",
            "androidx.compose.material.icons.filled.Home",
            "androidx.compose.material.icons.filled.Search",
            "androidx.compose.material.icons.filled.Settings",
            "androidx.compose.material3.Icon",
            "androidx.compose.material3.NavigationRail",
            "androidx.compose.material3.NavigationRailItem",
            "androidx.compose.material3.Text",
            
            "androidx.compose.runtime.getValue",
            "androidx.compose.runtime.mutableStateOf",
            "androidx.compose.runtime.remember",
            "androidx.compose.runtime.setValue",
        )
        code = """
            var selectedItem by remember { mutableStateOf(0) }
            val items = listOf("Home", "Search", "Settings")
            val icons = listOf(Icons.Filled.Home, Icons.Filled.Search, Icons.Filled.Settings)
    
            NavigationRail {
                items.forEachIndexed { index, item ->
                    NavigationRailItem(
                        icon = { Icon(imageVector = icons[index], contentDescription = item) },
                        label = { Text(item) },
                        selected = selectedItem == index,
                        onClick = { selectedItem = index }
                    )
                }
            }
        """.trimIndent()
        customCode = """
            var selectedItem by remember { mutableStateOf(0) }
            val items = listOf("Home", "Search", "Settings")
            val icons = listOf(Icons.Filled.Home, Icons.Filled.Search, Icons.Filled.Settings)
    
            NavigationRail(
                modifier = Modifier,
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                header = {
                    Text(text = "Menu")
                }
            ) {
                items.forEachIndexed { index, item ->
                    NavigationRailItem(
                        modifier = Modifier,
                        icon = { Icon(imageVector = icons[index], contentDescription = item) },
                        label = { Text(item) },
                        selected = selectedItem == index,
                        onClick = { selectedItem = index },
                        enabled = true,
                        colors = NavigationRailItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                            indicatorColor = MaterialTheme.colorScheme.primary,
                            unselectedIconColor = MaterialTheme.colorScheme.tertiary,
                            unselectedTextColor = MaterialTheme.colorScheme.tertiary,
                            disabledIconColor = MaterialTheme.colorScheme.tertiary,
                            disabledTextColor = MaterialTheme.colorScheme.tertiary
                        )
                    )
                }
            }
        """.trimIndent()
    }
}