package com.ivyapps.composehammer.m3content

import com.ivyapps.composehammer.domain.ContentScope
import com.ivyapps.composehammer.domain.component
import com.ivyapps.composehammer.domain.group

fun ContentScope.progressIndicators() = group("Progress indicators") {
    component {
        name = "Linear determinate"
        specUrl = "https://m3.material.io/components/progress-indicators/specs#b4bf0322-bfe6-4fad-babc-7802c691f135"
        guidelinesUrl =
            "https://m3.material.io/components/progress-indicators/guidelines#c73006ec-d3ae-4b15-b060-d72b01b2085f"
        docsUrl =
            "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#LinearProgressIndicator(androidx.compose.ui.Modifier,androidx.compose.ui.graphics.Color,androidx.compose.ui.graphics.Color,androidx.compose.ui.graphics.StrokeCap)"
        screenshot = "progressindicator_linear_determinate"
        description = """
            Linear progress indicators display progress by animating along the length of a fixed, visible track. The behavior of the indicator is dependent on whether the progress of a process is known.
            Determinate operations display the indicator increasing from 0 to 100% of the track, in sync with the process’s progress.
        """.trimIndent()
        imports = listOf(
            "androidx.compose.material3.LinearProgressIndicator",
            
            "androidx.compose.ui.Modifier",
        )
        code = """
            LinearProgressIndicator(
                modifier = Modifier,
                progress = 0.5f,
            )
        """.trimIndent()
        customCode = """
            var progress by remember { mutableStateOf(0.1f) }
            val animatedProgress by animateFloatAsState(
                targetValue = progress,
                animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec,
                label = "Progress animation"
            )
    
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                LinearProgressIndicator(
                    modifier = Modifier
                        .semantics(mergeDescendants = true) {}
                        .padding(10.dp),
                    progress = animatedProgress,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    trackColor = MaterialTheme.colorScheme.primaryContainer,
                    strokeCap = ProgressIndicatorDefaults.LinearStrokeCap
                )
    
                Spacer(Modifier.requiredHeight(30.dp))
    
                OutlinedButton(
                    onClick = {
                        if (progress < 1f) progress += 0.1f
                    }
                ) {
                    Text("Increase")
                }
            }
        """.trimIndent()
    }

    component {
        name = "Linear indeterminate (infinite)"
        specUrl = "https://m3.material.io/components/progress-indicators/specs#b4bf0322-bfe6-4fad-babc-7802c691f135"
        guidelinesUrl =
            "https://m3.material.io/components/progress-indicators/guidelines#c73006ec-d3ae-4b15-b060-d72b01b2085f"
        docsUrl =
            "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#LinearProgressIndicator(androidx.compose.ui.Modifier,androidx.compose.ui.graphics.Color,androidx.compose.ui.graphics.Color,androidx.compose.ui.graphics.StrokeCap)"
        screenshot = "progressindicator_linear_indeterminate"
        description = """
            Linear progress indicators display progress by animating along the length of a fixed, visible track. The behavior of the indicator is dependent on whether the progress of a process is known.
            Indeterminate operations display the indicator continually growing and shrinking along the track until the process is complete.
        """.trimIndent()
        imports = listOf(
            "androidx.compose.material3.LinearProgressIndicator",
            
            "androidx.compose.ui.Modifier",
        )
        code = """
            LinearProgressIndicator(
                modifier = Modifier,
            )
        """.trimIndent()
        customCode = """
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LinearProgressIndicator(
                    modifier = Modifier
                        .semantics(mergeDescendants = true) {}
                        .padding(10.dp),
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    trackColor = MaterialTheme.colorScheme.primaryContainer,
                    strokeCap = ProgressIndicatorDefaults.LinearStrokeCap
                )
            }
        """.trimIndent()
    }

    component {
        name = "Circular determinate"
        specUrl = "https://m3.material.io/components/progress-indicators/specs#c6a801ca-8a87-4529-8eb1-2c8e9791e3b0"
        guidelinesUrl =
            "https://m3.material.io/components/progress-indicators/guidelines#b1d0217c-e8b5-46c6-906d-fca334d96092"
        docsUrl =
            "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#CircularProgressIndicator(androidx.compose.ui.Modifier,androidx.compose.ui.graphics.Color,androidx.compose.ui.unit.Dp,androidx.compose.ui.graphics.Color,androidx.compose.ui.graphics.StrokeCap)"
        screenshot = "progressindicator_circular_determinate"
        description = """
            Circular progress indicators display progress by animating along an invisible circular track in a clockwise direction. They can be applied directly to a surface, such as a button or card.
            On Android, the “swipe to refresh” gesture displays a circular progress indicator to indicate that the UI is being refreshed.
            Determinate circular indicators fill the invisible, circular track with color, as the indicator moves from 0 to 360 degrees.
        """.trimIndent()
        imports = listOf(
            "androidx.compose.material3.CircularProgressIndicator",
            
            "androidx.compose.ui.Modifier",
        )
        code = """
            CircularProgressIndicator(
                modifier = Modifier,
                progress = 0.5f,
            )
        """.trimIndent()
        customCode = """
            var progress by remember { mutableStateOf(0.1f) }
            val animatedProgress by animateFloatAsState(
                targetValue = progress,
                animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec,
                label = "Progress bar"
            )
    
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                    modifier = Modifier,
                    strokeWidth = 4.dp,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    trackColor = MaterialTheme.colorScheme.primaryContainer,
                    strokeCap = ProgressIndicatorDefaults.LinearStrokeCap,
                    progress = animatedProgress
                )
    
                Spacer(Modifier.requiredHeight(30.dp))
    
                OutlinedButton(
                    onClick = {
                        if (progress < 1f) progress += 0.1f
                    }
                ) {
                    Text("Increase")
                }
            }
        """.trimIndent()
    }

    component {
        name = "Circular indeterminate (infinite)"
        specUrl = "https://m3.material.io/components/progress-indicators/specs#c6a801ca-8a87-4529-8eb1-2c8e9791e3b0"
        guidelinesUrl =
            "https://m3.material.io/components/progress-indicators/guidelines#b1d0217c-e8b5-46c6-906d-fca334d96092"
        docsUrl =
            "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#CircularProgressIndicator(androidx.compose.ui.Modifier,androidx.compose.ui.graphics.Color,androidx.compose.ui.unit.Dp,androidx.compose.ui.graphics.Color,androidx.compose.ui.graphics.StrokeCap)"
        screenshot = "progressindicator_circular_indeterminate"
        description = """
            Circular progress indicators display progress by animating along an invisible circular track in a clockwise direction. They can be applied directly to a surface, such as a button or card.
            On Android, the “swipe to refresh” gesture displays a circular progress indicator to indicate that the UI is being refreshed.
            Indeterminate circular indicators grow and shrink in size while moving along the invisible track.
        """.trimIndent()
        imports = listOf(
            "androidx.compose.material3.CircularProgressIndicator",
            
            "androidx.compose.ui.Modifier",
        )
        code = """
            CircularProgressIndicator(
                modifier = Modifier,
            )
        """.trimIndent()
        customCode = """
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                    modifier = Modifier,
                    strokeWidth = 4.dp,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    trackColor = MaterialTheme.colorScheme.primaryContainer,
                    strokeCap = ProgressIndicatorDefaults.LinearStrokeCap
                )
            }
        """.trimIndent()
    }
}