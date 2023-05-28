package com.ivyapps.composehammer.content

import com.ivyapps.composehammer.domain.ContentScope
import com.ivyapps.composehammer.domain.component
import com.ivyapps.composehammer.domain.group

fun ContentScope.bottomSheets() = group("Bottom sheets") {
    component {
        name = "Bottom sheets"
        specUrl = "https://m3.material.io/components/bottom-sheets/specs"
        guidelinesUrl = "https://m3.material.io/components/bottom-sheets/guidelines"
        docsUrl =
            "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#BottomSheetScaffold(kotlin.Function1,androidx.compose.ui.Modifier,androidx.compose.material3.BottomSheetScaffoldState,androidx.compose.ui.unit.Dp,androidx.compose.ui.graphics.Shape,androidx.compose.ui.graphics.Color,androidx.compose.ui.graphics.Color,androidx.compose.ui.unit.Dp,androidx.compose.ui.unit.Dp,kotlin.Function0,kotlin.Boolean,kotlin.Function0,kotlin.Function1,androidx.compose.ui.graphics.Color,androidx.compose.ui.graphics.Color,kotlin.Function1)"
        screenshot = "bottomsheet"
        description = """
            Bottom sheets display supplementary content and actions on a mobile screen.
            Bottom sheets are a versatile component that can contain a wide variety of information and layouts, 
            including menu items (in list or grid layouts), actions, and supplemental content.
        """.trimIndent()
        imports = listOf(
            "androidx.compose.foundation.layout.Arrangement",
            "androidx.compose.foundation.layout.Column",
            "androidx.compose.foundation.layout.Row",
            "androidx.compose.foundation.layout.Spacer",
            "androidx.compose.foundation.layout.fillMaxSize",
            "androidx.compose.foundation.layout.fillMaxWidth",
            "androidx.compose.foundation.layout.height",
            "androidx.compose.foundation.layout.padding",
            "androidx.compose.foundation.shape.RoundedCornerShape",
            "androidx.compose.material3.BottomSheetScaffold",
            "androidx.compose.material3.ExperimentalMaterial3Api",
            "androidx.compose.material3.MaterialTheme",
            "androidx.compose.material3.Surface",
            "androidx.compose.material3.Text",
            "androidx.compose.material3.rememberBottomSheetScaffoldState",
            "androidx.compose.runtime.Composable",
            "androidx.compose.ui.Alignment",
            "androidx.compose.ui.Modifier",
            "androidx.compose.ui.unit.dp",
        )
        code = """
            val scaffoldState = rememberBottomSheetScaffoldState()
            /* Use this to control the BottomSheet's visibility
            for example in Button(onClick = {}) or LaunchedEffect{}
            val scope = rememberCoroutineScope()
            scope.launch {
                scaffoldState.bottomSheetState.show()
                scaffoldState.bottomSheetState.hide()
                scaffoldState.bottomSheetState.expand()
                scaffoldState.bottomSheetState.partialExpand()
            }
             */
    
            BottomSheetScaffold(
                scaffoldState = scaffoldState,
                sheetPeekHeight = 120.dp, // BottomSheet's height when it's collapsed
                sheetShadowElevation = 12.dp,
                sheetContent = {
                    Text(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        text = "Sheet content"
                    )
                }) { innerPadding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Screen content")
                }
            }
        """.trimIndent()
        customCode = """
            val scaffoldState = rememberBottomSheetScaffoldState()
            /* Use this to control the BottomSheet's visibility
            for example in Button(onClick = {}) or LaunchedEffect{}
            val scope = rememberCoroutineScope()
            scope.launch {
                scaffoldState.bottomSheetState.show()
                scaffoldState.bottomSheetState.hide()
                scaffoldState.bottomSheetState.expand()
                scaffoldState.bottomSheetState.partialExpand()
            }
             */
    
            BottomSheetScaffold(
                modifier = Modifier,
                scaffoldState = scaffoldState,
                sheetPeekHeight = 80.dp, // BottomSheet's height when it's collapsed
                sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
                sheetContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                sheetContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                sheetShadowElevation = 4.dp,
                sheetDragHandle = null,
                sheetSwipeEnabled = true,
                topBar = {
                    Surface(
                        color = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary,
                        shadowElevation = 8.dp
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                        ) {
                            Text(text = "Topbar")
                        }
                    }
                },
                sheetContent = {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        text = "Sheet content"
                    )
                }) { innerPadding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Spacer(modifier = Modifier.height(32.dp))
                    Text("Screen content")
                }
            }
        """.trimIndent()
    }
}