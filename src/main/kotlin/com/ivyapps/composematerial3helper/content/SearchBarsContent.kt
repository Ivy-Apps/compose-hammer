package com.ivyapps.composematerial3helper.content

import com.ivyapps.composematerial3helper.domain.ContentScope
import com.ivyapps.composematerial3helper.domain.component
import com.ivyapps.composematerial3helper.domain.group

fun ContentScope.searchBars() = group("Search bars") {
    component {
        name = "Search bar"
        specUrl = "https://m3.material.io/components/search/specs"
        guidelinesUrl = "https://m3.material.io/components/search/guidelines"
        docsUrl =
            "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#SearchBar(kotlin.String,kotlin.Function1,kotlin.Function1,kotlin.Boolean,kotlin.Function1,androidx.compose.ui.Modifier,kotlin.Boolean,kotlin.Function0,kotlin.Function0,kotlin.Function0,androidx.compose.ui.graphics.Shape,androidx.compose.material3.SearchBarColors,androidx.compose.ui.unit.Dp,androidx.compose.foundation.layout.WindowInsets,androidx.compose.foundation.interaction.MutableInteractionSource,kotlin.Function1)"
        screenshot = "searchbar"
        description = """
            A search bar represents a floating search field that allows users to enter a keyword or phrase and get relevant information. It can be used as a way to navigate through an app via search queries.
            An active search bar expands into a search "view" and can be used to display dynamic suggestions.
        """.trimIndent()
        imports = listOf(
            "androidx.compose.material.icons.Icons",
            "androidx.compose.material.icons.filled.MoreVert",
            "androidx.compose.material.icons.filled.Search",
            "androidx.compose.material3.ExperimentalMaterial3Api",
            "androidx.compose.material3.Icon",
            "androidx.compose.material3.SearchBar",
            "androidx.compose.material3.Text",
            "androidx.compose.runtime.Composable",
            "androidx.compose.runtime.getValue",
            "androidx.compose.runtime.mutableStateOf",
            "androidx.compose.runtime.saveable.rememberSaveable",
            "androidx.compose.runtime.setValue",
            "androidx.compose.ui.Modifier",
        )
        code = """
            var searchQuery by rememberSaveable { mutableStateOf("") }
            SearchBar(
                modifier = Modifier,
                query = searchQuery,
                onQueryChange = { searchQuery = it },
                onSearch = { query ->
                    // Handle search ImeAction.Search here
                },
                active = true,
                onActiveChange = { isActive ->
                },
                placeholder = { Text("Hinted search text") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                trailingIcon = { Icon(Icons.Default.MoreVert, contentDescription = null) }
            ) {
                // Show suggestions here
                // for example a LazyColumn with suggestion items
            }
        """.trimIndent()
        customCode = """
            var text by rememberSaveable { mutableStateOf("") }
            var active by rememberSaveable { mutableStateOf(false) }
    
            Box(Modifier.fillMaxSize()) {
                Box(
                    Modifier
                        .zIndex(1f)
                        .fillMaxWidth()
                ) {
                    SearchBar(
                        modifier = Modifier.align(Alignment.TopCenter),
                        query = text,
                        onQueryChange = { text = it },
                        onSearch = { active = false },
                        active = active,
                        onActiveChange = {
                            active = it
                        },
                        placeholder = { Text("Hinted search text") },
                        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                        trailingIcon = { Icon(Icons.Default.MoreVert, contentDescription = null) },
                        colors = SearchBarDefaults.colors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            dividerColor = MaterialTheme.colorScheme.primary,
                            inputFieldColors = TextFieldDefaults.colors()
                        )
                    ) {
                        LazyColumn(
                            modifier = Modifier.fillMaxWidth(),
                            contentPadding = PaddingValues(16.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            items(4) { idx ->
                                val resultText = "Suggestion N"
    
                                ListItem(
                                    headlineContent = { Text(resultText) },
                                    supportingContent = { Text("Additional info") },
                                    leadingContent = {
                                        Icon(
                                            Icons.Filled.Star,
                                            contentDescription = null
                                        )
                                    },
                                    modifier = Modifier.clickable {
                                        text = resultText
                                        active = false
                                    }
                                )
                            }
                        }
                    }
                }
    
                LazyColumn(
                    contentPadding = PaddingValues(
                        start = 16.dp,
                        top = 72.dp,
                        end = 16.dp,
                        bottom = 16.dp
                    ),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    val list = List(100) { "Text N" }
    
                    items(count = list.size) {
                        Text(
                            list[it],
                            Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                        )
                    }
                }
            }
        """.trimIndent()
    }
}