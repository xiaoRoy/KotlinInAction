package com.learn.tutorial.coroutine.chap4

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.coroutines.suspendCoroutine

suspend fun <T> getValue(provider:() -> T): T {
    return suspendCoroutine<T> { continuation ->
        continuation.resumeWith(Result.runCatching {
            provider()
        })
    }
}

fun executeInBackground(action: suspend () -> Unit) {
    GlobalScope.launch {
        action()
    }
}

fun executeInMain(action: suspend () -> Unit) {
    GlobalScope.launch(context = Dispatchers.Main) {
        action()
    }
}
