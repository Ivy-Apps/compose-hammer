package com.ivyapps.composehammer.domain.quickcode.compiler

class QuickCodeDetector {
    fun detectQuickCode(template: String): Boolean {
        val specialSyntaxPattern = """\{\{.+?}}|#if \{\{.+?}}""".toRegex()
        return specialSyntaxPattern.containsMatchIn(template)
    }

}