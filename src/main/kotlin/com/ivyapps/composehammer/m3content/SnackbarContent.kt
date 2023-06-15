package com.ivyapps.composehammer.m3content

import com.ivyapps.composehammer.domain.ContentScope
import com.ivyapps.composehammer.domain.component
import com.ivyapps.composehammer.domain.group

fun ContentScope.snackbars() = group("Snackbars") {
    component {
        name = "Snackbar"
        specUrl = "https://m3.material.io/components/snackbar/specs"
        guidelinesUrl = "https://m3.material.io/components/snackbar/guidelines"
        docsUrl =
            "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#Snackbar(androidx.compose.material3.SnackbarData,androidx.compose.ui.Modifier,kotlin.Boolean,androidx.compose.ui.graphics.Shape,androidx.compose.ui.graphics.Color,androidx.compose.ui.graphics.Color,androidx.compose.ui.graphics.Color,androidx.compose.ui.graphics.Color,androidx.compose.ui.graphics.Color)"
        screenshot = "snackbar"
        description = """
            Snackbars inform users of a process that an app has performed or will perform. They appear temporarily, towards the bottom of the screen. They shouldn’t interrupt the user experience, and they don’t require user input to disappear.
            >Frequency:
            >Only one snackbar may be displayed at a time.
            >Actions:
            >A snackbar can contain a single action. "Dismiss" or "cancel" actions are optional.
            >When to use snackbars:
            >Snackbars communicate messages that are minimally interruptive and don’t require user action.
        """.trimIndent()
        imports = listOf(
            "androidx.compose.foundation.layout.Column",
            "androidx.compose.foundation.layout.padding",
            "androidx.compose.material.icons.Icons",
            "androidx.compose.material.icons.filled.Create",
            "androidx.compose.material3.FloatingActionButton",
            "androidx.compose.material3.Icon",
            "androidx.compose.material3.Scaffold",
            "androidx.compose.material3.SnackbarHost",
            "androidx.compose.material3.SnackbarHostState",
            
            "androidx.compose.runtime.remember",
            "androidx.compose.runtime.rememberCoroutineScope",
            "androidx.compose.ui.Modifier",
            "kotlinx.coroutines.launch",
        )
        code = """
            val snackbarHostState = remember { SnackbarHostState() }
            val scope = rememberCoroutineScope()
    
            Scaffold(
                snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = {
                            // this is how you show a Snackbar
                            scope.launch {
                                snackbarHostState.showSnackbar("Hello!")
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Create,
                            contentDescription = "Create"
                        )
                    }
                },
                content = { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        // Screen content
                    }
                }
            )
        """.trimIndent()
        customCode = """
            class SnackbarVisualsWithError(
                override val message: String,
                val isError: Boolean
            ) : SnackbarVisuals {
                override val actionLabel: String
                    get() = if (isError) "Error" else "OK"
                override val withDismissAction: Boolean
                    get() = false
                override val duration: SnackbarDuration
                    get() = SnackbarDuration.Indefinite
            }
    
            val snackbarHostState = remember { SnackbarHostState() }
            val scope = rememberCoroutineScope()
            Scaffold(
                snackbarHost = {
                    // reuse default SnackbarHost to have default animation and timing handling
                    SnackbarHost(hostState = snackbarHostState) { data ->
                        // custom snackbar with the custom action button color and border
                        val isError = (data.visuals as? SnackbarVisualsWithError)?.isError ?: false
                        val buttonColor = if (isError) {
                            ButtonDefaults.textButtonColors(
                                containerColor = MaterialTheme.colorScheme.errorContainer,
                                contentColor = MaterialTheme.colorScheme.error
                            )
                        } else {
                            ButtonDefaults.textButtonColors(
                                contentColor = MaterialTheme.colorScheme.inversePrimary
                            )
                        }
    
                        Snackbar(
                            modifier = Modifier
                                .border(2.dp, MaterialTheme.colorScheme.secondary)
                                .padding(12.dp),
                            action = {
                                TextButton(
                                    onClick = { if (isError) data.dismiss() else data.performAction() },
                                    colors = buttonColor
                                ) {
                                    Text(data.visuals.actionLabel ?: "")
                                }
                            },
                            shape = RoundedCornerShape(12.dp),
                            containerColor = MaterialTheme.colorScheme.secondaryContainer,
                            contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                            actionContentColor = MaterialTheme.colorScheme.primary
                        ) {
                            Text(data.visuals.message)
                        }
                    }
                },
                floatingActionButton = {
                    var clickCount by remember { mutableStateOf(0) }
                    ExtendedFloatingActionButton(
                        onClick = {
                            scope.launch {
                                snackbarHostState.showSnackbar(
                                    SnackbarVisualsWithError(
                                        "Snackbar # ++clickCount",
                                        isError = clickCount % 2 != 0
                                    )
                                )
                            }
                        }
                    ) { Text("Show snackbar") }
                },
                content = { innerPadding ->
                    Text(
                        text = "Custom Snackbar Demo",
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                            .wrapContentSize()
                    )
                }
            )
        """.trimIndent()
    }
}
