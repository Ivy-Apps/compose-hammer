package com.ivyapps.composematerial3helper.domain

fun generateImportsCode(imports: List<String>): String? {
    if (imports.isEmpty()) return null
    return imports.joinToString(separator = "\n") { "import $it" }
}