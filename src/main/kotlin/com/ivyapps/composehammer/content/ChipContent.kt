package com.ivyapps.composehammer.content

import com.ivyapps.composehammer.domain.ContentScope
import com.ivyapps.composehammer.domain.component
import com.ivyapps.composehammer.domain.group

fun ContentScope.chips() = group("Chips") {
    component {
        name = "Assist chip"
        shortName = "Assist"
        specUrl = "https://m3.material.io/components/chips/specs#a144389c-9478-4fe4-9bd8-ca9f7dd830eb"
        guidelinesUrl = "https://m3.material.io/components/chips/guidelines#5dd1928c-1476-4029-bdc5-fde66fc0dcb1"
        docsUrl =
            "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#AssistChip(kotlin.Function0,kotlin.Function0,androidx.compose.ui.Modifier,kotlin.Boolean,kotlin.Function0,kotlin.Function0,androidx.compose.ui.graphics.Shape,androidx.compose.material3.ChipColors,androidx.compose.material3.ChipElevation,androidx.compose.material3.ChipBorder,androidx.compose.foundation.interaction.MutableInteractionSource)"
        screenshot = "chip_assist"
        description = """
            Chips help people enter information, make selections, filter content, or trigger actions. Chips can show multiple interactive elements together in the same area, such as a list of selectable movie times, or a series of email contacts.

            Assist chips represent smart or automated actions that can span multiple apps, such as opening a calendar event from the home screen. Assist chips function as though the user asked an assistant to complete the action. They should appear dynamically and contextually in a UI.
        """.trimIndent()
        imports = listOf(
            "androidx.compose.foundation.layout.size",
            "androidx.compose.material.icons.Icons",
            "androidx.compose.material.icons.filled.Call",
            "androidx.compose.material3.AssistChip",
            "androidx.compose.material3.AssistChipDefaults",
            "androidx.compose.material3.Icon",
            "androidx.compose.material3.Text",
            "androidx.compose.runtime.Composable",
            "androidx.compose.ui.Modifier",
        )
        code = """
            AssistChip(
                onClick = {
                    /* Do something! */
                },
                label = {
                    Text("Call")
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Call,
                        contentDescription = "Call",
                        modifier = Modifier.size(AssistChipDefaults.IconSize)
                    )
                }
            )
        """.trimIndent()
        customCode = """
            AssistChip(
                modifier = Modifier,
                onClick = {
                    /* Do something! */
                },
                label = {
                    Text("Call")
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Call,
                        contentDescription = "Call",
                        Modifier.size(AssistChipDefaults.IconSize)
                    )
                },
                enabled = true,
                shape = RoundedCornerShape(12.dp),
                colors = AssistChipDefaults.assistChipColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    labelColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    leadingIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                    disabledLabelColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    disabledLeadingIconContentColor = MaterialTheme.colorScheme.onSecondaryContainer
                ),
                border = AssistChipDefaults.assistChipBorder(
                    borderColor = MaterialTheme.colorScheme.secondary,
                    disabledBorderColor = MaterialTheme.colorScheme.tertiary,
                    borderWidth = 1.dp
                )
            )
        """.trimIndent()
    }

    component {
        name = "Filter chip"
        shortName = "Filter"
        specUrl = "https://m3.material.io/components/chips/specs#e900592f-75a4-4298-853c-bedd8f462f83"
        guidelinesUrl = "https://m3.material.io/components/chips/guidelines#8d453d50-8d8e-43aa-9ae3-87ed134d2e64"
        docsUrl =
            "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#FilterChip(kotlin.Boolean,kotlin.Function0,kotlin.Function0,androidx.compose.ui.Modifier,kotlin.Boolean,kotlin.Function0,kotlin.Function0,androidx.compose.ui.graphics.Shape,androidx.compose.material3.SelectableChipColors,androidx.compose.material3.SelectableChipElevation,androidx.compose.material3.SelectableChipBorder,androidx.compose.foundation.interaction.MutableInteractionSource)"
        screenshot = "chip_filter"
        description = """
            Chips help people enter information, make selections, filter content, or trigger actions. Chips can show multiple interactive elements together in the same area, such as a list of selectable movie times, or a series of email contacts.

            Filter chips use tags or descriptive words to filter content. They can be a good alternative to toggle buttons or checkboxes.
        """.trimIndent()
        imports = listOf(
            "androidx.compose.foundation.layout.size",
            "androidx.compose.material.icons.Icons",
            "androidx.compose.material.icons.filled.Done",
            "androidx.compose.material3.ExperimentalMaterial3Api",
            "androidx.compose.material3.FilterChip",
            "androidx.compose.material3.FilterChipDefaults",
            "androidx.compose.material3.Icon",
            "androidx.compose.material3.Text",
            "androidx.compose.runtime.Composable",
            "androidx.compose.runtime.getValue",
            "androidx.compose.runtime.mutableStateOf",
            "androidx.compose.runtime.remember",
            "androidx.compose.runtime.setValue",
            "androidx.compose.ui.Modifier",
        )
        code = """
            var selected by remember { mutableStateOf(false) }

            FilterChip(
                selected = !selected,
                onClick = {
                    selected = !selected
                },
                label = {
                    Text("Remote")
                },
                leadingIcon = if (selected) {
                    {
                        Icon(
                            imageVector = Icons.Filled.Done,
                            contentDescription = "Done",
                            modifier = Modifier.size(FilterChipDefaults.IconSize)
                        )
                    }
                } else {
                    null
                }
            )
        """.trimIndent()
        customCode = """
            var selected by remember { mutableStateOf(true) }
    
            FilterChip(
                modifier = Modifier,
                selected = selected,
                onClick = {
                    selected = !selected
                },
                label = {
                    Text("Remote")
                },
                leadingIcon = if (selected) {
                    {
                        Icon(
                            imageVector = Icons.Filled.Done,
                            contentDescription = "Done",
                            modifier = Modifier.size(FilterChipDefaults.IconSize)
                        )
                    }
                } else {
                    null
                },
                enabled = true,
                shape = RoundedCornerShape(12.dp),
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    labelColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    iconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                    disabledLabelColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    disabledLeadingIconColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    selectedContainerColor = MaterialTheme.colorScheme.secondary,
                    disabledSelectedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                    selectedLabelColor = MaterialTheme.colorScheme.onSecondary,
                    selectedLeadingIconColor = MaterialTheme.colorScheme.onSecondary
                )
            )
        """.trimIndent()
    }

    component {
        name = "Input chip"
        shortName = "Input"
        specUrl = "https://m3.material.io/components/chips/specs#facb7c02-74c4-4b81-bd52-6ad10ce351eb"
        guidelinesUrl = "https://m3.material.io/components/chips/guidelines#4d2d5ef5-3fcd-46e9-99f2-067747b2393f"
        docsUrl =
            "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#InputChip(kotlin.Boolean,kotlin.Function0,kotlin.Function0,androidx.compose.ui.Modifier,kotlin.Boolean,kotlin.Function0,kotlin.Function0,kotlin.Function0,androidx.compose.ui.graphics.Shape,androidx.compose.material3.SelectableChipColors,androidx.compose.material3.SelectableChipElevation,androidx.compose.material3.SelectableChipBorder,androidx.compose.foundation.interaction.MutableInteractionSource)"
        screenshot = "chip_input"
        description = """
            Chips help people enter information, make selections, filter content, or trigger actions. Chips can show multiple interactive elements together in the same area, such as a list of selectable movie times, or a series of email contacts.

            Input chips represent discrete pieces of information entered by a user.
        """.trimIndent()
        imports = listOf(
            "androidx.compose.foundation.layout.size",
            "androidx.compose.material.icons.Icons",
            "androidx.compose.material.icons.filled.Person",
            "androidx.compose.material3.ExperimentalMaterial3Api",
            "androidx.compose.material3.Icon",
            "androidx.compose.material3.InputChip",
            "androidx.compose.material3.InputChipDefaults",
            "androidx.compose.material3.Text",
            "androidx.compose.runtime.Composable",
            "androidx.compose.runtime.getValue",
            "androidx.compose.runtime.mutableStateOf",
            "androidx.compose.runtime.remember",
            "androidx.compose.runtime.setValue",
            "androidx.compose.ui.Modifier",
        )
        code = """
            var selected by remember { mutableStateOf(false) }

            InputChip(
                selected = selected,
                onClick = { selected = !selected },
                label = { Text("Person") },
                avatar = {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "Person",
                        modifier = Modifier.size(InputChipDefaults.AvatarSize)
                    )
                }
            )
        """.trimIndent()
        customCode = """
            var selected by remember { mutableStateOf(false) }
    
            InputChip(
                modifier = Modifier,
                selected = selected,
                onClick = { selected = !selected },
                label = { Text("Input") },
                avatar = {
                    Icon(
                        modifier = Modifier.size(InputChipDefaults.AvatarSize),
                        imageVector = Icons.Filled.Person,
                        contentDescription = "Person"
                    )
                },
                enabled = true,
                shape = RoundedCornerShape(12.dp),
                colors = InputChipDefaults.inputChipColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    labelColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                    disabledLabelColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    selectedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                    disabledSelectedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                    selectedLabelColor = MaterialTheme.colorScheme.onSecondaryContainer
                )
            )
        """.trimIndent()
    }

    component {
        name = "Suggestion chip"
        shortName = "Suggestion"
        specUrl = "https://m3.material.io/components/chips/specs#67a358c0-c370-4bf1-b410-7f8dd3f1a60c"
        guidelinesUrl = "https://m3.material.io/components/chips/guidelines#36d7bb16-a9bf-4cf6-a73d-8e05510d66a7"
        docsUrl =
            "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#SuggestionChip(kotlin.Function0,kotlin.Function0,androidx.compose.ui.Modifier,kotlin.Boolean,kotlin.Function0,androidx.compose.ui.graphics.Shape,androidx.compose.material3.ChipColors,androidx.compose.material3.ChipElevation,androidx.compose.material3.ChipBorder,androidx.compose.foundation.interaction.MutableInteractionSource)"
        screenshot = "chip_suggestion"
        description = """
            Chips help people enter information, make selections, filter content, or trigger actions. Chips can show multiple interactive elements together in the same area, such as a list of selectable movie times, or a series of email contacts.

            Suggestion chips help narrow a user's intent by presenting dynamically generated suggestions, such as possible responses or search filters.
        """.trimIndent()
        imports = listOf(
            "androidx.compose.material3.SuggestionChip",
            "androidx.compose.material3.Text",
        )
        code = """
            SuggestionChip(
                onClick = {
                    /* Do something! */
                },
                label = {
                    Text("Thank you")
                }
            )
        """.trimIndent()
        customCode = """
            SuggestionChip(
                modifier = Modifier,
                onClick = {
                    /* Do something! */
                },
                label = {
                    Text("Thank you")
                },
                enabled = true,
                shape = RoundedCornerShape(12.dp),
                colors = SuggestionChipDefaults.suggestionChipColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    labelColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                    disabledLabelColor = MaterialTheme.colorScheme.onSecondaryContainer
                )
            )
        """.trimIndent()
    }
}