package com.ivyapps.composehammer.domain.quickcode.compiler.parser

import com.ivyapps.composehammer.domain.quickcode.compiler.data.QuickCodeAst

sealed interface ParseResult {
    data class Success(
        val ast: QuickCodeAst
    ) : ParseResult

    data class Failure(
        val errorMsg: String
    ) : ParseResult
}