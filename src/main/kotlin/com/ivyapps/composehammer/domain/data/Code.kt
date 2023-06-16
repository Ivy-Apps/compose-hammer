package com.ivyapps.composehammer.domain.data

interface Code {
    val menuName: String
    val imports: List<String>
    val code: String
}