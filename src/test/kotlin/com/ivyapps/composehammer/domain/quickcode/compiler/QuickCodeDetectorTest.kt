package com.ivyapps.composehammer.domain.quickcode.compiler

import com.intellij.testFramework.TestDataPath
import com.intellij.testFramework.fixtures.BasePlatformTestCase

@TestDataPath("\$CONTENT_ROOT/src/test/testData")
class QuickCodeDetectorTest : BasePlatformTestCase() {

    private lateinit var service: QuickCodeDetector

    override fun setUp() {
        super.setUp()
        service = QuickCodeDetector()
    }

    fun testEmptyString() {
        // given
        val input = ""

        // when
        val output = service.containsSpecialSyntax(input)

        // then
        assertFalse(output)
    }

    fun testStringWithNoSpecialSyntax() {
        // given
        val input = "This is a test string with no special syntax."

        // when
        val output = service.containsSpecialSyntax(input)

        // then
        assertFalse(output)
    }

    fun testStringWithVariableSyntax() {
        // given
        val input = "This string contains a {{variable}}."

        // when
        val output = service.containsSpecialSyntax(input)

        // then
        assertTrue(output)
    }

    fun testStringWithIfSyntax() {
        // given
        val input = "This string contains an #if {{condition}}."

        // when
        val output = service.containsSpecialSyntax(input)

        // then
        assertTrue(output)
    }

    fun testStringWithBothSyntaxes() {
        // given
        val input = "This string contains a {{variable}} and an #if {{condition}}."

        // when
        val output = service.containsSpecialSyntax(input)

        // then
        assertTrue(output)
    }
}
