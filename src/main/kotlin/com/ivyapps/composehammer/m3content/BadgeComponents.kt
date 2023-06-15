package com.ivyapps.composehammer.m3content

import com.ivyapps.composehammer.domain.ContentScope
import com.ivyapps.composehammer.domain.component
import com.ivyapps.composehammer.domain.group

fun ContentScope.badges() = group("Badges") {
    component {
        name = "Badge"
        specUrl = "https://m3.material.io/components/badges/specs"
        guidelinesUrl = "https://m3.material.io/components/badges/guidelines"
        docsUrl =
            "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#Badge(androidx.compose.ui.Modifier,androidx.compose.ui.graphics.Color,androidx.compose.ui.graphics.Color,kotlin.Function1)"
        description = """
            Badges are used to indicate a notification, item count, 
            or other information relating to a navigation destination. 
            They are placed on the ending edge of icons, typically within other components.
        """.trimIndent()
        screenshot = "badge"
        imports = listOf(
            "androidx.compose.material.icons.Icons",
            "androidx.compose.material.icons.filled.ShoppingCart",
            "androidx.compose.material3.Badge",
            "androidx.compose.material3.BadgedBox",
            "androidx.compose.material3.Icon",
            "androidx.compose.material3.Text",
        )
        code = """
            BadgedBox(
                badge = {
                    Badge {
                        Text(text = "New")
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.ShoppingCart,
                    contentDescription = "Card"
                )
            }
        """.trimIndent()
        customCode = """
            BadgedBox(
                modifier = Modifier,
                badge = {
                    Badge(
                        modifier = Modifier.offset(x = (-16).dp, y = 8.dp),
                        containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                        contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
                    ) {
                        Text(
                            modifier = Modifier.padding(all = 4.dp),
                            text = "New"
                        )
                    }
                }
            ) {
                Button(
                    onClick = {
                        /* Do something! */
                    }
                ) {
                    Text("Shop")
                }
            }
        """.trimIndent()
    }
}