package com.ivyapps.composehammer.content

import com.ivyapps.composehammer.domain.ContentScope
import com.ivyapps.composehammer.domain.component
import com.ivyapps.composehammer.domain.group

fun ContentScope.composeRuntime() = group(
    title = "(*) Compose runtime",
    showInToolWindow = false
) {
    component {
        showInToolWindow = false
        name = "by remember { mutableStateOf(false) }"
        imports = listOf(
            "androidx.compose.runtime.getValue",
            "androidx.compose.runtime.mutableStateOf",
            "androidx.compose.runtime.remember",
            "androidx.compose.runtime.setValue",
        )
        code = """
            var visible by remember { mutableStateOf(false) }
        """.trimIndent()
    }

    component {
        showInToolWindow = false
        name = "by remember { mutableStateOf<String?>(null) }"
        imports = listOf(
            "androidx.compose.runtime.getValue",
            "androidx.compose.runtime.mutableStateOf",
            "androidx.compose.runtime.remember",
            "androidx.compose.runtime.setValue",
        )
        code = """
            var state by remember { mutableStateOf<String?>(null) }
        """.trimIndent()
    }

    component {
        showInToolWindow = false
        name = "LocalContext.current"
        imports = listOf(
            "androidx.compose.ui.platform.LocalContext",
        )
        code = """
            val context = LocalContext.current
        """.trimIndent()
    }

    component {
        showInToolWindow = false
        name = "Coroutine scope"
        import = "androidx.compose.runtime.rememberCoroutineScope"
        code = """
            val coroutineScope = rememberCoroutineScope()
        """.trimIndent()
    }

    component {
        showInToolWindow = false
        name = "LaunchedEffect"
        import = "androidx.compose.runtime.LaunchedEffect"
        code = """
            LaunchedEffect(Unit) {
    
            }
        """.trimIndent()
    }
}
