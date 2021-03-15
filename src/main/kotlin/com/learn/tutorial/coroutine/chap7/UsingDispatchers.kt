package com.learn.tutorial.coroutine.chap7

import com.learn.tutorial.coroutine.showCurrentThreadName
import kotlinx.coroutines.*
import java.util.concurrent.Executors
import kotlin.coroutines.CoroutineContext

fun main() {
//    learnDispatcher(Dispatchers.Unconfined)
//    learnDispatcher(Dispatchers.IO)
//    learnDispatcher()
    learnUsingExecutor()
}

private fun learnDispatcher(
        coroutineContext: CoroutineContext = Dispatchers.Default
) = runBlocking {
    val scope = this
    scope.launch(context = coroutineContext) {
        showCurrentThreadName()
    }
//    delay(100L)
}

private fun learnUsingExecutor() {
    val executorDispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher()
    learnDispatcher(executorDispatcher)
}


