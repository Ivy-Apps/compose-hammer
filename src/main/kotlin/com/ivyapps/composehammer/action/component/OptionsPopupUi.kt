package com.ivyapps.composehammer.action.component

import com.intellij.openapi.editor.Editor
import com.intellij.openapi.ui.popup.JBPopup
import com.intellij.openapi.ui.popup.JBPopupFactory

fun Editor.showOptionsPopup(
    title: String,
    backItem: String,
    backItemLast: Boolean,
    items: List<String>,
    onBack: (JBPopup) -> Unit,
    onChosen: (String) -> Unit
) {
    var popup: JBPopup? = null
    popup = JBPopupFactory.getInstance()
        .createPopupChooserBuilder(
            if (backItemLast) {
                items + backItem
            } else {
                listOf(backItem) + items
            }
        )
        .setTitle(title)
        .setItemChosenCallback {
            if (it != backItem) {
                onChosen(it)
            } else {
                onBack(popup!!)
            }
        }
        .createPopup()
    popup.showInBestPositionFor(this)
}
