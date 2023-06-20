package com.ivyapps.composehammer.domain.quickcode.compiler

import com.ivyapps.composehammer.domain.data.quickcode.QCVariable
import com.ivyapps.composehammer.domain.quickcode.compiler.QuickCodeCompiler.CompilationResult
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

    fun testExample1() = fileTestCase(
        caseName = "example1",
        vars = mapOf(
            "isViewModel" to QCVariableValue.Bool(true),
            "className" to QCVariableValue.Str("MyClass")
        )
    )

    fun testElseif1() = fileTestCase(
        caseName = "elseif1",
        vars = mapOf(
            "a" to QCVariableValue.Bool(false),
            "b" to QCVariableValue.Bool(true),
            "c" to QCVariableValue.Bool(true),
        )
    )

    fun testResolvesVariablesConflicts() {
        // given
        val code = """
            println("Hello, {{name}}!")
            #if {{name}} #then
            // {{name}}
            #endif
            // Bye, {{name}}!
        """.trimIndent()

        // when
        val result = compiler.compile(code)

        // res
        (result as CompilationResult.Valid).variables shouldBe listOf(
            QCVariable.Bool("name")
        )
    }

    private fun fileTestCase(
        caseName: String,
        vars: Map<String, QCVariableValue>
    ) {
        // given
        val template = loadFile("$caseName.txt")

        // when
        println(compiler.compile(template))
        val result = compiler.execute(template, vars)

        // then
        result shouldBe loadFile("${caseName}_expected.txt")
    }
}