package com.ivyapps.composematerial3helper.content

import com.ivyapps.composematerial3helper.domain.ContentScope
import com.ivyapps.composematerial3helper.domain.component
import com.ivyapps.composematerial3helper.domain.group

fun ContentScope.composeRuntime() = group(
    title = "Compose runtime",
    showInToolWindow = false
) {
    component {
        showInToolWindow = false
        name = "Compose State"
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
        name = "Local Context"
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
        name = "Launched Effect"
        import = "androidx.compose.runtime.LaunchedEffect"
        code = """
            LaunchedEffect(Unit) {
    
            }
        """.trimIndent()
    }
}
