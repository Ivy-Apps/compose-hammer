package com.ivyapps.composehammer.m3content

import com.ivyapps.composehammer.domain.ContentScope
import com.ivyapps.composehammer.domain.component
import com.ivyapps.composehammer.domain.group

fun ContentScope.layouts() = group(
    title = "⚡ Layouts",
    showInToolWindow = false
) {
    component {
        showInToolWindow = false
        name = "Column"
        imports = listOf(
            "androidx.compose.ui.Modifier",
            "androidx.compose.foundation.layout.Column"
        )
        code = """
            Column(
                modifier = Modifier,
            ) {
        
            }
        """.trimIndent()
    }

    component {
        showInToolWindow = false
        name = "Row"
        imports = listOf(
            "androidx.compose.ui.Modifier",
            "androidx.compose.foundation.layout.Row",
            "androidx.compose.ui.Alignment",
        )
        code = """
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically,
            ) {
        
            }
        """.trimIndent()
    }

    component {
        showInToolWindow = false
        name = "Box"
        imports = listOf(
            "androidx.compose.ui.Modifier",
            "androidx.compose.foundation.layout.Box",
            "androidx.compose.ui.Alignment",
        )
        code = """
            Box(
                modifier = Modifier,
                contentAlignment = Alignment.Center,
            ) {
        
            }
        """.trimIndent()
    }

    component {
        showInToolWindow = false
        name = "Lazy Column"
        imports = listOf(
            "androidx.compose.foundation.layout.PaddingValues",
            "androidx.compose.foundation.lazy.LazyColumn",
            "androidx.compose.foundation.lazy.items",
            "androidx.compose.ui.Modifier",
            "androidx.compose.ui.unit.dp",
        )
        code = """
            LazyColumn(
                modifier = Modifier,
                contentPadding = PaddingValues(vertical = 8.dp) // 8.dp between each item
            ) {
                item {
                    // If you want a single item
                }
                items(items = listOf<Any>()) { item ->
                    // display a single list item
                    // it's like forEach {}
                }
            }
        """.trimIndent()
    }

    component {
        showInToolWindow = false
        name = "Lazy Row"
        imports = listOf(
            "androidx.compose.foundation.layout.PaddingValues",
            "androidx.compose.foundation.lazy.LazyRow",
            "androidx.compose.foundation.lazy.items",
            "androidx.compose.ui.Modifier",
            "androidx.compose.ui.unit.dp",
        )
        code = """
            LazyRow(
                modifier = Modifier,
                contentPadding = PaddingValues(horizontal = 8.dp) // 8.dp between each item
            ) {
                item {
                    // If you want a single item
                }
                items(items = listOf<Any>()) { item ->
                    // display a single list item
                    // it's like forEach {}
                }
            }
        """.trimIndent()
    }
}
