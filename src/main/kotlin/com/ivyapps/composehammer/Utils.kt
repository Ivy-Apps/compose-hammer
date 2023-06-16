package com.ivyapps.composehammer

import java.awt.Component
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import kotlin.random.Random

fun copyToClipboard(content: String) {
    val selection = StringSelection(content)
    val clipboard = Toolkit.getDefaultToolkit().systemClipboard
    clipboard.setContents(selection, selection)
}

fun Component.addOnClickListener(onClick: () -> Unit) {
    addMouseListener(object : MouseListener {
        override fun mouseClicked(p0: MouseEvent?) {
            onClick()
        }

        override fun mousePressed(p0: MouseEvent?) {}

        override fun mouseReleased(p0: MouseEvent?) {}

        override fun mouseEntered(p0: MouseEvent?) {}

        override fun mouseExited(p0: MouseEvent?) {}
    })
}

fun randomBetween(min: Double, max: Double): Double {
    require(min <= max) { "Max must be greater than min" }
    return min + (Random.nextDouble() * (max - min))
}