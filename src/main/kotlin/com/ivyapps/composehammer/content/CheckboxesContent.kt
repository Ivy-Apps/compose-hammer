package com.ivyapps.composehammer.content

import com.ivyapps.composehammer.domain.ContentScope
import com.ivyapps.composehammer.domain.component
import com.ivyapps.composehammer.domain.group

fun ContentScope.checkboxes() = group("Checkboxes") {
    component {
        name = "Checkbox"
        specUrl = "https://m3.material.io/components/checkbox/specs"
        guidelinesUrl = "https://m3.material.io/components/checkbox/guidelines"
        docsUrl =
            "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#Checkbox(kotlin.Boolean,kotlin.Function1,androidx.compose.ui.Modifier,kotlin.Boolean,androidx.compose.material3.CheckboxColors,androidx.compose.foundation.interaction.MutableInteractionSource)"
        screenshot = "checkbox"
        description = """
            Use checkboxes to:
            - Select one or more options from a list
            - Present a list containing sub-selections
            - Turn an item on or off in a desktop environment
            >Checkboxes should be used instead of switches if multiple options can be selected from a list.
        """.trimIndent()
        imports = listOf(
            "androidx.compose.material3.Checkbox",
            
            "androidx.compose.runtime.getValue",
            "androidx.compose.runtime.mutableStateOf",
            "androidx.compose.runtime.remember",
            "androidx.compose.runtime.setValue",
        )
        code = """
            var checked by remember { mutableStateOf(false) }
            Checkbox(
                checked = checked,
                onCheckedChange = { checked = it }
            )
        """.trimIndent()
        customCode = """
            val checkedState = remember { mutableStateOf(true) }

            Checkbox(
                modifier = Modifier,
                checked = checkedState.value,
                onCheckedChange = { checkedState.value = it },
                enabled = true,
                colors = CheckboxDefaults.colors(
                    checkedColor = MaterialTheme.colorScheme.secondary,
                    uncheckedColor = MaterialTheme.colorScheme.secondaryContainer,
                    checkmarkColor = MaterialTheme.colorScheme.tertiaryContainer,
                    disabledCheckedColor = MaterialTheme.colorScheme.tertiaryContainer,
                    disabledUncheckedColor = MaterialTheme.colorScheme.tertiaryContainer,
                    disabledIndeterminateColor = MaterialTheme.colorScheme.onTertiaryContainer
                )
            )
        """.trimIndent()
    }

    component {
        name = "Tri-state checkbox"
        specUrl = "https://m3.material.io/components/checkbox/specs"
        guidelinesUrl = "https://m3.material.io/components/checkbox/guidelines"
        docsUrl =
            "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#TriStateCheckbox(androidx.compose.ui.state.ToggleableState,kotlin.Function0,androidx.compose.ui.Modifier,kotlin.Boolean,androidx.compose.material3.CheckboxColors,androidx.compose.foundation.interaction.MutableInteractionSource)"
        screenshot = "checkbox_tri_state"
        description = """
            A checkbox with three states: On / Off / Indeterminate.
            Checkboxes can have a parent-child relationship with other checkboxes. 
            When the parent checkbox is checked, all child checkboxes are checked. 
            If a parent checkbox is unchecked, all child checkboxes are unchecked. 
            If some, but not all, child checkboxes are checked, 
            the parent checkbox becomes an indeterminate checkbox.
        """.trimIndent()
        imports = listOf(
            "androidx.compose.material3.TriStateCheckbox",
            
            "androidx.compose.ui.state.ToggleableState",
        )
        code = """
            TriStateCheckbox(
                state = ToggleableState.On,
                onClick = {
                    /* Do something! */
                }
            )
        """.trimIndent()
        customCode = """
            Column {
                // define dependent checkboxes states
                val (state, onStateChange) = remember { mutableStateOf(true) }
                val (state2, onStateChange2) = remember { mutableStateOf(false) }
    
                // TriStateCheckbox state reflects state of dependent checkboxes
                val parentState = remember(state, state2) {
                    if (state && state2) ToggleableState.On
                    else if (!state && !state2) ToggleableState.Off
                    else ToggleableState.Indeterminate
                }
                // click on TriStateCheckbox can set state for dependent checkboxes
                val onParentClick = {
                    val s = parentState != ToggleableState.On
                    onStateChange(s)
                    onStateChange2(s)
                }
    
                /*
                 The sample below composes just basic checkboxes which are not fully accessible on
                 their own. See the CheckboxWithTextSample as a way to ensure your checkboxes are
                 fully accessible.
                 */
                TriStateCheckbox(
                    modifier = Modifier,
                    state = parentState,
                    onClick = onParentClick,
                    enabled = true,
                    colors = CheckboxDefaults.colors()
                )
    
                Spacer(Modifier.size(25.dp))
    
                Column(Modifier.padding(10.dp, 0.dp, 0.dp, 0.dp)) {
                    Checkbox(state, onStateChange)
                    Spacer(Modifier.size(25.dp))
                    Checkbox(state2, onStateChange2)
                }
            }
        """.trimIndent()
    }
}