package com.github.ivyapps.composematerial3helper

import com.intellij.testFramework.TestDataPath
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import com.ivyapps.composehammer.domain.core.findImportsOffset

@TestDataPath("\$CONTENT_ROOT/src/test/testData")
class ImportsServiceTest : BasePlatformTestCase() {
    fun testOffset_package() {
        // given
        val file = """
            package com.example.composehamemrdemo1.ui.demo

            import androidx.compose.material3.Text
            import androidx.compose.material3.Icon
            import androidx.compose.material3.BadgedBox
            import androidx.compose.material3.Badge
        """.trimIndent()

        // when
        val offset = file.findImportsOffset()

        // then
        val expectedOffset = "package com.example.composehamemrdemo1.ui.demo".length + 1
        assertEquals(expectedOffset, offset)
    }

    fun testOffset_fileOptIn() {
        // given
        val file = """
            @file:OptIn(ExperimentalMaterial3Api::class)

            package com.example.composehamemrdemo1.ui.demo

            import androidx.compose.material3.Text
            import androidx.compose.material3.Icon
            import androidx.compose.material3.BadgedBox
            import androidx.compose.material3.Badge
        """.trimIndent()

        // when
        val offset = file.findImportsOffset()

        // then
        val expectedOffset = """
            @file:OptIn(ExperimentalMaterial3Api::class)

            package com.example.composehamemrdemo1.ui.demo
        """.trimIndent().length + 1
        assertEquals(expectedOffset, offset)
    }
}