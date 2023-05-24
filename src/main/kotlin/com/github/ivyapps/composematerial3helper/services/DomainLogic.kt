package com.github.ivyapps.composematerial3helper.services

fun importsCode(imports: List<String>): String? {
    if (imports.isEmpty()) return null
    return imports.joinToString(separator = "\n") { "import $it" }
}

fun String.formatText(maxLineLength: Int = 35): String {
    val words = this.filter { it == ' ' || !it.isWhitespace() }.split(" ")

    return buildString {
        var lineLength = 0
        for (word in words) {
            append(word)
            lineLength += word.length
            if (lineLength > maxLineLength) {
                append('\n')
                lineLength = 0
            } else {
                append(' ')
            }
        }
    }.trim()
}