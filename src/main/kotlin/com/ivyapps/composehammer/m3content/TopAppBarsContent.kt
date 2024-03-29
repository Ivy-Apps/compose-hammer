package com.ivyapps.composehammer.m3content

import com.ivyapps.composehammer.domain.ContentScope
import com.ivyapps.composehammer.domain.component
import com.ivyapps.composehammer.domain.group

fun ContentScope.topAppBars() = group("Top app bars") {
    component {
        name = "Center-aligned top app bar"
        shortName = "Center-aligned"
        specUrl = "https://m3.material.io/components/top-app-bar/specs#51ac0fae-61c2-4abc-b8f9-1167bf54e875"
        guidelinesUrl = "https://m3.material.io/components/top-app-bar/guidelines"
        docsUrl =
            "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#CenterAlignedTopAppBar(kotlin.Function0,androidx.compose.ui.Modifier,kotlin.Function0,kotlin.Function1,androidx.compose.foundation.layout.WindowInsets,androidx.compose.material3.TopAppBarColors,androidx.compose.material3.TopAppBarScrollBehavior)"
        screenshot = "topappbar_center_aligned"
        description = """
            Top app bars display information and actions at the top of a screen.
            This small top app bar has a header title that is horizontally aligned to the center.
            This CenterAlignedTopAppBar has slots for a title, navigation icon, and actions.
            A center aligned top app bar that uses a scrollBehavior to customize its nested scrolling behavior when working in conjunction with a scrolling content
        """.trimIndent()
        imports = listOf(
            "androidx.compose.foundation.layout.padding",
            "androidx.compose.foundation.lazy.LazyColumn",
            "androidx.compose.material.icons.Icons",
            "androidx.compose.material.icons.filled.Favorite",
            "androidx.compose.material.icons.filled.Menu",
            "androidx.compose.material3.CenterAlignedTopAppBar",
            "androidx.compose.material3.ExperimentalMaterial3Api",
            "androidx.compose.material3.Icon",
            "androidx.compose.material3.IconButton",
            "androidx.compose.material3.Scaffold",
            "androidx.compose.material3.Text",
            
            "androidx.compose.ui.Modifier",
            "androidx.compose.ui.text.style.TextOverflow",
        )
        code = """
            Scaffold(
                topBar = {
                    CenterAlignedTopAppBar(
                        title = {
                            Text(
                                text = "Centered TopAppBar",
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        },
                        navigationIcon = {
                            IconButton(onClick = { /* doSomething() */ }) {
                                Icon(
                                    imageVector = Icons.Filled.Menu,
                                    contentDescription = "Menu"
                                )
                            }
                        },
                        actions = {
                            IconButton(onClick = { /* doSomething() */ }) {
                                Icon(
                                    imageVector = Icons.Filled.Favorite,
                                    contentDescription = "Favorite"
                                )
                            }
                        }
                    )
                },
                content = { innerPadding ->
                    LazyColumn(modifier = Modifier.padding(innerPadding)) {
                        // Your content goes here
                    }
                }
            )
        }
        """.trimIndent()
        customCode = """
            Scaffold(
                modifier = Modifier,
                topBar = {
                    CenterAlignedTopAppBar(
                        modifier = Modifier,
                        title = {
                            Text(
                                text = "Centered TopAppBar",
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        },
                        navigationIcon = {
                            IconButton(onClick = { /* doSomething() */ }) {
                                Icon(
                                    imageVector = Icons.Filled.Menu,
                                    contentDescription = "Menu"
                                )
                            }
                        },
                        actions = {
                            IconButton(onClick = { /* doSomething() */ }) {
                                Icon(
                                    imageVector = Icons.Filled.Favorite,
                                    contentDescription = "Favorite"
                                )
                            }
                        },
                        colors = TopAppBarDefaults.centerAlignedTopAppBarColors()
                    )
                },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                content = { innerPadding ->
                    LazyColumn(
                        contentPadding = innerPadding,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        val list = (0..75).map { it.toString() }
    
                        items(count = list.size) {
                            Text(
                                text = list[it],
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp)
                            )
                        }
                    }
                }
            )
        """.trimIndent()
    }

    component {
        name = "Small top app bar"
        shortName = "Small"
        specUrl = "https://m3.material.io/components/top-app-bar/specs#14e23895-ac2e-40d8-b0f7-8d016c10a225"
        guidelinesUrl = "https://m3.material.io/components/top-app-bar/guidelines"
        docsUrl =
            "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#SmallTopAppBar(kotlin.Function0,androidx.compose.ui.Modifier,kotlin.Function0,kotlin.Function1,androidx.compose.foundation.layout.WindowInsets,androidx.compose.material3.TopAppBarColors,androidx.compose.material3.TopAppBarScrollBehavior)"
        screenshot = "topappbar"
        description = """
            Top app bars display information and actions at the top of a screen.
            This SmallTopAppBar has slots for a title, navigation icon, and actions.
        """.trimIndent()
        imports = listOf(
            "androidx.compose.foundation.lazy.LazyColumn",
            "androidx.compose.material.icons.Icons",
            "androidx.compose.material.icons.filled.Favorite",
            "androidx.compose.material.icons.filled.Menu",
            "androidx.compose.material3.ExperimentalMaterial3Api",
            "androidx.compose.material3.Icon",
            "androidx.compose.material3.IconButton",
            "androidx.compose.material3.Scaffold",
            "androidx.compose.material3.Text",
            "androidx.compose.material3.TopAppBar",
            
            "androidx.compose.ui.text.style.TextOverflow",
        )
        code = """
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                "Simple TopAppBar",
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        },
                        navigationIcon = {
                            IconButton(onClick = { /* doSomething() */ }) {
                                Icon(
                                    imageVector = Icons.Filled.Menu,
                                    contentDescription = "Localized description"
                                )
                            }
                        },
                        actions = {
                            IconButton(onClick = { /* doSomething() */ }) {
                                Icon(
                                    imageVector = Icons.Filled.Favorite,
                                    contentDescription = "Localized description"
                                )
                            }
                        }
                    )
                },
                content = { innerPadding ->
                    LazyColumn(
                        contentPadding = innerPadding,
                    ) {
                        // Your content goes here
                    }
                }
            )
        """.trimIndent()
        customCode = """
            Scaffold(
                modifier = Modifier,
                topBar = {
                    TopAppBar(
                        modifier = Modifier,
                        title = {
                            Text(
                                "Simple TopAppBar",
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        },
                        navigationIcon = {
                            IconButton(onClick = { /* doSomething() */ }) {
                                Icon(
                                    imageVector = Icons.Filled.Menu,
                                    contentDescription = "Localized description"
                                )
                            }
                        },
                        actions = {
                            IconButton(onClick = { /* doSomething() */ }) {
                                Icon(
                                    imageVector = Icons.Filled.Favorite,
                                    contentDescription = "Localized description"
                                )
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            scrolledContainerColor = MaterialTheme.colorScheme.primaryContainer,
                            navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                            actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    )
                },
                content = { innerPadding ->
                    LazyColumn(
                        contentPadding = innerPadding,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        val list = (0..75).map { it.toString() }
    
                        items(count = list.size) {
                            Text(
                                text = list[it],
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp)
                            )
                        }
                    }
                }
            )
        """.trimIndent()
    }

    component {
        name = "Medium top app bar"
        shortName = "Medium"
        specUrl = "https://m3.material.io/components/top-app-bar/specs#e3fd3eba-0444-437c-9a82-071ef03d85b1"
        guidelinesUrl = "https://m3.material.io/components/top-app-bar/guidelines"
        docsUrl =
            "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#MediumTopAppBar(kotlin.Function0,androidx.compose.ui.Modifier,kotlin.Function0,kotlin.Function1,androidx.compose.foundation.layout.WindowInsets,androidx.compose.material3.TopAppBarColors,androidx.compose.material3.TopAppBarScrollBehavior)"
        screenshot = "topappbar_medium"
        description = """
            Top app bars display information and actions at the top of a screen.
            This MediumTopAppBar has slots for a title, navigation icon, and actions. In its default expanded state, the title is displayed in a second row under the navigation and actions.
            A medium top app bar that uses a scrollBehavior to customize its nested scrolling behavior when working in conjunction with scrolling content.
        """.trimIndent()
        imports = listOf(
            "androidx.compose.foundation.lazy.LazyColumn",
            "androidx.compose.material.icons.Icons",
            "androidx.compose.material.icons.filled.Favorite",
            "androidx.compose.material.icons.filled.Menu",
            "androidx.compose.material3.ExperimentalMaterial3Api",
            "androidx.compose.material3.Icon",
            "androidx.compose.material3.IconButton",
            "androidx.compose.material3.MediumTopAppBar",
            "androidx.compose.material3.Scaffold",
            "androidx.compose.material3.Text",
            "androidx.compose.material3.TopAppBarDefaults",
            
            "androidx.compose.ui.Modifier",
            "androidx.compose.ui.input.nestedscroll.nestedScroll",
            "androidx.compose.ui.text.style.TextOverflow",
        )
        code = """
            val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    
            Scaffold(
                modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                topBar = {
                    MediumTopAppBar(
                        title = {
                            Text(
                                "Medium TopAppBar",
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        },
                        navigationIcon = {
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
                        },
                        actions = {
                            IconButton(
                                onClick = {
                                    /* doSomething() */
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Favorite,
                                    contentDescription = "Favorite"
                                )
                            }
                        },
                        scrollBehavior = scrollBehavior
                    )
                },
                content = { innerPadding ->
                    LazyColumn(
                        contentPadding = innerPadding,
                    ) {
                        // Your content goes here
                    }
                }
            )
        """.trimIndent()
        customCode = """
            val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    
            Scaffold(
                modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                topBar = {
                    MediumTopAppBar(
                        modifier = Modifier,
                        title = {
                            Text(
                                "Medium TopAppBar",
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        },
                        navigationIcon = {
                            IconButton(onClick = {
                                /* doSomething() */
                            }
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Menu,
                                    contentDescription = "Menu"
                                )
                            }
                        },
                        actions = {
                            IconButton(onClick = {
                                /* doSomething() */
                            }
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Favorite,
                                    contentDescription = "Favorite"
                                )
                            }
                        },
                        colors = TopAppBarDefaults.mediumTopAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            scrolledContainerColor = MaterialTheme.colorScheme.primaryContainer,
                            navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                            actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                        ),
                        scrollBehavior = scrollBehavior
                    )
                },
                content = { innerPadding ->
                    LazyColumn(
                        contentPadding = innerPadding,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        val list = (0..75).map { it.toString() }
    
                        items(count = list.size) {
                            Text(
                                text = list[it],
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp)
                            )
                        }
                    }
                }
            )
        """.trimIndent()
    }

    component {
        name = "Large top app bar"
        shortName = "Large"
        specUrl = "https://m3.material.io/components/top-app-bar/specs#8140aaaf-5729-4368-a0f5-baef8d576dbf"
        guidelinesUrl = "https://m3.material.io/components/top-app-bar/guidelines"
        docsUrl =
            "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#LargeTopAppBar(kotlin.Function0,androidx.compose.ui.Modifier,kotlin.Function0,kotlin.Function1,androidx.compose.foundation.layout.WindowInsets,androidx.compose.material3.TopAppBarColors,androidx.compose.material3.TopAppBarScrollBehavior)"
        screenshot = "topappbar_large"
        description = """
            Top app bars display information and actions at the top of a screen.
            This LargeTopAppBar has slots for a title, navigation icon, and actions. In its default expanded state, the title is displayed in a second row under the navigation and actions.

            A large top app bar that uses a scrollBehavior to customize its nested scrolling behavior when working in conjunction with scrolling content.
        """.trimIndent()
        imports = listOf(
            "androidx.compose.foundation.lazy.LazyColumn",
            "androidx.compose.material.icons.Icons",
            "androidx.compose.material.icons.filled.Favorite",
            "androidx.compose.material.icons.filled.Menu",
            "androidx.compose.material3.ExperimentalMaterial3Api",
            "androidx.compose.material3.Icon",
            "androidx.compose.material3.IconButton",
            "androidx.compose.material3.LargeTopAppBar",
            "androidx.compose.material3.Scaffold",
            "androidx.compose.material3.Text",
            "androidx.compose.material3.TopAppBarDefaults",
            
            "androidx.compose.ui.Modifier",
            "androidx.compose.ui.input.nestedscroll.nestedScroll",
            "androidx.compose.ui.text.style.TextOverflow",
        )
        code = """
            val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    
            Scaffold(
                modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                topBar = {
                    LargeTopAppBar(
                        title = {
                            Text(
                                "Large TopAppBar",
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        },
                        navigationIcon = {
                            IconButton(onClick = {
                                /* doSomething() */
                            }) {
                                Icon(
                                    imageVector = Icons.Filled.Menu,
                                    contentDescription = "Menu"
                                )
                            }
                        },
                        actions = {
                            IconButton(onClick = {
                                /* doSomething() */
                            }) {
                                Icon(
                                    imageVector = Icons.Filled.Favorite,
                                    contentDescription = "Favorite"
                                )
                            }
                        },
                        scrollBehavior = scrollBehavior
                    )
                },
                content = { innerPadding ->
                    LazyColumn(
                        contentPadding = innerPadding,
                    ) {
                        // You content goes here
                    }
                }
            )
        """.trimIndent()

        customCode = """
            val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    
            Scaffold(
                modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                topBar = {
                    LargeTopAppBar(
                        modifier = Modifier,
                        title = {
                            Text(
                                "Large TopAppBar",
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        },
                        navigationIcon = {
                            IconButton(onClick = {
                                /* doSomething() */
                            }
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Menu,
                                    contentDescription = "Menu"
                                )
                            }
                        },
                        actions = {
                            IconButton(onClick = {
                                /* doSomething() */
                            }) {
                                Icon(
                                    imageVector = Icons.Filled.Favorite,
                                    contentDescription = "Favorite"
                                )
                            }
                        },
                        colors = TopAppBarDefaults.largeTopAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            scrolledContainerColor = MaterialTheme.colorScheme.primaryContainer,
                            navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                            actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                        ),
                        scrollBehavior = scrollBehavior
                    )
                },
                content = { innerPadding ->
                    LazyColumn(
                        contentPadding = innerPadding,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        val list = (0..75).map { it.toString() }
    
                        items(count = list.size) {
                            Text(
                                text = list[it],
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp)
                            )
                        }
                    }
                }
            )
        """.trimIndent()
    }
}