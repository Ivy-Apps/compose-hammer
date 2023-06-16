package com.ivyapps.composehammer.m3content

import com.ivyapps.composehammer.domain.ContentScope
import com.ivyapps.composehammer.domain.component
import com.ivyapps.composehammer.domain.group

fun ContentScope.composeRuntime() = group(
    title = "⚡ Compose runtime",
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
        name = "derivedStateOf {}"
        imports = listOf(
            "androidx.compose.foundation.lazy.rememberLazyListState",
            "androidx.compose.runtime.derivedStateOf",
            "androidx.compose.runtime.remember",
        )
        code = """
            val listState = rememberLazyListState()
            val firstItemVisible = remember {
                derivedStateOf { listState.firstVisibleItemIndex > 0 }
            }
        """.trimIndent()
    }

    component {
        showInToolWindow = false
        name = "produceState {}"
        imports = listOf(
            "androidx.compose.runtime.getValue",
            "androidx.compose.runtime.produceState",
            "kotlinx.coroutines.ExperimentalCoroutinesApi",
            "kotlinx.coroutines.channels.produce",
            "kotlinx.coroutines.delay",
        )
        code = """
            val state by produceState<String?>(initialValue = null) {
                produce<String> {
                    delay(1_000)
                    // do your async logic here
                    value = "Produced!"
                }
            }
        """.trimIndent()

    }

    component {
        showInToolWindow = false
        name = "Coroutine scope"
        imports = listOf("androidx.compose.runtime.rememberCoroutineScope")
        code = """
            val coroutineScope = rememberCoroutineScope()
        """.trimIndent()
    }

    component {
        showInToolWindow = false
        name = "LaunchedEffect"
        imports = listOf("androidx.compose.runtime.LaunchedEffect")
        code = """
            LaunchedEffect(Unit) {
    
            }
        """.trimIndent()
    }

    component {
        showInToolWindow = false
        name = "with(LocalDensity.current) {}"
        imports = listOf(
            "androidx.compose.ui.platform.LocalDensity",
        )
        code = """
            with(LocalDensity.current) {
                
            }
        """.trimIndent()
    }

    component {
        showInToolWindow = false
        name = "Open link in the browser"
        imports = listOf(
            "androidx.compose.ui.platform.LocalUriHandler"
        )
        code = """
            val browser = LocalUriHandler.current
            browser.openUri("https://www.google.com/")
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
}
