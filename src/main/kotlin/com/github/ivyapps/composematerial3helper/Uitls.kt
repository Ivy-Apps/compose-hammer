package com.github.ivyapps.composematerial3helper

import com.github.ivyapps.composematerial3helper.ui.toolwindow.MaterialComponentsWindowFactory
import java.awt.Component
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import java.net.URL

fun copyToClipboard(content: String) {
    val selection = StringSelection(content)
    val clipboard = Toolkit.getDefaultToolkit().systemClipboard
    clipboard.setContents(selection, selection)
}


fun String.toImagePath(): URL {
    val fullPath = "/images/$this.png"
    val resource = MaterialComponentsWindowFactory::class.java.getResource(fullPath)
    requireNotNull(resource) {
        "Couldn't find an image for '$fullPath'"
    }
    return resource
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