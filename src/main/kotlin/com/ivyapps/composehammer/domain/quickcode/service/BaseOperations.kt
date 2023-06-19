package com.ivyapps.composehammer.domain.quickcode.service

import com.ivyapps.composehammer.domain.data.Either
import com.ivyapps.composehammer.domain.data.Reorderable
import com.ivyapps.composehammer.randomBetween
import com.ivyapps.composehammer.sortedByOrder


abstract class BaseOperations<Input, T : Reorderable> {
    protected abstract val items: List<T>

    protected abstract fun createItem(
        input: Input,
        order: Double,
        existingItem: T?,
    ): MaybeValid<T>

    protected abstract fun copyWithNewOrder(item: T, newOrder: Double): T

    protected abstract fun updateState(updatedItems: List<T>)

    fun addItem(input: Input): AddOperationResult<T> {
        val newItem = when (val maybeValid = createItem(input, items.findNextOrderNum(), null)) {
            is MaybeValid.Invalid -> return AddOperationResult.Invalid(maybeValid.reason)
            is MaybeValid.Valid -> maybeValid.item
        }

        val existingItem = items.find { it.name == newItem.name }
        if (existingItem != null) {
            // item already exists, don't add it!
            return AddOperationResult.AlreadyExists(existingItem)
        }

        updateStateInternal(updatedItems = items + newItem)
        return AddOperationResult.Added(newItem)
    }

    fun editItem(
        item: T,
        input: Input,
    ): EditOperationResult<T> {
        val updatedItem = when (val maybeValid = createItem(input, item.order, item)) {
            is MaybeValid.Invalid -> return EditOperationResult.Invalid(maybeValid.reason)
            is MaybeValid.Valid -> maybeValid.item
        }
        updateStateInternal(
            updatedItems = items.withRemoved(item) + updatedItem
        )
        return EditOperationResult.Updated(updatedItem)
    }

    fun moveItemUp(
        item: T
    ): MoveOperationResult<T> {
        val newOrder = items.moveUp(item)
            ?: return MoveOperationResult.Failed("Failed to move up: $item")
        return MoveOperationResult.Moved(
            moveItem(item, newOrder)
        )
    }

    fun moveItemDown(item: T): MoveOperationResult<T> {
        val newOrder = items.moveDown(item)
            ?: return MoveOperationResult.Failed("Failed to move down: $item")
        return MoveOperationResult.Moved(
            moveItem(item, newOrder)
        )
    }

    private fun moveItem(
        item: T,
        newOrder: Double
    ): T {
        val updatedItem = copyWithNewOrder(item, newOrder)
        updateStateInternal(
            updatedItems = items.withRemoved(item) + updatedItem
        )
        return updatedItem
    }

    fun deleteItem(item: T): Either.Right<T?> {
        updateStateInternal(
            updatedItems = items.withRemoved(item)
        )
        return Either.Right(null)
    }

    private fun <T : Reorderable> List<T>.moveUp(target: Reorderable): Double? {
        for ((index, group) in this.withIndex()) {
            if (target.name == group.name) {
                val prev = getOrNull(index - 1)
                    ?: return null // it's already first!
                val prevPrev = getOrNull(index - 2)
                return randomBetween(
                    min = prevPrev?.order ?: prev.order.minus(2),
                    max = prev.order
                )
            }
        }
        return null
    }

    private fun <T : Reorderable> List<T>.moveDown(target: Reorderable): Double? {
        for ((index, item) in this.withIndex()) {
            if (target.name == item.name) {
                val next = getOrNull(index + 1)
                    ?: return null // it's already last!
                val nextNext = getOrNull(index + 2)
                return randomBetween(
                    min = next.order,
                    max = nextNext?.order ?: next.order.plus(2)
                )
            }
        }
        return null
    }

    private fun updateStateInternal(
        updatedItems: List<T>
    ) {
        updateState(
            updatedItems.sortedByOrder()
                .ensureUniqueOrder(::copyWithNewOrder)
        )
    }

    private fun <T : Reorderable> List<T>.findNextOrderNum(): Double =
        lastOrNull()?.order?.plus(2) ?: 1.0

    private fun <T : Reorderable> List<T>.ensureUniqueOrder(
        constructor: (item: T, order: Double) -> T
    ) = mapIndexed { index, item ->
        // ensure that items have unique order double
        constructor(item, index.toDouble())
    }
}

sealed interface MaybeValid<out T> {
    data class Valid<T>(val item: T) : MaybeValid<T>
    data class Invalid(val reason: String) : MaybeValid<Nothing>
}

sealed interface AddOperationResult<out T> {
    data class AlreadyExists<T>(val item: T) : AddOperationResult<T>
    data class Added<T>(val item: T) : AddOperationResult<T>
    data class Invalid<T>(val reason: String) : AddOperationResult<T>
}

sealed interface EditOperationResult<out T> {
    data class Updated<T>(val item: T) : EditOperationResult<T>
    data class Invalid<T>(val reason: String) : EditOperationResult<T>
}

sealed interface MoveOperationResult<out T> {
    data class Moved<T>(val item: T) : MoveOperationResult<T>
    data class Failed<T>(val errMsg: String) : MoveOperationResult<T>
}

fun <T : Reorderable> List<T>.withRemoved(item: T) = filter { it.name != item.name }

fun <T> AddOperationResult<T>.toResultEither(): Either<String, T> = when (this) {
    is AddOperationResult.Added -> Either.Right(item)
    is AddOperationResult.AlreadyExists -> Either.Left("Already exists!")
    is AddOperationResult.Invalid -> Either.Left("Invalid: $reason")
}

fun <T> EditOperationResult<T>.toResultEither(): Either<String, T> = when (this) {
    is EditOperationResult.Invalid -> Either.Left("Invalid: $reason")
    is EditOperationResult.Updated -> Either.Right(item)
}

fun <T> MoveOperationResult<T>.toResultEither(): Either<String, T> = when (this) {
    is MoveOperationResult.Failed -> Either.Left("Move failed: $errMsg")
    is MoveOperationResult.Moved -> Either.Right(item)
}

fun Boolean.toResultEither(): Either<String, Unit> = when (this) {
    true -> Either.Right(Unit)
    false -> Either.Left("Operation failed")
}