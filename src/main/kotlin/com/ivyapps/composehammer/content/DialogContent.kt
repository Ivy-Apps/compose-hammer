package com.ivyapps.composehammer.content

import com.ivyapps.composehammer.domain.ContentScope
import com.ivyapps.composehammer.domain.component
import com.ivyapps.composehammer.domain.group

fun ContentScope.dialogs() = group("Dialogs") {
    component {
        name = "Alert Dialog"
        specUrl = "https://m3.material.io/components/dialogs/specs"
        guidelinesUrl = "https://m3.material.io/components/dialogs/guidelines"
        docsUrl =
            "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#AlertDialog(kotlin.Function0,androidx.compose.ui.Modifier,androidx.compose.ui.window.DialogProperties,kotlin.Function0)"
        description = """
            A dialog is a modal window that appears in front of app content to provide critical information or ask for a decision. Dialogs disable all app functionality when they appear, and remain on screen until confirmed, dismissed, or a required action has been taken.

            Dialogs are purposefully interruptive, so they should be used sparingly. A less disruptive alternative is to use a menu, which provides options without interrupting a user’s experience.
        """.trimIndent()
        screenshot = "dialog_basic"
        imports = listOf(
            "androidx.compose.material.icons.Icons",
            "androidx.compose.material.icons.filled.Info",
            "androidx.compose.material3.AlertDialog",
            "androidx.compose.material3.Icon",
            "androidx.compose.material3.Text",
            "androidx.compose.material3.TextButton",
            "androidx.compose.runtime.Composable",
            "androidx.compose.runtime.getValue",
            "androidx.compose.runtime.mutableStateOf",
            "androidx.compose.runtime.remember",
            "androidx.compose.runtime.setValue",
        )
        code = """
            var openDialog by remember { mutableStateOf(true) }
    
            if (openDialog) {
                AlertDialog(
                    onDismissRequest = { openDialog = false },
                    icon = { Icon(imageVector = Icons.Filled.Info, contentDescription = "Info") },
                    title = {
                        Text(text = "Title")
                    },
                    text = {
                        Text(
                            "This area typically contains the supportive text " +
                                    "which presents the details regarding the Dialog's purpose."
                        )
                    },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                openDialog = false
                            }
                        ) {
                            Text("Confirm")
                        }
                    },
                    dismissButton = {
                        TextButton(
                            onClick = {
                                openDialog = false
                            }
                        ) {
                            Text("Dismiss")
                        }
                    }
                )
            }
        """.trimIndent()
        customCode = """
            val openDialog = remember { mutableStateOf(true) }
    
            if (openDialog.value) {
                AlertDialog(
                    modifier = Modifier,
                    onDismissRequest = { openDialog.value = false },
                    icon = { Icon(imageVector = Icons.Filled.Info, contentDescription = "Info") },
                    title = {
                        Text(text = "Title")
                    },
                    text = {
                        Text(
                            "This area typically contains the supportive text " +
                                    "which presents the details regarding the Dialog's purpose."
                        )
                    },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                openDialog.value = false
                            }
                        ) {
                            Text("Confirm")
                        }
                    },
                    dismissButton = {
                        TextButton(
                            onClick = {
                                openDialog.value = false
                            }
                        ) {
                            Text("Dismiss")
                        }
                    },
                    shape = RoundedCornerShape(12.dp),
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    iconContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    textContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    tonalElevation = 1.dp
                )
            }
        """.trimIndent()
    }
}