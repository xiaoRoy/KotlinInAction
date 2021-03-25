package com.learn.tutorial.coroutine.chap8

import kotlinx.coroutines.*

fun main() {
//    handleExceptionInAsync()
    handleMultipleException()
}

private fun handleExceptionInAsync() = runBlocking {
    val scope = this
    val deferred = GlobalScope.async<Int> {
        println("Throwing exception from async")
        throw IllegalArgumentException()
    }

    try {
//        deferred.await()
    } catch (exception: IllegalArgumentException) {
        println("Catch $exception")
    }
}

private fun handleMultipleException() = runBlocking {
    val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        val message = "Caught $exception with suppressed ${exception.suppressed?.contentToString()}"
        println(message)
    }

    val parentJob = GlobalScope.launch(context = coroutineExceptionHandler) {
        val parentScope = this

        //first child job
        val firstChild = parentScope.launch {
            try {
                println("first: I am running normally.")
                delay(1000 * 12)
            } catch (exception: Exception) {
                println("first: Catch the exception caused by second child of the parent.")
                println("$exception caught in first child job")
            } finally {
                throw IndexOutOfBoundsException("throw by finally")
            }
        }
//        firstChild.invokeOnCompletion { exception ->
//            println("first: caught $exception")
//        }

        //second child job
        parentScope.launch {
            delay(500)
            emptyList<Int>().first()
        }
        delay(1000 * 20)
    }
    parentJob.join()
}
