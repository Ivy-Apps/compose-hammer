package com.github.ivyapps.composematerial3helper.ui.common

import com.github.ivyapps.composematerial3helper.addOnClickListener
import com.github.ivyapps.composematerial3helper.ui.toolwindow.MaterialComponentsWindowFactory
import com.intellij.ui.dsl.builder.Panel
import java.net.URL
import javax.swing.ImageIcon
import javax.swing.JLabel

fun Panel.image(
    imageFileName: String,
    onClick: (() -> Unit)? = null,
) {
    row(
        label = JLabel(ImageIcon(imageFileName.toImagePath())).apply {
            if (onClick != null) {
                addOnClickListener(onClick = onClick)
            }
        }
    ) {}
}

fun String.toImagePath(): URL {
    val fullPath = "/images/$this.png"
    val resource = MaterialComponentsWindowFactory::class.java.getResource(fullPath)
    requireNotNull(resource) {
        "Couldn't find an image for '$fullPath'"
    }
    return resource
}