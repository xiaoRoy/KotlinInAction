package com.learn.tutorial.coroutine.chap8

import kotlinx.coroutines.*


fun main() {
//    learnLaunchBuilderException()
    learnExceptionHandler()
}

private fun learnLaunchBuilderException() = runBlocking {
    val job = launch {
        println("1. Exception created vie launch coroutine.")
        throw IndexOutOfBoundsException()
    }

    job.invokeOnCompletion { exception ->
        println("2. Caught $exception")
    }

    job.join()
}

private fun learnExceptionHandler() {
    val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        println("Caught $exception")
    }
    runBlocking(exceptionHandler) {
        val scope = this
        val job = scope.launch {
            throw IllegalArgumentException()
        }
        val deferred = scope.async {
            throw IndexOutOfBoundsException()
        }
        joinAll(job, deferred)
    }
}
