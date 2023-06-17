package com.github.ivyapps.composematerial3helper.domain.quickCode.compiler

import com.intellij.testFramework.TestDataPath
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import com.ivyapps.composehammer.domain.quickcode.compiler.QuickCodeParserService

@TestDataPath("\$CONTENT_ROOT/src/test/testData")
class QuickCodeParserServiceTest : BasePlatformTestCase() {

    private lateinit var service: QuickCodeParserService

    override fun setUp() {
        super.setUp()
        service = QuickCodeParserService()
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
