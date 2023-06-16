package com.ivyapps.composehammer.toolwindow.component

import com.intellij.ui.dsl.builder.Row
import javax.swing.JButton

class DeleteButton {
    private var confirmation = ConfirmationStage.NotConfirmed

    fun ui(
        row: Row,
        notConfirmedLabel: String = "Delete",
        onDelete: () -> Unit,
    ): Unit = with(row) {
        var button: JButton? = null
        button(notConfirmedLabel) {
            when (confirmation) {
                ConfirmationStage.NotConfirmed -> {
                    button?.text = "Confirm, deletion?"
                    confirmation = ConfirmationStage.ConfirmedOnce
                }

                ConfirmationStage.ConfirmedOnce -> {
                    button?.text = "[DANGER] DELETE PERMANENTLY!"
                    confirmation = ConfirmationStage.ConfirmedTwice
                }

                ConfirmationStage.ConfirmedTwice -> {
                    button?.text = notConfirmedLabel
                    confirmation = ConfirmationStage.NotConfirmed
                    onDelete()
                }
            }
        }.also {
            button = it.component
        }
    }
}


enum class ConfirmationStage {
    NotConfirmed,
    ConfirmedOnce,
    ConfirmedTwice,
}