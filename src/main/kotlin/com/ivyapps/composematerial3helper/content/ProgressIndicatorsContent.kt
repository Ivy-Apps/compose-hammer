package com.ivyapps.composematerial3helper.content

import com.ivyapps.composematerial3helper.domain.ContentScope
import com.ivyapps.composematerial3helper.domain.component
import com.ivyapps.composematerial3helper.domain.group

fun ContentScope.progressIndicators() = group("Progress indicators") {
    component {
        name = "Linear determinate progress indicator"
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
            "androidx.compose.runtime.Composable",
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
        name = "Linear determinate progress indicator"
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
            "androidx.compose.runtime.Composable",
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
}