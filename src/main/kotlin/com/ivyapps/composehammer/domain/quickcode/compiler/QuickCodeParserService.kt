package com.ivyapps.composehammer.domain.quickcode.compiler

import com.intellij.openapi.components.Service

@Service(Service.Level.PROJECT)
class QuickCodeParserService {
    fun containsSpecialSyntax(template: String): Boolean {
        val specialSyntaxPattern = """\{\{.+?}}|#if \{\{.+?}}""".toRegex()
        return specialSyntaxPattern.containsMatchIn(template)
    }

}