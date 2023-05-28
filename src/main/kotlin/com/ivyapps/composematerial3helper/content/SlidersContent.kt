package com.ivyapps.composematerial3helper.content

import com.ivyapps.composematerial3helper.domain.ContentScope
import com.ivyapps.composematerial3helper.domain.component
import com.ivyapps.composematerial3helper.domain.group

fun ContentScope.sliders() = group("Sliders") {
    component {
        name = "Slider"
        specUrl = "https://m3.material.io/components/sliders/specs"
        guidelinesUrl = "https://m3.material.io/components/sliders/guidelines"
        docsUrl =
            "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#Slider(kotlin.Float,kotlin.Function1,androidx.compose.ui.Modifier,kotlin.Boolean,kotlin.Function0,androidx.compose.material3.SliderColors,androidx.compose.foundation.interaction.MutableInteractionSource,kotlin.Int,kotlin.Function1,kotlin.Function1,kotlin.ranges.ClosedFloatingPointRange)"
        screenshot = "slider"
        description = """
            Sliders allow users to make selections from a range of values.

            Sliders reflect a range of values along a bar, from which users may select a single value. They are ideal for adjusting settings such as volume, brightness, or applying image filters.
            Use continuous sliders to allow users to make meaningful selections that don’t require a specific value.
        """.trimIndent()
        imports = listOf(
            "androidx.compose.material3.Slider",
            "androidx.compose.runtime.Composable",
            "androidx.compose.runtime.getValue",
            "androidx.compose.runtime.mutableStateOf",
            "androidx.compose.runtime.remember",
            "androidx.compose.runtime.setValue",
            "androidx.compose.ui.Modifier",
            "androidx.compose.ui.semantics.contentDescription",
            "androidx.compose.ui.semantics.semantics",
        )
        code = """
            var sliderPosition by remember { mutableStateOf(0f) }
    
            Slider(
                modifier = Modifier.semantics { contentDescription = "" },
                value = sliderPosition,
                onValueChange = { sliderPosition = it }
            )
        """.trimIndent()
        customCode = """
            var sliderPosition by remember { mutableStateOf(0f) }
    
            Column {
                Text(text = sliderPosition.toString())
    
                Slider(
                    modifier = Modifier.semantics { contentDescription = "" },
                    value = sliderPosition,
                    onValueChange = { sliderPosition = it },
                    enabled = true,
                    steps = 5,
                    colors = SliderDefaults.colors(
                        thumbColor = MaterialTheme.colorScheme.primary,
                        activeTrackColor = MaterialTheme.colorScheme.primary,
                        activeTickColor = MaterialTheme.colorScheme.primary,
                        inactiveTrackColor = MaterialTheme.colorScheme.secondary,
                        inactiveTickColor = MaterialTheme.colorScheme.secondary,
                        disabledThumbColor = MaterialTheme.colorScheme.secondary,
                        disabledActiveTrackColor = MaterialTheme.colorScheme.secondary,
                        disabledActiveTickColor = MaterialTheme.colorScheme.secondary,
                        disabledInactiveTrackColor = MaterialTheme.colorScheme.secondary,
                        disabledInactiveTickColor = MaterialTheme.colorScheme.secondary
                    )
                )
            }
        """.trimIndent()
    }

    component {
        name = "Range Slider"
        specUrl = "https://m3.material.io/components/sliders/specs"
        guidelinesUrl = "https://m3.material.io/components/sliders/guidelines"
        docsUrl =
            "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#RangeSlider(kotlin.ranges.ClosedFloatingPointRange,kotlin.Function1,androidx.compose.ui.Modifier,kotlin.Boolean,kotlin.ranges.ClosedFloatingPointRange,kotlin.Function0,androidx.compose.material3.SliderColors,androidx.compose.foundation.interaction.MutableInteractionSource,androidx.compose.foundation.interaction.MutableInteractionSource,kotlin.Function1,kotlin.Function1,kotlin.Function1,kotlin.Int)"
        screenshot = "slider_range"
        description = """
            Range Sliders expand upon Slider using the same concepts but allow the user to select 2 values.

            The two values are still bounded by the value range but they also cannot cross each other.

            It uses the provided startThumb for the slider's start thumb and endThumb for the slider's end thumb. 
            It also uses the provided track for the slider's track. If nothing is passed for these parameters, it will use SliderDefaults.Thumb and SliderDefaults.Track for the thumbs and track.

            Use continuous Range Sliders to allow users to make meaningful selections that don’t require a specific values.
        """.trimIndent()
        imports = listOf(
            "androidx.compose.material3.RangeSlider",
            "androidx.compose.runtime.Composable",
            "androidx.compose.runtime.getValue",
            "androidx.compose.runtime.mutableStateOf",
            "androidx.compose.runtime.remember",
            "androidx.compose.runtime.setValue",
            "androidx.compose.ui.Modifier",
        )
        code = """
            var sliderPosition by remember { mutableStateOf(0f..100f) }
    
            RangeSlider(
                modifier = Modifier,
                steps = 5,
                value = sliderPosition,
                onValueChange = { sliderPosition = it },
                valueRange = 0f..100f,
                onValueChangeFinished = {}
            )
        """.trimIndent()
        customCode = """
            var sliderPosition by remember { mutableStateOf(0f..100f) }
    
            Column {
                Text(text = sliderPosition.toString())
    
                RangeSlider(
                    modifier = Modifier.semantics { contentDescription = "" },
                    steps = 5,
                    value = sliderPosition,
                    onValueChange = { sliderPosition = it },
                    valueRange = 0f..100f,
                    onValueChangeFinished = {},
                    enabled = true,
                    colors = SliderDefaults.colors(
                        thumbColor = MaterialTheme.colorScheme.primary,
                        activeTrackColor = MaterialTheme.colorScheme.primary,
                        activeTickColor = MaterialTheme.colorScheme.primary,
                        inactiveTrackColor = MaterialTheme.colorScheme.secondary,
                        inactiveTickColor = MaterialTheme.colorScheme.secondary,
                        disabledThumbColor = MaterialTheme.colorScheme.secondary,
                        disabledActiveTrackColor = MaterialTheme.colorScheme.secondary,
                        disabledActiveTickColor = MaterialTheme.colorScheme.secondary,
                        disabledInactiveTrackColor = MaterialTheme.colorScheme.secondary,
                        disabledInactiveTickColor = MaterialTheme.colorScheme.secondary
                    )
                )
            }
        """.trimIndent()
    }
}