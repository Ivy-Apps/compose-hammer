package com.ivyapps.composehammer

import com.intellij.openapi.ui.Messages

fun showInfoToast(
    message: String,
    title: String = "Info",
) {
    Messages.showInfoMessage(message, title)
}

fun showErrorToast(
    message: String,
    title: String = "Error",
) {
    Messages.showErrorDialog(message, title)
}