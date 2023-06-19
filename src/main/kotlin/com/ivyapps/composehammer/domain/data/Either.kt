package com.ivyapps.composehammer.domain.data

sealed interface Either<out E, out A> {
    data class Left<E>(val error: E) : Either<E, Nothing>
    data class Right<A>(val value: A) : Either<Nothing, A>
}

fun <E, A, T> Either<E, A>.fold(
    mapLeft: (E) -> T,
    mapRight: (A) -> T
): T {
    return when (this) {
        is Either.Left -> mapLeft(error)
        is Either.Right -> mapRight(value)
    }
}

fun <E, A, A2> Either<E, A>.mapRight(
    mapRight: (A) -> A2
): Either<E, A2> {
    return when (this) {
        is Either.Left -> this
        is Either.Right -> Either.Right(mapRight(value))
    }
}

fun <E, A, E2> Either<E, A>.mapLeft(
    mapLeft: (E) -> E2
): Either<E2, A> {
    return when (this) {
        is Either.Left -> Either.Left(mapLeft(error))
        is Either.Right -> this
    }
}