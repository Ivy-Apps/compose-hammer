package com.ivyapps.composematerial3helper.util

import com.ivyapps.composematerial3helper.toolwindow.MaterialComponentsWindowFactory
import com.intellij.ui.components.JBLabel
import com.intellij.ui.dsl.builder.Panel
import com.intellij.ui.dsl.builder.RowLayout
import java.net.URL
import javax.swing.ImageIcon

fun Panel.image(
    imageFileName: String,
    onClick: (() -> Unit)? = null,
) {
    row(
        label = JBLabel(ImageIcon(imageFileName.toImagePath())).apply {
            if (onClick != null) {
                addOnClickListener(onClick = onClick)
            }

        }
    ) {}.layout(RowLayout.PARENT_GRID)
}

fun String.toImagePath(): URL {
    val fullPath = "/images/$this.png"
    val resource = MaterialComponentsWindowFactory::class.java.getResource(fullPath)
    requireNotNull(resource) {
        "Couldn't find an image for '$fullPath'"
    }
    return resource
}