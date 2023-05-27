package com.github.ivyapps.composematerial3helper

import com.intellij.testFramework.TestDataPath
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import com.ivyapps.composematerial3helper.domain.formatText

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
        val input = "IliyanGermanov"

        // when
        val output = input.formatText(maxLineLength = 5)

        // then
        assertEquals("IliyanGermanov", output)
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
        assertTrue(output.split("\n").size == 5)
        assertTrue('>' !in output)
    }
}