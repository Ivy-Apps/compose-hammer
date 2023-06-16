package com.ivyapps.composehammer.toolwindow.component

import com.intellij.ui.dsl.builder.Row
import javax.swing.JButton


fun Row.deleteButton(
    notConfirmedLabel: String = "Delete",
    confirmation: ConfirmationStage = ConfirmationStage.NotConfirmed,
    onDelete: () -> Unit,
) {
    var button: JButton? = null
    when (confirmation) {
        ConfirmationStage.NotConfirmed -> {
            button = button(notConfirmedLabel) {
                button?.isVisible = false
                deleteButton(
                    confirmation = ConfirmationStage.ConfirmedOnce,
                    onDelete = onDelete
                )
            }.component
        }

        ConfirmationStage.ConfirmedOnce -> {
            button("Confirm, deletion?") {
                button?.isVisible = false
                deleteButton(
                    confirmation = ConfirmationStage.ConfirmedTwice,
                    onDelete = onDelete
                )
            }.also {
                button = it.component
                button?.isVisible = true
            }.comment("This operation can NOT be reverted.")
        }

        ConfirmationStage.ConfirmedTwice -> {
            button("[DANGER] DELETE PERMANENTLY!") {
                button?.isVisible = false
                onDelete()
            }.also {
                button = it.component
                button?.isVisible = true
            }.comment("The item will be permanently deleted!")
        }
    }
}


enum class ConfirmationStage {
    NotConfirmed,
    ConfirmedOnce,
    ConfirmedTwice,
}