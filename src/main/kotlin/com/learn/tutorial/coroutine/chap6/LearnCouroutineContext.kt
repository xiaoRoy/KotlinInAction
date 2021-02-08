package com.learn.tutorial.coroutine.chap6

import com.learn.tutorial.coroutine.showCurrentThreadName
import kotlinx.coroutines.*

fun main() {
    firstContext()
}

private fun firstContext() {
    val defaultDispatcher = Dispatchers.Default
    val coroutineErrorHandler = CoroutineExceptionHandler { context, error ->
        println("Problems with coroutine: $error")
    }
    val emptyParentJob = Job()

    val firstContext = defaultDispatcher + coroutineErrorHandler + emptyParentJob

    GlobalScope.launch(context = firstContext) {
        throwError()
        showCurrentThreadName()
    }

    runBlocking {
        delay(100)
    }

}

private suspend fun throwError() {
    throw IllegalArgumentException()
}

