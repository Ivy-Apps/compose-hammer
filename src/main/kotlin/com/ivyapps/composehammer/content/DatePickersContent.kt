package com.ivyapps.composehammer.content

import com.ivyapps.composehammer.domain.ContentScope
import com.ivyapps.composehammer.domain.component
import com.ivyapps.composehammer.domain.group


fun ContentScope.datePickers() = group("Date Pickers") {
    component {
        name = "Date picker (input)"
        specUrl = "https://m3.material.io/components/date-pickers/specs#ccd8cb55-4c20-4832-9db2-7c14c49b6e8f"
        guidelinesUrl = "https://m3.material.io/components/date-pickers/guidelines#07d8ef51-1085-4838-b20f-2a0aa62dabe0"
        docsUrl =
            "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#DatePicker(androidx.compose.material3.DatePickerState,androidx.compose.ui.Modifier,androidx.compose.material3.DatePickerFormatter,kotlin.Function0,kotlin.Function0,kotlin.Boolean,androidx.compose.material3.DatePickerColors)"
        description = """
            Mobile date input pickers allow the manual entry of dates using the numbers on a keyboard. 
            Users can input a date or a range of dates in a dialog.
        """.trimIndent()
        screenshot = "datepicker_input"
        imports = listOf(
            "androidx.compose.material3.DatePicker",
            "androidx.compose.material3.rememberDatePickerState",
            "androidx.compose.material3.DisplayMode",
        )
        code = """
            val datePickerState = rememberDatePickerState(
                initialSelectedDateMillis = 1685112333816, // epoch/unix timestamp
                initialDisplayMode = DisplayMode.Input,
            )

            DatePicker(
                state = datePickerState,
                showModeToggle = false,
                headline = null,
                title = null,
            )
        """.trimIndent()
        customCode = """
            val datePickerState = rememberDatePickerState(
                initialSelectedDateMillis = 1685112333816, // epoch/unix timestamp
                initialDisplayMode = DisplayMode.Input,
            )

            DatePicker(
                state = datePickerState,
                showModeToggle = true,
                headline = {
                    // <Your Compose UI here>
                },
                title = {
                    // <Your Compose UI here>
                },
                colors = DatePickerDefaults.colors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    headlineContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    weekdayContentColor = MaterialTheme.colorScheme.primary,
                    subheadContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    yearContentColor = MaterialTheme.colorScheme.tertiary,
                    currentYearContentColor = MaterialTheme.colorScheme.tertiary,
                    selectedYearContentColor = MaterialTheme.colorScheme.primary,
                    selectedYearContainerColor = MaterialTheme.colorScheme.onPrimary,
                    dayContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    disabledDayContentColor = MaterialTheme.colorScheme.onTertiary,
                    selectedDayContentColor = MaterialTheme.colorScheme.onPrimary,
                    disabledSelectedDayContentColor = MaterialTheme.colorScheme.onTertiary,
                    selectedDayContainerColor = MaterialTheme.colorScheme.primary,
                    disabledSelectedDayContainerColor = MaterialTheme.colorScheme.tertiary,
                    todayContentColor = MaterialTheme.colorScheme.primary,
                    todayDateBorderColor = MaterialTheme.colorScheme.primary,
                    dayInSelectionRangeContentColor = MaterialTheme.colorScheme.primary,
                    dayInSelectionRangeContainerColor = MaterialTheme.colorScheme.onPrimary
                ),
            )
        """.trimIndent()
    }

    component {
        name = "Date picker (calendar)"
        specUrl = "https://m3.material.io/components/date-pickers/specs#d58626b9-ed69-4963-a75c-18d00cae5a06"
        guidelinesUrl = "https://m3.material.io/components/date-pickers/guidelines#c5c0471f-aa8a-4205-ab4b-1ab8cb893c5c"
        docsUrl =
            "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#DatePicker(androidx.compose.material3.DatePickerState,androidx.compose.ui.Modifier,androidx.compose.material3.DatePickerFormatter,kotlin.Function0,kotlin.Function0,kotlin.Boolean,androidx.compose.material3.DatePickerColors)"
        description = """
            Mobile calendar pickers navigate across dates in several ways:
            - To navigate across months, swipe horizontally
            - To navigate across years, scroll vertically
            - To access the year picker, tap the year
            Don’t use a modal date picker to prompt for dates in the distant past or future, 
            such as a date of birth. In these cases, use a modal input picker or a docked date picker instead.
        """.trimIndent()
        screenshot = "datepicker_calendar"
        imports = listOf(
            "androidx.compose.material3.DatePicker",
            "androidx.compose.material3.rememberDatePickerState",
            "androidx.compose.material3.DisplayMode",
        )
        code = """
            val datePickerState = rememberDatePickerState(
                initialSelectedDateMillis = 1685112333816, // epoch/unix timestamp
                initialDisplayMode = DisplayMode.Picker,
            )

            DatePicker(
                state = datePickerState,
                showModeToggle = false,
                headline = null,
                title = null,
            )
        """.trimIndent()
        customCode = """
            val datePickerState = rememberDatePickerState(
                initialSelectedDateMillis = 1685112333816, // epoch/unix timestamp
                initialDisplayMode = DisplayMode.Picker,
            )

            DatePicker(
                state = datePickerState,
                showModeToggle = true,
                headline = {
                    // <Your Compose UI here>
                },
                title = {
                    // <Your Compose UI here>
                },
                colors = DatePickerDefaults.colors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    headlineContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    weekdayContentColor = MaterialTheme.colorScheme.primary,
                    subheadContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    yearContentColor = MaterialTheme.colorScheme.tertiary,
                    currentYearContentColor = MaterialTheme.colorScheme.tertiary,
                    selectedYearContentColor = MaterialTheme.colorScheme.primary,
                    selectedYearContainerColor = MaterialTheme.colorScheme.onPrimary,
                    dayContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    disabledDayContentColor = MaterialTheme.colorScheme.onTertiary,
                    selectedDayContentColor = MaterialTheme.colorScheme.onPrimary,
                    disabledSelectedDayContentColor = MaterialTheme.colorScheme.onTertiary,
                    selectedDayContainerColor = MaterialTheme.colorScheme.primary,
                    disabledSelectedDayContainerColor = MaterialTheme.colorScheme.tertiary,
                    todayContentColor = MaterialTheme.colorScheme.primary,
                    todayDateBorderColor = MaterialTheme.colorScheme.primary,
                    dayInSelectionRangeContentColor = MaterialTheme.colorScheme.primary,
                    dayInSelectionRangeContainerColor = MaterialTheme.colorScheme.onPrimary
                ),
            )
        """.trimIndent()
    }

    component {
        name = "Date picker dialog"
        specUrl = "https://m3.material.io/components/date-pickers/specs#d58626b9-ed69-4963-a75c-18d00cae5a06"
        guidelinesUrl = "https://m3.material.io/components/date-pickers/guidelines#c5c0471f-aa8a-4205-ab4b-1ab8cb893c5c"
        docsUrl =
            "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#DatePickerDialog(kotlin.Function0,kotlin.Function0,androidx.compose.ui.Modifier,kotlin.Function0,androidx.compose.ui.graphics.Shape,androidx.compose.ui.unit.Dp,androidx.compose.material3.DatePickerColors,androidx.compose.ui.window.DialogProperties,kotlin.Function1)"
        description = """
            A dialog wrapper over the the DatePicker component allowing you to show it as a dialog.
        """.trimIndent()
        screenshot = "datepicker_dialog"
        imports = listOf(
            "androidx.compose.material3.DatePicker",
            "androidx.compose.material3.DatePickerDialog",
            "androidx.compose.material3.ExperimentalMaterial3Api",
            "androidx.compose.material3.Text",
            "androidx.compose.material3.TextButton",
            "androidx.compose.material3.rememberDatePickerState",
            "androidx.compose.runtime.Composable",
            "androidx.compose.runtime.derivedStateOf",
            "androidx.compose.runtime.getValue",
            "androidx.compose.runtime.mutableStateOf",
            "androidx.compose.runtime.remember",
            "androidx.compose.runtime.setValue",
        )
        code = """
            var openDialog by remember { mutableStateOf(false) }
        
            if (openDialog) {
                val datePickerState = rememberDatePickerState()
                val confirmEnabled by remember {
                    derivedStateOf { datePickerState.selectedDateMillis != null }
                }
                DatePickerDialog(
                    onDismissRequest = {
                        openDialog = false
                    },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                openDialog = false
                            },
                            enabled = confirmEnabled
                        ) {
                            Text("OK")
                        }
                    },
                    dismissButton = {
                        TextButton(
                            onClick = {
                                openDialog = false
                            }
                        ) {
                            Text("Cancel")
                        }
                    }
                ) {
                    DatePicker(state = datePickerState)
                }
            }
        """.trimIndent()
    }

    component {
        name = "Date range picker"
        specUrl = "https://m3.material.io/components/date-pickers/specs#d3189372-1b73-49d2-977e-e766f43a2774"
        guidelinesUrl = "https://m3.material.io/components/date-pickers/guidelines#b4c77e58-e85e-40da-8a6b-7d921832a7ad"
        docsUrl =
            "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#DateRangePicker(androidx.compose.material3.DateRangePickerState,androidx.compose.ui.Modifier,androidx.compose.material3.DatePickerFormatter,kotlin.Function0,kotlin.Function0,kotlin.Boolean,androidx.compose.material3.DatePickerColors)"
        description = """
            Date range selection provides a start and end date.
            Common use cases include:
            - Booking a flight
            - Reserving a hotel
            
            Mobile date range pickers navigate across dates in several ways:
            - To select a range of dates, tap the start and end dates on the calendar
            - To navigate across months, scroll vertically
        """.trimIndent()
        screenshot = "datepicker_range"
        imports = listOf(
            "androidx.compose.material3.rememberDateRangePickerState",
            "androidx.compose.material3.DateRangePicker",
        )
        code = """
            val state = rememberDateRangePickerState()
            DateRangePicker(state = state)
        """.trimIndent()
        customCode = """
            val state = rememberDateRangePickerState()
            DateRangePicker(
                modifier = Modifier.padding(horizontal = 16.dp),
                state = state,
                colors = DatePickerDefaults.colors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface,
                    headlineContentColor = MaterialTheme.colorScheme.onSurface,
                    weekdayContentColor = MaterialTheme.colorScheme.onSurface,
                    subheadContentColor = MaterialTheme.colorScheme.onSurface,
                    yearContentColor = MaterialTheme.colorScheme.onSurface,
                    currentYearContentColor = MaterialTheme.colorScheme.onSurface,
                    selectedYearContentColor = MaterialTheme.colorScheme.onSurface,
                    selectedYearContainerColor = MaterialTheme.colorScheme.surface,
                    dayContentColor = MaterialTheme.colorScheme.onSurface,
                    disabledDayContentColor = MaterialTheme.colorScheme.onSurface,
                    selectedDayContentColor = MaterialTheme.colorScheme.onPrimary,
                    disabledSelectedDayContentColor = MaterialTheme.colorScheme.onSurface,
                    selectedDayContainerColor = MaterialTheme.colorScheme.primary,
                    disabledSelectedDayContainerColor = MaterialTheme.colorScheme.surface,
                    todayContentColor = MaterialTheme.colorScheme.onSurface,
                    todayDateBorderColor = MaterialTheme.colorScheme.primary,
                    dayInSelectionRangeContentColor = MaterialTheme.colorScheme.onPrimary,
                    dayInSelectionRangeContainerColor = MaterialTheme.colorScheme.primary
                )
            )
        """.trimIndent()
    }
}