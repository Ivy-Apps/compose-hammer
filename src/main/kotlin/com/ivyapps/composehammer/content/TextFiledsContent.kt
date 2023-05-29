package com.ivyapps.composehammer.content

import com.ivyapps.composehammer.domain.ContentScope
import com.ivyapps.composehammer.domain.component
import com.ivyapps.composehammer.domain.group

fun ContentScope.textFields() = group("Text fields") {
    component {
        name = "Filled text field"
        shortName = "Filled"
        specUrl = "https://m3.material.io/components/text-fields/specs#6d654d1d-262e-4697-858c-9a75e8e7c81d"
        guidelinesUrl = "https://m3.material.io/components/text-fields/guidelines"
        docsUrl =
            "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#TextField(androidx.compose.ui.text.input.TextFieldValue,kotlin.Function1,androidx.compose.ui.Modifier,kotlin.Boolean,kotlin.Boolean,androidx.compose.ui.text.TextStyle,kotlin.Function0,kotlin.Function0,kotlin.Function0,kotlin.Function0,kotlin.Function0,kotlin.Function0,kotlin.Function0,kotlin.Boolean,androidx.compose.ui.text.input.VisualTransformation,androidx.compose.foundation.text.KeyboardOptions,androidx.compose.foundation.text.KeyboardActions,kotlin.Boolean,kotlin.Int,kotlin.Int,androidx.compose.foundation.interaction.MutableInteractionSource,androidx.compose.ui.graphics.Shape,androidx.compose.material3.TextFieldColors)"
        screenshot = "textfield_filled"
        description = """
            Text fields allow users to enter text into a UI. They typically appear in forms and dialogs. Filled text fields have more visual emphasis than outlined text fields, making them stand out when surrounded by other content and components.
            If you are looking for an outlined version, see OutlinedTextField.
        """.trimIndent()
        imports = listOf(
            "androidx.compose.material3.Text",
            "androidx.compose.material3.TextField",
            
            "androidx.compose.runtime.getValue",
            "androidx.compose.runtime.mutableStateOf",
            "androidx.compose.runtime.saveable.rememberSaveable",
            "androidx.compose.runtime.setValue",
            "androidx.compose.ui.text.TextRange",
            "androidx.compose.ui.text.input.TextFieldValue",
        )
        code = """
            var text by rememberSaveable(stateSaver = TextFieldValue.Saver) {
                mutableStateOf(TextFieldValue("", TextRange(0, 7)))
            }
    
            TextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Label") }
            )
        """.trimIndent()
        customCode = """
            var text by rememberSaveable(stateSaver = TextFieldValue.Saver) {
                mutableStateOf(TextFieldValue("", TextRange(0, 7)))
            }
    
            TextField(
                modifier = Modifier,
                value = text,
                onValueChange = { text = it },
                label = { Text("Label") },
                enabled = true,
                readOnly = false,
                textStyle = MaterialTheme.typography.bodyMedium,
                placeholder = {
                    Text(text = "placeholder")
                },
                supportingText = {
                    Text(text = "Supporting text")
                },
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.colors(
                    // Customize colors here
                )
            )
        """.trimIndent()
    }

    component {
        name = "Outlined text field"
        shortName = "Outlined"
        specUrl = "https://m3.material.io/components/text-fields/specs#68b00bd6-ab40-4b4f-93d9-ed1fbbc5d06e"
        guidelinesUrl = "https://m3.material.io/components/text-fields/guidelines#1206edf3-4472-4c05-90e7-8efe7e409ea2"
        docsUrl =
            "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#OutlinedTextField(androidx.compose.ui.text.input.TextFieldValue,kotlin.Function1,androidx.compose.ui.Modifier,kotlin.Boolean,kotlin.Boolean,androidx.compose.ui.text.TextStyle,kotlin.Function0,kotlin.Function0,kotlin.Function0,kotlin.Function0,kotlin.Function0,kotlin.Function0,kotlin.Function0,kotlin.Boolean,androidx.compose.ui.text.input.VisualTransformation,androidx.compose.foundation.text.KeyboardOptions,androidx.compose.foundation.text.KeyboardActions,kotlin.Boolean,kotlin.Int,kotlin.Int,androidx.compose.foundation.interaction.MutableInteractionSource,androidx.compose.ui.graphics.Shape,androidx.compose.material3.TextFieldColors)"
        screenshot = "textfield_outlined"
        description = """
            Outlined text fields have less visual emphasis than filled text fields. When they appear in places like forms (where many text fields are placed together) their reduced emphasis helps simplify the layout.
        """.trimIndent()
        imports = listOf(
            "androidx.compose.material3.OutlinedTextField",
            "androidx.compose.material3.Text",
            
            "androidx.compose.runtime.getValue",
            "androidx.compose.runtime.mutableStateOf",
            "androidx.compose.runtime.saveable.rememberSaveable",
            "androidx.compose.runtime.setValue",
        )
        code = """
            var text by rememberSaveable { mutableStateOf("") }
    
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Label") }
            )
        """.trimIndent()
        customCode = """
            var text by rememberSaveable { mutableStateOf("") }
    
            OutlinedTextField(
                modifier = Modifier,
                value = text,
                onValueChange = { text = it },
                label = { Text("Label") },
                enabled = true,
                readOnly = false,
                textStyle = MaterialTheme.typography.bodyMedium,
                placeholder = {
                    Text(text = "placeholder")
                },
                supportingText = {
                    Text(text = "Supporting text")
                },
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    // Customize colors here
                )
            )
        """.trimIndent()
    }
}