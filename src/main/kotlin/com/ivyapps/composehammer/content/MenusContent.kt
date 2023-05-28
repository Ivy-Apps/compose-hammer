package com.ivyapps.composehammer.content

import com.ivyapps.composehammer.domain.ContentScope
import com.ivyapps.composehammer.domain.component
import com.ivyapps.composehammer.domain.group

fun ContentScope.menus() = group("Dropdown menus") {
    component {
        name = "Dropdown menu"
        shortName = "Dropdown"
        specUrl = "https://m3.material.io/components/menus/specs"
        guidelinesUrl = "https://m3.material.io/components/menus/guidelines"
        docsUrl =
            "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#DropdownMenu(kotlin.Boolean,kotlin.Function0,androidx.compose.ui.Modifier,androidx.compose.ui.unit.DpOffset,androidx.compose.foundation.ScrollState,androidx.compose.ui.window.PopupProperties,kotlin.Function1)"
        screenshot = "menu_dropdown"
        description = """
            Use a menu to display a list of choices on a temporary surface, such as a set of overflow actions in a top app bar. 

            Menus allow users to make a selection from multiple options. They’re less prominent and take up less space than a set of radio buttons or choice chips.

            A menu opens upon interaction with an element (such as an icon, button, or input field) or when users perform a specific action.
        """.trimIndent()
        imports = listOf(
            "androidx.compose.material.icons.Icons",
            "androidx.compose.material.icons.outlined.Edit",
            "androidx.compose.material.icons.outlined.Settings",
            "androidx.compose.material3.DropdownMenu",
            "androidx.compose.material3.DropdownMenuItem",
            "androidx.compose.material3.Icon",
            "androidx.compose.material3.Text",
            "androidx.compose.runtime.Composable",
            "androidx.compose.runtime.getValue",
            "androidx.compose.runtime.mutableStateOf",
            "androidx.compose.runtime.remember",
            "androidx.compose.runtime.setValue",
        )
        code = """
            var expanded by remember { mutableStateOf(false) }
            
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Edit") },
                    onClick = {
                        /* Handle edit! */
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Edit,
                            contentDescription = "Edit"
                        )
                    }
                )
                DropdownMenuItem(
                    text = { Text("Settings") },
                    onClick = { /* Handle settings! */ },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Settings,
                            contentDescription = "Settings"
                        )
                    }
                )
            }
        """.trimIndent()
        customCode = """
            // Overflow menu
            var expanded by remember { mutableStateOf(false) }
    
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.TopStart)
            ) {
                IconButton(
                    modifier = Modifier,
                    onClick = { expanded = true },
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = MaterialTheme.colorScheme.background,
                        contentColor = MaterialTheme.colorScheme.primary,
                        disabledContainerColor = MaterialTheme.colorScheme.background,
                        disabledContentColor = MaterialTheme.colorScheme.onBackground
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "More menu"
                    )
                }
    
                DropdownMenu(
                    modifier = Modifier,
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(
                        modifier = Modifier,
                        text = { Text("Edit") },
                        onClick = {
                            /* Handle edit! */
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Edit,
                                contentDescription = "Edit"
                            )
                        },
                        colors = MenuDefaults.itemColors(
                            textColor = MaterialTheme.colorScheme.onBackground,
                            leadingIconColor = MaterialTheme.colorScheme.onBackground,
                            trailingIconColor = MaterialTheme.colorScheme.onBackground,
                            disabledTextColor = MaterialTheme.colorScheme.onBackground,
                            disabledLeadingIconColor = MaterialTheme.colorScheme.onBackground,
                            disabledTrailingIconColor = MaterialTheme.colorScheme.onBackground
                        )
                    )
    
                    DropdownMenuItem(
                        text = { Text("Settings") },
                        onClick = {
                            /* Handle settings! */
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Settings,
                                contentDescription = "Settings"
                            )
                        },
                        colors = MenuDefaults.itemColors(
                            textColor = MaterialTheme.colorScheme.onBackground,
                            leadingIconColor = MaterialTheme.colorScheme.onBackground,
                            trailingIconColor = MaterialTheme.colorScheme.onBackground,
                            disabledTextColor = MaterialTheme.colorScheme.onBackground,
                            disabledLeadingIconColor = MaterialTheme.colorScheme.onBackground,
                            disabledTrailingIconColor = MaterialTheme.colorScheme.onBackground
                        )
                    )
    
                    Divider()
    
                    DropdownMenuItem(
                        text = { Text("Send Feedback") },
                        onClick = {
                            /* Handle send feedback! */
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Email,
                                contentDescription = "Send feedback"
                            )
                        },
                        trailingIcon = { Text("F11", textAlign = TextAlign.Center) },
                        colors = MenuDefaults.itemColors(
                            textColor = MaterialTheme.colorScheme.onBackground,
                            leadingIconColor = MaterialTheme.colorScheme.onBackground,
                            trailingIconColor = MaterialTheme.colorScheme.onBackground,
                            disabledTextColor = MaterialTheme.colorScheme.onBackground,
                            disabledLeadingIconColor = MaterialTheme.colorScheme.onBackground,
                            disabledTrailingIconColor = MaterialTheme.colorScheme.onBackground
                        )
                    )
                }
            }
        """.trimIndent()
    }

    component {
        name = "Exposed dropdown menu"
        shortName = "Exposed dropdown"
        specUrl = "https://m3.material.io/components/menus/specs"
        guidelinesUrl = "https://m3.material.io/components/menus/guidelines"
        docsUrl =
            "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#ExposedDropdownMenuBox(kotlin.Boolean,kotlin.Function1,androidx.compose.ui.Modifier,kotlin.Function1)"
        screenshot = "menu_exposed_dropdown"
        description = """
            Menus display a list of choices on a temporary surface. They appear when users interact with a button, action, or other control.

            Exposed dropdown menus display the currently selected item in a text field to which the menu is anchored. In some cases, it can accept and display user input (whether or not it’s listed as a menu choice). If the text field input is used to filter results in the menu, the component is also known as "autocomplete" or a "combobox".
        """.trimIndent()
        imports = listOf(
            "androidx.compose.material3.DropdownMenuItem",
            "androidx.compose.material3.ExperimentalMaterial3Api",
            "androidx.compose.material3.ExposedDropdownMenuBox",
            "androidx.compose.material3.ExposedDropdownMenuDefaults",
            "androidx.compose.material3.Text",
            "androidx.compose.material3.TextField",
            "androidx.compose.runtime.Composable",
            "androidx.compose.runtime.getValue",
            "androidx.compose.runtime.mutableStateOf",
            "androidx.compose.runtime.remember",
            "androidx.compose.runtime.setValue",
            "androidx.compose.ui.Modifier",
        )
        code = """
            val options = listOf("Option 1", "Option 2", "Option 3", "Option 4", "Option 5")
            var expanded by remember { mutableStateOf(false) }
            var selectedOptionText by remember { mutableStateOf(options[0]) }
    
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded },
            ) {
                TextField(
                    modifier = Modifier.menuAnchor(),
                    readOnly = true,
                    value = selectedOptionText,
                    onValueChange = {},
                    label = { Text("Label") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    colors = ExposedDropdownMenuDefaults.textFieldColors()
                )
    
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                ) {
                    options.forEach { selectionOption ->
                        DropdownMenuItem(
                            text = { Text(selectionOption) },
                            onClick = {
                                selectedOptionText = selectionOption
                                expanded = false
                            },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                        )
                    }
                }
            }
        """.trimIndent()
        customCode = """
            val options = listOf("Option 1", "Option 2", "Option 3", "Option 4", "Option 5")
            var expanded by remember { mutableStateOf(false) }
            var selectedOptionText by remember { mutableStateOf(options[0]) }
    
            ExposedDropdownMenuBox(
                modifier = Modifier,
                expanded = expanded,
                onExpandedChange = { expanded = !expanded },
            ) {
                TextField(
                    modifier = Modifier.menuAnchor(),
                    readOnly = true,
                    value = selectedOptionText,
                    onValueChange = {},
                    label = { Text("Label") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    colors = ExposedDropdownMenuDefaults.textFieldColors()
                )
    
                ExposedDropdownMenu(
                    modifier = Modifier,
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                ) {
                    options.forEach { selectionOption ->
                        DropdownMenuItem(
                            modifier = Modifier,
                            text = { Text(selectionOption) },
                            onClick = {
                                selectedOptionText = selectionOption
                                expanded = false
                            },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                        )
                    }
                }
            }
        """.trimIndent()
    }
}