package com.ivyapps.composehammer.util

import com.intellij.testFramework.fixtures.BasePlatformTestCase

infix fun String.shouldBe(expected: String) {
    BasePlatformTestCase.assertEquals(expected, this)
}