package com.github.ivyapps.composehammer.domain.quickCode.compiler

import com.intellij.testFramework.TestDataPath
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import com.ivyapps.composehammer.domain.quickcode.compiler.QuickCodeParser

@TestDataPath("\$CONTENT_ROOT/src/test/testData")
class QuickCodeParserTest : BasePlatformTestCase() {

    private lateinit var parser: QuickCodeParser

    override fun setUp() {
        super.setUp()
        parser = QuickCodeParser()
    }


}