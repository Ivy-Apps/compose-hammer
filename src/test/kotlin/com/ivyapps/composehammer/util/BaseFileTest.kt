package com.ivyapps.composehammer.util

import com.intellij.testFramework.fixtures.BasePlatformTestCase
import java.io.File

abstract class BaseFileTest : BasePlatformTestCase() {
    abstract val folderName: String

    protected fun loadFile(fileName: String): String {
        return File("$testDataPath/$fileName").readText()
    }

    override fun getTestDataPath() = "src/test/testData/$folderName"
}