package com.ivyapps.composehammer.domain.quickcode.compiler

import com.ivyapps.composehammer.domain.quickcode.compiler.data.QCVariableValue
import com.ivyapps.composehammer.util.BaseFileTest
import com.ivyapps.composehammer.util.shouldBe

class QuickCodeCompilerTest : BaseFileTest() {

    override val folderName = "compiler"

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
}