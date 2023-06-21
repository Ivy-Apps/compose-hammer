package com.ivyapps.composehammer.m3content

import com.ivyapps.composehammer.domain.ContentScope
import com.ivyapps.composehammer.domain.component
import com.ivyapps.composehammer.domain.group

fun ContentScope.animations() = group(
    title = "⚡ Animations",
    showInToolWindow = false
) {
    component {
        showInToolWindow = false
        name = "Visibility: expand/shrink"
        imports = listOf(
            "androidx.compose.animation.AnimatedVisibility",
            "androidx.compose.animation.expandVertically",
            "androidx.compose.animation.fadeIn",
            "androidx.compose.animation.fadeOut",
            "androidx.compose.animation.shrinkVertically",
            "androidx.compose.runtime.getValue",
            "androidx.compose.runtime.mutableStateOf",
            "androidx.compose.runtime.remember",
            "androidx.compose.runtime.setValue",
            "androidx.compose.ui.Modifier",

            )
        code = """
            var visible by remember { mutableStateOf(false) }
            AnimatedVisibility(
                modifier = Modifier,
                visible = visible,
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically(),
            ) {
        
            }
        """.trimIndent()
    }

    component {
        showInToolWindow = false
        name = "Visibility: slide in/out"
        imports = listOf(
            "androidx.compose.animation.AnimatedVisibility",
            "androidx.compose.animation.slideInVertically",
            "androidx.compose.animation.slideOutVertically",
            "androidx.compose.foundation.layout.Column",
            "androidx.compose.runtime.Composable",
            "androidx.compose.runtime.LaunchedEffect",
            "androidx.compose.runtime.getValue",
            "androidx.compose.runtime.mutableStateOf",
            "androidx.compose.runtime.remember",
            "androidx.compose.runtime.setValue",
            "androidx.compose.ui.Modifier",
            "kotlinx.coroutines.delay",
        )
        code = """
            var visible by remember { mutableStateOf(false) }
            LaunchedEffect(Unit) {
                // [DELETE THIS] If not needed
                delay(1_000L) // show after 1 second
                visible = true
            }
        
            AnimatedVisibility(
                modifier = Modifier,
                visible = visible,
                enter = slideInVertically { fullHeight -> fullHeight },
                exit = slideOutVertically { fullHeight -> fullHeight },
            ) {
                // The content below will be animated:
                Column(
                    modifier = Modifier,
                ) {
        
                }
            }
        """.trimIndent()
    }

    component {
        showInToolWindow = false
        name = "Crossfade"
        imports = listOf(
            "androidx.compose.animation.Crossfade",
            "androidx.compose.runtime.getValue",
            "androidx.compose.runtime.mutableStateOf",
            "androidx.compose.runtime.remember",
            "androidx.compose.runtime.setValue",

            )
        code = """
            var visible by remember { mutableStateOf(false) }
            Crossfade(
                targetState = visible,
                label = "crossfade content"
            ) { state ->
                when (state) {
                    true -> {
        
                    }
                    false -> {
        
                    }
                }
            }
        """.trimIndent()
    }

    component {
        showInToolWindow = false
        name = "Animate Float"
        imports = listOf(
            "androidx.compose.animation.core.animateFloatAsState",
            "androidx.compose.runtime.getValue",
            "androidx.compose.runtime.mutableStateOf",
            "androidx.compose.runtime.remember",
            "androidx.compose.runtime.setValue",
        )
        code = """
            var visible by remember { mutableStateOf(false) }
            val alpha by animateFloatAsState(
                targetValue = if (visible) 1f else 0f,
                label = "alpha anim"
            )
            // use Modifier.alpha(alpha) if you want to animate alpha animation
        """.trimIndent()
    }

    component {
        showInToolWindow = false
        name = "Animate Dp"
        imports = listOf(
            "androidx.compose.animation.core.animateDpAsState",
            "androidx.compose.runtime.getValue",
            "androidx.compose.runtime.mutableStateOf",
            "androidx.compose.runtime.remember",
            "androidx.compose.runtime.setValue",
            "androidx.compose.ui.unit.dp",
        )
        code = """
            var visible by remember { mutableStateOf(false) }
            val animatedDp by animateDpAsState(
                targetValue = if (visible) 0.dp else 48.dp,
                label = "dp anim"
            )
        """.trimIndent()
    }

    component {
        showInToolWindow = false
        name = "Animate Color"
        imports = listOf(
            "androidx.compose.animation.animateColorAsState",
            "androidx.compose.runtime.getValue",
            "androidx.compose.runtime.mutableStateOf",
            "androidx.compose.runtime.remember",
            "androidx.compose.runtime.setValue",
            "androidx.compose.ui.graphics.Color",
        )
        code = """
            var visible by remember { mutableStateOf(false) }
            val animatedColor by animateColorAsState(
                targetValue = if (visible) Color.Blue else Color.Red,
                label = "color anim"
            )
        """.trimIndent()
    }
}