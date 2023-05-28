package com.ivyapps.composehammer.content

import com.ivyapps.composehammer.domain.ContentScope
import com.ivyapps.composehammer.domain.component
import com.ivyapps.composehammer.domain.group

fun ContentScope.common() = group(
    title = "(*) Quick UI",
    showInToolWindow = false
) {
    component {
        showInToolWindow = false
        name = "Text"
        imports = listOf(
            "androidx.compose.material3.MaterialTheme",
            "androidx.compose.material3.Text",
            "androidx.compose.runtime.Composable",
            "androidx.compose.ui.Modifier",
            "androidx.compose.ui.text.style.TextAlign",
        )
        code = """
            Text(
                modifier = Modifier,
                text = "Text",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Start,
            )
        """.trimIndent()
    }

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
        name = "Spacer - Vertical"
        imports = listOf(
            "androidx.compose.foundation.layout.Spacer",
            "androidx.compose.ui.Modifier",
            "androidx.compose.ui.unit.dp",
            "androidx.compose.foundation.layout.height",
        )
        code = """
            Spacer(modifier = Modifier.height(8.dp))
        """.trimIndent()
    }

    component {
        showInToolWindow = false
        name = "Spacer - Horizontal"
        imports = listOf(
            "androidx.compose.foundation.layout.Spacer",
            "androidx.compose.ui.Modifier",
            "androidx.compose.ui.unit.dp",
            "androidx.compose.foundation.layout.width"
        )
        code = """
            Spacer(modifier = Modifier.width(8.dp))
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
        name = "modifier: Modifier = Modifier"
        import = "androidx.compose.ui.Modifier"
        code = """
            modifier: Modifier = Modifier,
        """.trimIndent()
    }

    component {
        showInToolWindow = false
        name = "Spacer - Weight"
        imports = listOf(
            "androidx.compose.foundation.layout.Spacer",
            "androidx.compose.ui.Modifier",
        )
        code = """
            Spacer(modifier = Modifier.weight(1f))
        """.trimIndent()
    }

    component {
        showInToolWindow = false
        name = "Lazy Column"
        imports = listOf(
            "androidx.compose.ui.Modifier",
            "androidx.compose.foundation.lazy.LazyColumn",
            "androidx.compose.foundation.layout.PaddingValues",
            "androidx.compose.ui.unit.dp",
        )
        code = """
            LazyColumn(
                modifier = Modifier,
                contentPadding = PaddingValues(vertical = 8.dp)
            ) {
        
            }
        """.trimIndent()
    }

    component {
        showInToolWindow = false
        name = "Lazy Row"
        imports = listOf(
            "androidx.compose.ui.Modifier",
            "androidx.compose.foundation.lazy.LazyRow",
            "androidx.compose.foundation.layout.PaddingValues",
            "androidx.compose.ui.unit.dp",
        )
        code = """
            LazyRow(
                modifier = Modifier,
                contentPadding = PaddingValues(horizontal = 8.dp)
            ) {
        
            }
        """.trimIndent()
    }
}
