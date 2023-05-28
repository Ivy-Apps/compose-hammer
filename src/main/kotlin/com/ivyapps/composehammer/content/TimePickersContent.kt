package com.ivyapps.composehammer.content

import com.ivyapps.composehammer.domain.ContentScope
import com.ivyapps.composehammer.domain.component
import com.ivyapps.composehammer.domain.group

fun ContentScope.timePickers() = group("Time pickers") {
    component {
        name = "Time picker dial"
        specUrl = "https://m3.material.io/components/time-pickers/specs#656721f2-de86-4311-807d-f295bddfb72f"
        guidelinesUrl = "https://m3.material.io/components/time-pickers/guidelines"
        docsUrl =
            "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#TimePicker(androidx.compose.material3.TimePickerState,androidx.compose.ui.Modifier,androidx.compose.material3.TimePickerColors,androidx.compose.material3.TimePickerLayoutType)"
        screenshot = "timepicker"
        description = """
            Copy link
            Time pickers allow people to enter a specific time value. They’re displayed in dialogs and can be used to select hours, minutes, or periods of time.
            They can be used for a wide range of scenarios. 
            >Common use cases include:
            - Setting an alarm
            - Scheduling a meeting
            >Time pickers are not ideal for nuanced or granular time selection, such as milliseconds for a stopwatch application.
        """.trimIndent()
        imports = listOf(
            "androidx.compose.material3.ExperimentalMaterial3Api",
            "androidx.compose.material3.TimePicker",
            "androidx.compose.material3.rememberTimePickerState",
            "androidx.compose.runtime.Composable",
        )
        code = """
            val state = rememberTimePickerState()
            TimePicker(state = state)
        """.trimIndent()
        customCode = """
            var showTimePicker by remember { mutableStateOf(false) }
            val state = rememberTimePickerState()
            val snackState = remember { SnackbarHostState() }
    
            Box(propagateMinConstraints = false) {
                Button(
                    modifier = Modifier.align(Alignment.Center),
                    onClick = { showTimePicker = true }
                ) {
                    Text("Set Time")
                }
    
                SnackbarHost(hostState = snackState)
            }
    
            if (showTimePicker) {
                TimePicker(
                    modifier = Modifier,
                    state = state,
                    colors = TimePickerDefaults.colors(
                        clockDialColor = MaterialTheme.colorScheme.secondaryContainer,
                        clockDialSelectedContentColor = MaterialTheme.colorScheme.onPrimary,
                        clockDialUnselectedContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                        selectorColor = MaterialTheme.colorScheme.primary,
                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        periodSelectorBorderColor = MaterialTheme.colorScheme.primary,
                        periodSelectorSelectedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                        periodSelectorUnselectedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                        periodSelectorSelectedContentColor = MaterialTheme.colorScheme.primary,
                        periodSelectorUnselectedContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                        timeSelectorSelectedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                        timeSelectorUnselectedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                        timeSelectorSelectedContentColor = MaterialTheme.colorScheme.primary,
                        timeSelectorUnselectedContentColor = MaterialTheme.colorScheme.onSecondaryContainer
                    ),
                    layoutType = TimePickerLayoutType.Vertical
                )
            }
        """.trimIndent()
    }

    component {
        name = "Time picker input"
        specUrl = "https://m3.material.io/components/time-pickers/specs#f07ad824-7e63-4d86-b5ca-090f1a6a3ded"
        guidelinesUrl = "https://m3.material.io/components/time-pickers/guidelines#9d639b70-cd08-4033-8634-59acab30502b"
        docsUrl =
            "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#TimePicker(androidx.compose.material3.TimePickerState,androidx.compose.ui.Modifier,androidx.compose.material3.TimePickerColors,androidx.compose.material3.TimePickerLayoutType)"
        screenshot = "timepicker_input"
        description = """
            Time input pickers allow people to specify a time using keyboard numbers. This input option should be accessible from any other mobile time picker interface by tapping the keyboard icon.
        """.trimIndent()
        imports = listOf(
            "androidx.compose.material3.ExperimentalMaterial3Api",
            "androidx.compose.material3.TimeInput",
            "androidx.compose.material3.rememberTimePickerState",
            "androidx.compose.runtime.Composable",
        )
        code = """
            val state = rememberTimePickerState()
            TimeInput(state = state)
        """.trimIndent()
        customCode = """
            var showTimePicker by remember { mutableStateOf(false) }
            val state = rememberTimePickerState()
            val snackState = remember { SnackbarHostState() }
    
            Column {
                Box(propagateMinConstraints = false) {
                    Button(
                        modifier = Modifier.align(Alignment.Center),
                        onClick = { showTimePicker = true }
                    ) {
                        Text("Set Time")
                    }
    
                    SnackbarHost(hostState = snackState)
                }
    
                Spacer(modifier = Modifier.height(20.dp))
    
                if (showTimePicker) {
                    TimeInput(
                        modifier = Modifier,
                        state = state,
                        colors = TimePickerDefaults.colors(
                            clockDialColor = MaterialTheme.colorScheme.secondaryContainer,
                            clockDialSelectedContentColor = MaterialTheme.colorScheme.onPrimary,
                            clockDialUnselectedContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                            selectorColor = MaterialTheme.colorScheme.primary,
                            containerColor = MaterialTheme.colorScheme.secondaryContainer,
                            periodSelectorBorderColor = MaterialTheme.colorScheme.primary,
                            periodSelectorSelectedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                            periodSelectorUnselectedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                            periodSelectorSelectedContentColor = MaterialTheme.colorScheme.primary,
                            periodSelectorUnselectedContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                            timeSelectorSelectedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                            timeSelectorUnselectedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                            timeSelectorSelectedContentColor = MaterialTheme.colorScheme.primary,
                            timeSelectorUnselectedContentColor = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    )
                }
            }
        """.trimIndent()
    }
}