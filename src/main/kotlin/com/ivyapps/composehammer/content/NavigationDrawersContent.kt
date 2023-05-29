package com.ivyapps.composehammer.content

import com.ivyapps.composehammer.domain.ContentScope
import com.ivyapps.composehammer.domain.component
import com.ivyapps.composehammer.domain.group

fun ContentScope.navigationDrawers() = group("Navigation drawers") {
    component {
        name = "Modal navigation drawer"
        shortName = "Modal nav drawer"
        specUrl = "https://m3.material.io/components/navigation-drawer/specs"
        guidelinesUrl = "https://m3.material.io/components/navigation-drawer/guidelines"
        docsUrl =
            "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#ModalNavigationDrawer(kotlin.Function0,androidx.compose.ui.Modifier,androidx.compose.material3.DrawerState,kotlin.Boolean,androidx.compose.ui.graphics.Color,kotlin.Function0)"
        screenshot = "navigationdrawer_modal"
        description = """
            Navigation drawers provide ergonomic access to destinations in an app.

            Modal navigation drawers block interaction with the rest of an app’s content with a scrim. They are elevated above most of the app’s UI and don’t affect the screen’s layout grid.
        """.trimIndent()
        imports = listOf(
            "androidx.compose.foundation.layout.Spacer",
            "androidx.compose.foundation.layout.height",
            "androidx.compose.foundation.layout.padding",
            "androidx.compose.material.icons.Icons",
            "androidx.compose.material.icons.filled.Email",
            "androidx.compose.material.icons.filled.Face",
            "androidx.compose.material.icons.filled.Favorite",
            "androidx.compose.material3.DrawerValue",
            "androidx.compose.material3.Icon",
            "androidx.compose.material3.ModalDrawerSheet",
            "androidx.compose.material3.ModalNavigationDrawer",
            "androidx.compose.material3.NavigationDrawerItem",
            "androidx.compose.material3.NavigationDrawerItemDefaults",
            "androidx.compose.material3.Text",
            "androidx.compose.material3.rememberDrawerState",
            
            "androidx.compose.runtime.mutableStateOf",
            "androidx.compose.runtime.remember",
            "androidx.compose.runtime.rememberCoroutineScope",
            "androidx.compose.ui.Modifier",
            "androidx.compose.ui.unit.dp",
            "kotlinx.coroutines.launch",
        )
        code = """
            val drawerState = rememberDrawerState(DrawerValue.Closed)
            val scope = rememberCoroutineScope()
            val items = listOf(Icons.Default.Favorite, Icons.Default.Face, Icons.Default.Email)
            val selectedItem = remember { mutableStateOf(items[0]) }
    
            ModalNavigationDrawer(
                drawerState = drawerState,
                drawerContent = {
                    ModalDrawerSheet {
                        Spacer(Modifier.height(12.dp))
    
                        items.forEach { item ->
                            NavigationDrawerItem(
                                icon = { Icon(imageVector = item, contentDescription = null) },
                                label = { Text(item.name) },
                                selected = item == selectedItem.value,
                                onClick = {
                                    scope.launch { drawerState.close() }
                                    selectedItem.value = item
                                },
                                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                            )
                        }
                    }
                },
                content = {
                    // Screen's content
                }
            )
        """.trimIndent()
        customCode = """
            val drawerState = rememberDrawerState(DrawerValue.Closed)
            val scope = rememberCoroutineScope()
            val items = listOf(Icons.Default.Favorite, Icons.Default.Face, Icons.Default.Email)
            val selectedItem = remember { mutableStateOf(items[0]) }
    
            ModalNavigationDrawer(
                modifier = Modifier,
                drawerState = drawerState,
                drawerContent = {
                    ModalDrawerSheet {
                        Spacer(Modifier.height(12.dp))
    
                        items.forEach { item ->
                            NavigationDrawerItem(
                                icon = { Icon(imageVector = item, contentDescription = null) },
                                label = { Text(item.name) },
                                selected = item == selectedItem.value,
                                onClick = {
                                    scope.launch { drawerState.close() }
                                    selectedItem.value = item
                                },
                                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                            )
                        }
                    }
                },
                scrimColor = MaterialTheme.colorScheme.scrim,
                content = {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = if (drawerState.isClosed) ">>> Swipe >>>" else "<<< Swipe <<<")
    
                        Spacer(Modifier.height(20.dp))
    
                        Button(onClick = { scope.launch { drawerState.open() } }) {
                            Text("Click to open")
                        }
                    }
                }
            )
        """.trimIndent()
    }

    component {
        name = "Dismissible Navigation drawer"
        shortName = "Dismissible nav drawer"
        specUrl = "https://m3.material.io/components/navigation-drawer/specs"
        guidelinesUrl = "https://m3.material.io/components/navigation-drawer/guidelines"
        docsUrl =
            "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#DismissibleNavigationDrawer(kotlin.Function0,androidx.compose.ui.Modifier,androidx.compose.material3.DrawerState,kotlin.Boolean,kotlin.Function0)"
        screenshot = "navigationdrawer_dismissible"
        description = """
            Navigation drawers provide ergonomic access to destinations in an app. They’re often next to app content and affect the screen’s layout grid.
        
            Dismissible standard drawers can be used for layouts that prioritize content (such as a photo gallery) or for apps where users are unlikely to switch destinations often. They should use a visible navigation menu icon to open and close the drawer.
        """.trimIndent()
        imports = listOf(
            "androidx.activity.compose.BackHandler",
            "androidx.compose.foundation.layout.Spacer",
            "androidx.compose.foundation.layout.height",
            "androidx.compose.foundation.layout.padding",
            "androidx.compose.material.icons.Icons",
            "androidx.compose.material.icons.filled.Email",
            "androidx.compose.material.icons.filled.Face",
            "androidx.compose.material.icons.filled.Favorite",
            "androidx.compose.material3.DismissibleDrawerSheet",
            "androidx.compose.material3.DismissibleNavigationDrawer",
            "androidx.compose.material3.DrawerValue",
            "androidx.compose.material3.Icon",
            "androidx.compose.material3.NavigationDrawerItem",
            "androidx.compose.material3.Text",
            "androidx.compose.material3.rememberDrawerState",
            
            "androidx.compose.runtime.mutableStateOf",
            "androidx.compose.runtime.remember",
            "androidx.compose.runtime.rememberCoroutineScope",
            "androidx.compose.ui.Modifier",
            "androidx.compose.ui.unit.dp",
            "kotlinx.coroutines.launch",
        )
        code = """
            val drawerState = rememberDrawerState(DrawerValue.Closed)
            val scope = rememberCoroutineScope()
            val items = listOf(Icons.Default.Favorite, Icons.Default.Face, Icons.Default.Email)
            val selectedItem = remember { mutableStateOf(items[0]) }
    
            BackHandler(enabled = drawerState.isOpen) {
                scope.launch {
                    drawerState.close()
                }
            }
    
            DismissibleNavigationDrawer(
                drawerState = drawerState,
                drawerContent = {
                    DismissibleDrawerSheet {
                        Spacer(Modifier.height(12.dp))
    
                        items.forEach { item ->
                            NavigationDrawerItem(
                                icon = { Icon(imageVector = item, contentDescription = null) },
                                label = { Text(item.name) },
                                selected = item == selectedItem.value,
                                onClick = {
                                    scope.launch { drawerState.close() }
                                    selectedItem.value = item
                                },
                                modifier = Modifier.padding(horizontal = 12.dp)
                            )
                        }
                    }
                },
                content = {
                    // Screen content
                }
            )
        """.trimIndent()
        customCode = """
            val drawerState = rememberDrawerState(DrawerValue.Closed)
            val scope = rememberCoroutineScope()
            val items = listOf(Icons.Default.Favorite, Icons.Default.Face, Icons.Default.Email)
            val selectedItem = remember { mutableStateOf(items[0]) }
    
            BackHandler(enabled = drawerState.isOpen) {
                scope.launch {
                    drawerState.close()
                }
            }
    
            DismissibleNavigationDrawer(
                drawerState = drawerState,
                drawerContent = {
                    DismissibleDrawerSheet(
                        modifier = Modifier,
                        drawerShape = RoundedCornerShape(12.dp),
                        drawerContainerColor = MaterialTheme.colorScheme.primaryContainer,
                        drawerContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        drawerTonalElevation = 4.dp
                    ) {
                        Spacer(Modifier.height(12.dp))
    
                        items.forEach { item ->
                            NavigationDrawerItem(
                                icon = { Icon(imageVector = item, contentDescription = null) },
                                label = { Text(item.name) },
                                selected = item == selectedItem.value,
                                onClick = {
                                    scope.launch { drawerState.close() }
                                    selectedItem.value = item
                                },
                                modifier = Modifier.padding(horizontal = 12.dp)
                            )
                        }
                    }
                },
                content = {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = if (drawerState.isClosed) ">>> Swipe >>>" else "<<< Swipe <<<")
                        Spacer(Modifier.height(20.dp))
                        Button(onClick = { scope.launch { drawerState.open() } }) {
                            Text("Click to open")
                        }
                    }
                }
            )
        """.trimIndent()
    }
}