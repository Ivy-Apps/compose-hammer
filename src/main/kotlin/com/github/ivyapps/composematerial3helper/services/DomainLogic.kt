package com.github.ivyapps.composematerial3helper.services

fun importsCode(imports: List<String>): String? {
    if (imports.isEmpty()) return null
    return imports.joinToString(separator = "\n") { "import $it" }
}