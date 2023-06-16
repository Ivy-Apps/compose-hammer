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
            val tabs = remember { listOf("Tab 1", "Tab 2", "Tab 3") }
            var selectedTabIndex by remember { mutableStateOf(0) }
        
            TabRow(selectedTabIndex = selectedTabIndex) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
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

    component {
        name = "Tabs with pager"
        specUrl = "https://m3.material.io/components/tabs/specs"
        guidelinesUrl = "https://m3.material.io/components/tabs/guidelines"
        docsUrl =
            "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#Tab(kotlin.Boolean,kotlin.Function0,androidx.compose.ui.Modifier,kotlin.Boolean,androidx.compose.ui.graphics.Color,androidx.compose.ui.graphics.Color,androidx.compose.foundation.interaction.MutableInteractionSource,kotlin.Function1)"
        screenshot = "tabrow"
        description = """
            Tabs organize groups of related content that are at the same level of hierarchy.
            This component also includes a HorizontalPager which allows the user to swipe
            between tabs.
        """.trimIndent()
        imports = listOf(
            "androidx.compose.foundation.ExperimentalFoundationApi",
            "androidx.compose.foundation.layout.Box",
            "androidx.compose.foundation.layout.Column",
            "androidx.compose.foundation.layout.fillMaxSize",
            "androidx.compose.foundation.pager.HorizontalPager",
            "androidx.compose.foundation.pager.PageSize",
            "androidx.compose.foundation.pager.rememberPagerState",
            "androidx.compose.material3.MaterialTheme",
            "androidx.compose.material3.Tab",
            "androidx.compose.material3.TabRow",
            "androidx.compose.material3.Text",
            "androidx.compose.runtime.derivedStateOf",
            "androidx.compose.runtime.getValue",
            "androidx.compose.runtime.remember",
            "androidx.compose.runtime.rememberCoroutineScope",
            "androidx.compose.ui.Alignment",
            "androidx.compose.ui.Modifier",
            "androidx.compose.ui.text.style.TextAlign",
            "androidx.compose.ui.text.style.TextOverflow",
            "kotlinx.coroutines.launch",
        )
        code = """
            Column(
                modifier = Modifier,
            ) {
                val tabs = remember { listOf("Tab 1", "Tab 2", "Tab 3") }
                val pagerState = rememberPagerState()
                val currentTabIndex by remember { derivedStateOf { pagerState.currentPage } }
        
                TabRow(
                    modifier = Modifier,
                    selectedTabIndex = currentTabIndex,
                ) {
                    tabs.forEachIndexed { index, title ->
                        val coroutineScope = rememberCoroutineScope()
                        Tab(
                            selected = currentTabIndex == index,
                            onClick = {
                                coroutineScope.launch {
                                    pagerState.animateScrollToPage(index)
                                }
                            },
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
        
                HorizontalPager(
                    modifier = Modifier.weight(1f),
                    state = pagerState,
                    pageCount = tabs.size,
                    pageSize = PageSize.Fill,
                ) { tabIndex ->
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            modifier = Modifier,
                            text = "Tab N",
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Start,
                        )
                    }
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

    component {
        showInToolWindow = false
        name = "Horizontal pager"
        imports = listOf(
            "androidx.compose.foundation.ExperimentalFoundationApi",
            "androidx.compose.foundation.layout.Box",
            "androidx.compose.foundation.layout.Column",
            "androidx.compose.foundation.layout.fillMaxSize",
            "androidx.compose.foundation.pager.HorizontalPager",
            "androidx.compose.foundation.pager.PageSize",
            "androidx.compose.foundation.pager.rememberPagerState",
            "androidx.compose.material3.MaterialTheme",
            "androidx.compose.material3.Text",
            "androidx.compose.ui.Alignment",
            "androidx.compose.ui.Modifier",
            "androidx.compose.ui.text.style.TextAlign",
        )
        code = """
            val pagerState = rememberPagerState()
            // pagerState.animateScrollToPage(3) to change pages
    
            HorizontalPager(
                modifier = Modifier.weight(1f),
                state = pagerState,
                pageCount = 3,
                pageSize = PageSize.Fill,
            ) { tabIndex ->
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        modifier = Modifier,
                        text = "Tab N",
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Start,
                    )
                }
            }
        """.trimIndent()
    }
}