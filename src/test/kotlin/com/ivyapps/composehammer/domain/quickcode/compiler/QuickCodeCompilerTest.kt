package com.ivyapps.composehammer.domain.quickcode.compiler

import com.intellij.testFramework.TestDataPath
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import com.ivyapps.composehammer.domain.quickcode.compiler.data.QCVariableValue
import com.ivyapps.composehammer.shouldBe
import java.io.File

@TestDataPath("\$CONTENT_ROOT/src/test/testData/compiler")
class QuickCodeCompilerTest : BasePlatformTestCase() {

    private lateinit var compiler: QuickCodeCompiler

    override fun setUp() {
        super.setUp()
        compiler = QuickCodeCompiler()
    }

    fun testExample1() {
        // given
        val template = loadFile("example1.txt")
        val vars = mapOf(
            "isViewModel" to QCVariableValue.Bool(true),
            "className" to QCVariableValue.Str("MyClass")
        )

        // when
        val result = compiler.execute(template, vars)

        // then
        result shouldBe loadFile("example1_expected.txt")
    }

    private fun loadFile(fileName: String): String {
        return File("$testDataPath/$fileName").readText()
    }

    override fun getTestDataPath() = "src/test/testData/compiler"
}