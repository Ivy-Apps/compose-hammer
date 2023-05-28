package com.ivyapps.composehammer.util

import java.awt.Component
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import java.awt.event.MouseEvent
import java.awt.event.MouseListener

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