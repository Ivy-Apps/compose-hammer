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
        folder = "sample",
        case = "1",
        vars = mapOf(
            "isViewModel" to QCVariableValue.Bool(true),
            "className" to QCVariableValue.Str("MyClass")
        )
    )

    fun testIf0() = fileTestCase(
        folder = "if",
        case = "0",
        vars = mapOf(
            "a" to QCVariableValue.Bool(true),
        )
    )

    fun testIf1() = fileTestCase(
        folder = "if",
        case = "1",
        vars = mapOf(
            "a" to QCVariableValue.Bool(false),
        )
    )

    fun testElseif0() = fileTestCase(
        folder = "elseif",
        case = "0",
        vars = mapOf(
            "a" to QCVariableValue.Bool(true),
            "b" to QCVariableValue.Bool(true),
        )
    )

    fun testElseif1() = fileTestCase(
        folder = "elseif",
        case = "1",
        vars = mapOf(
            "a" to QCVariableValue.Bool(false),
            "b" to QCVariableValue.Bool(true),
        )
    )

    fun testElseif2() = fileTestCase(
        folder = "elseif",
        case = "2",
        vars = mapOf(
            "a" to QCVariableValue.Bool(false),
            "b" to QCVariableValue.Bool(false),
        )
    )

    fun testStackedIfs1() = fileTestCase(
        folder = "stacked_ifs",
        case = "1",
        vars = mapOf(
            "a" to QCVariableValue.Bool(true),
            "b" to QCVariableValue.Bool(true),
        )
    )

    fun testStackedIfs2() = fileTestCase(
        folder = "stacked_ifs",
        case = "2",
        vars = mapOf(
            "a" to QCVariableValue.Bool(false),
            "b" to QCVariableValue.Bool(true),
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
        folder: String,
        case: String,
        vars: Map<String, QCVariableValue>
    ) {
        // given
        val template = loadFile("$folder/$case.txt")

        // when
        println(compiler.compile(template))
        val result = compiler.execute(template, vars)

        // then
        result shouldBe loadFile("$folder/${case}_expected.txt")
    }
}