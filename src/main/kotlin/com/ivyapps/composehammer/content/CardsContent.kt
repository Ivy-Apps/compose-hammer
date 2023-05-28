package com.ivyapps.composehammer.content

import com.ivyapps.composehammer.domain.ContentScope
import com.ivyapps.composehammer.domain.component
import com.ivyapps.composehammer.domain.group

fun ContentScope.cards() = group("Cards") {
    component {
        name = "Elevated card"
        specUrl = "https://m3.material.io/components/cards/specs#a012d40d-7a5c-4b07-8740-491dec79d58b"
        guidelinesUrl = "https://m3.material.io/components/cards/guidelines"
        docsUrl =
            "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#ElevatedCard(androidx.compose.ui.Modifier,androidx.compose.ui.graphics.Shape,androidx.compose.material3.CardColors,androidx.compose.material3.CardElevation,kotlin.Function1)"
        screenshot = "card_elevated"
        description = """
            Elevated cards contain content and actions that relate information about a subject. 
            They have a drop shadow, providing more separation from the background than filled cards, 
            but less than outlined cards.
        """.trimIndent()
        imports = listOf(
            "androidx.compose.foundation.layout.Column",
            "androidx.compose.foundation.layout.padding",
            "androidx.compose.material3.ElevatedCard",
            "androidx.compose.runtime.Composable",
            "androidx.compose.ui.Modifier",
            "androidx.compose.ui.unit.dp",
        )
        code = """
            ElevatedCard {
                Column(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                ) {
                    
                }
            }
        """.trimIndent()
        customCode = """
            ElevatedCard(
                modifier = Modifier,
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.elevatedCardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    disabledContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    disabledContentColor = MaterialTheme.colorScheme.onTertiaryContainer
                ),
                elevation = CardDefaults.cardElevation(1.dp)
            ) {
                Column(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = "Title",
                        style = MaterialTheme.typography.headlineMedium
                    )
    
                    Text(
                        text = "From your recent ones",
                        style = MaterialTheme.typography.bodySmall
                    )
    
                    Spacer(modifier = Modifier.height(8.dp))
    
                    Button(
                        onClick = {
                            /* Do something! */
                        }
                    ) {
                        Text("Buy")
                    }
                }
            }
        """.trimIndent()
    }

    component {
        name = "Filled card"
        specUrl = "https://m3.material.io/components/cards/specs#6192bdaa-bd56-45c9-97ff-d540ce5337ac"
        guidelinesUrl = "https://m3.material.io/components/cards/guidelines"
        docsUrl =
            "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#Card(androidx.compose.ui.Modifier,androidx.compose.ui.graphics.Shape,androidx.compose.material3.CardColors,androidx.compose.material3.CardElevation,androidx.compose.foundation.BorderStroke,kotlin.Function1)"
        screenshot = "card_filled"
        description = """
            Cards contain contain content and actions that relate information about a subject. 
            Filled cards provide subtle separation from the background. 
            This has less emphasis than elevated or outlined cards.
        """.trimIndent()
        imports = listOf(
            "androidx.compose.foundation.layout.Column",
            "androidx.compose.foundation.layout.padding",
            "androidx.compose.material3.Card",
            "androidx.compose.runtime.Composable",
            "androidx.compose.ui.Modifier",
            "androidx.compose.ui.unit.dp",
        )
        code = """
            Card {
                Column(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                ) {
    
                }
            }
        """.trimIndent()
        customCode = """
            Card(
                modifier = Modifier,
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    disabledContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    disabledContentColor = MaterialTheme.colorScheme.onTertiaryContainer
                )
            ) {
                Column(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = "Title",
                        style = MaterialTheme.typography.headlineMedium
                    )
    
                    Text(
                        text = "From your recent ones",
                        style = MaterialTheme.typography.bodySmall
                    )
    
                    Spacer(modifier = Modifier.height(8.dp))
    
                    Button(
                        onClick = {
                            /* Do something! */
                        }
                    ) {
                        Text("Buy")
                    }
                }
            }
        """.trimIndent()
    }

    component {
        name = "Outlined card"
        specUrl = "https://m3.material.io/components/cards/specs#9ad208b3-3d37-475c-a0eb-68cf845718f8"
        guidelinesUrl = "https://m3.material.io/components/cards/guidelines"
        docsUrl =
            "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#OutlinedCard(androidx.compose.ui.Modifier,androidx.compose.ui.graphics.Shape,androidx.compose.material3.CardColors,androidx.compose.material3.CardElevation,androidx.compose.foundation.BorderStroke,kotlin.Function1)"
        screenshot = "card_outlined"
        description = """
            Outlined cards contain content and actions that relate information about a subject. 
            They have a visual boundary around the container. 
            This can provide greater emphasis than the other types.
        """.trimIndent()
        imports = listOf(
            "androidx.compose.foundation.layout.Column",
            "androidx.compose.foundation.layout.padding",
            "androidx.compose.material3.OutlinedCard",
            "androidx.compose.runtime.Composable",
            "androidx.compose.ui.Modifier",
            "androidx.compose.ui.unit.dp",
        )
        code = """
            OutlinedCard {
                Column(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                ) {
                    
                }
            }
        """.trimIndent()
        customCode = """
            OutlinedCard(
                modifier = Modifier,
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.outlinedCardColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.onBackground,
                    disabledContainerColor = MaterialTheme.colorScheme.background,
                    disabledContentColor = MaterialTheme.colorScheme.onBackground
                ),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground)
            ) {
                Column(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = "Title",
                        style = MaterialTheme.typography.headlineMedium
                    )
    
                    Text(
                        text = "From your recent ones",
                        style = MaterialTheme.typography.bodySmall
                    )
    
                    Spacer(modifier = Modifier.height(8.dp))
    
                    Button(
                        onClick = {
                            /* Do something! */
                        }
                    ) {
                        Text("Buy")
                    }
                }
            }
        """.trimIndent()
    }
}