package com.ivyapps.composehammer.toolwindow.screen.quickcode.component

import com.intellij.ui.dsl.builder.Panel
import com.ivyapps.composehammer.domain.data.Reorderable
import com.ivyapps.composehammer.toolwindow.component.DeleteButton

fun <T : Reorderable> Panel.itemControls(
    index: Int,
    item: T,
    itemsCount: Int,
    label: String,
    onNavigateToItemDetails: (T) -> Unit,
    onMoveUp: (T) -> Unit,
    onMoveDown: (T) -> Unit,
    onDelete: ((T) -> Unit)? = null,
) {
    row {
        button("Rename") {
            onNavigateToItemDetails(item)
        }
        if (index > 0) {
            button("Move up") {
                onMoveUp(item)
            }
        }
        if (index < itemsCount - 1) {
            button("Move down") {
                onMoveDown(item)
            }
        }
        if (onDelete != null) {
            DeleteButton().ui(
                row = this,
                notConfirmedLabel = "Delete \"${item.name}\" $label"
            ) {
                onDelete(item)
            }
        }
    }
}
