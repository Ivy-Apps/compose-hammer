package com.ivyapps.composematerial3helper.domain

fun String.formatText(maxLineLength: Int = 35): String {
    val words = this.replace("\n", " ")
        .split(" ")
        .filter { it.isNotBlank() }

    return buildString {
        var lineLength = 0
        for (word in words) {
            if (word.firstOrNull() in listOf('-', '+', '>')) {
                append('\n')
                lineLength = 0
            }
            append(word.run {
                if (firstOrNull() == '>' && length > 1) drop(1) else this
            })
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