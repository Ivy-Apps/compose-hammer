package com.ivyapps.composehammer.util

import com.intellij.testFramework.fixtures.BasePlatformTestCase

infix fun <T> T.shouldBe(expected: T) {
    BasePlatformTestCase.assertEquals(expected, this)
}