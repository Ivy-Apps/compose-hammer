package com.ivyapps.composehammer

import com.intellij.testFramework.TestDataPath
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import com.ivyapps.composehammer.domain.ui.formatText

@TestDataPath("\$CONTENT_ROOT/src/test/testData")
class DescriptionFormatterTest : BasePlatformTestCase() {

    fun testEmptyString() {
        // given
        val input = ""

        // when
        val output = input.formatText()

        // then
        assertEquals("", output)
    }

    fun testBlankString() {
        // given
        val input = "    "

        // when
        val output = input.formatText()

        // then
        assertEquals("", output)
    }

    fun testOneWordString() {
        // given
        val input = "okay"

        // when
        val output = input.formatText()

        // then
        assertEquals("okay", output)
    }

    fun testOneWordAboveMaxChars() {
        // given
        val input = "JohnDoe"

        // when
        val output = input.formatText(maxLineLength = 5)

        // then
        assertEquals("JohnDoe", output)
    }

    fun testFormatsBullets() {
        // given
        val input = """
            Use checkboxes to:
            - Select one or more options from a list
            - Present a list containing sub-selections
            - Turn an item on or off in a desktop environment
            >Checkboxes should be used instead of switches if multiple options can be selected from a list.
        """.trimIndent()

        // when
        val output = input.formatText(maxLineLength = 100)

        // then
        assertTrue(output.split("<br>").size == 5)
        assertTrue(output.split("<br>").all { it.firstOrNull() != '>' })
    }
}