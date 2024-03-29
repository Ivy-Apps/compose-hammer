package com.ivyapps.composehammer.m3content

import com.ivyapps.composehammer.domain.ContentScope
import com.ivyapps.composehammer.domain.component
import com.ivyapps.composehammer.domain.group

fun ContentScope.quickUi() = group(
    title = "⚡ Quick UI",
    showInToolWindow = false
) {
    component {
        showInToolWindow = false
        name = "Text"
        imports = listOf(
            "androidx.compose.material3.MaterialTheme",
            "androidx.compose.material3.Text",
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
        name = "Icon"
        imports = listOf(
            "androidx.compose.material.icons.Icons",
            "androidx.compose.material.icons.filled.Add",
            "androidx.compose.material3.Icon",
        )
        code = """
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
            )
        """.trimIndent()
    }

    component {
        showInToolWindow = false
        name = "Image"
        imports = listOf(
            "androidx.compose.foundation.Image",
            "androidx.compose.ui.layout.ContentScale",
            "androidx.compose.ui.res.painterResource",
        )
        code = """
            Image(
                painter = painterResource(id = ),
                contentDescription = null,
                contentScale = ContentScale.Fit,
            )
        """.trimIndent()
    }

    component {
        showInToolWindow = false
        name = "Spacer (vertical)"
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
        name = "Spacer (horizontal)"
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
        name = "Spacer (weight)"
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
        name = "modifier = Modifier"
        imports = listOf("androidx.compose.ui.Modifier")
        code = """
            modifier = Modifier,
        """.trimIndent()
    }

    component {
        showInToolWindow = false
        name = "modifier: Modifier = Modifier"
        imports = listOf("androidx.compose.ui.Modifier")
        code = """
            modifier: Modifier = Modifier,
        """.trimIndent()
    }

    component {
        showInToolWindow = false
        name = "RoundedCornerShape (dp)"
        imports = listOf(
            "androidx.compose.foundation.shape.RoundedCornerShape",
            "androidx.compose.ui.unit.dp",
        )
        code = """
            RoundedCornerShape(16.dp),
        """.trimIndent()
    }

    component {
        showInToolWindow = false
        name = "RoundedCornerShape (fully rounded)"
        imports = listOf(
            "androidx.compose.foundation.shape.RoundedCornerShape",
        )
        code = """
            RoundedCornerShape(percent = 50),
        """.trimIndent()
    }
}
