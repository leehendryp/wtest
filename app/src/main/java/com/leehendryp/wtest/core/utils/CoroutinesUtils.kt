package com.leehendryp.wtest.core.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun <T> coTryCatch(
    onTry: suspend () -> T
): T {
    return try {
        onTry()
    } catch (cause: Throwable) {
        throw cause
    }
}

suspend fun <T> coTryCatch(
    dispatcher: CoroutineDispatcher,
    onTry: suspend () -> T,
    onCatch: (Throwable) -> Throwable
): T {
    return try {
        withContext(dispatcher) { onTry() }
    } catch (cause: Throwable) {
        throw onCatch(cause)
    }
}

fun io(): CoroutineDispatcher = Dispatchers.IO