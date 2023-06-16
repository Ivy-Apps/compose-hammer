package com.ivyapps.composehammer.domain.ui

fun generateImportsCode(imports: List<String>): String? {
    if (imports.isEmpty()) return null
    return imports.joinToString(separator = "\n") { "import $it" }
}