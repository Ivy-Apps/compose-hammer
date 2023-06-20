package com.ivyapps.composehammer

import com.intellij.testFramework.fixtures.BasePlatformTestCase

infix fun String.shouldBe(expected: String) {
    BasePlatformTestCase.assertEquals(expected, this)
}