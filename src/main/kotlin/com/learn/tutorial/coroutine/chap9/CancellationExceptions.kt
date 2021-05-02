package com.learn.tutorial.coroutine.chap9

import kotlinx.coroutines.*
import java.io.IOException
import java.lang.ArithmeticException
import java.lang.IllegalArgumentException


fun main() {
//    learnCancellationException()
//    throwExceptionFromAsync()
    throwExceptionFromLaunch()
}


private fun learnCancellationException() = runBlocking {
    val handler = CoroutineExceptionHandler { _, exception ->
        println("Caught original $exception")
    }
    val parentJob = GlobalScope.launch (handler) {
        val scope = this
        //second generation
        val secondGenerationJob = scope.launch {
            //third generation
            launch {
                throw IOException()
            }
        }

        try {
            secondGenerationJob.join()
        } catch (exception: CancellationException) {
            println("Rethrowing CancellationException with original cause.")
            throw exception
        }
    }
    parentJob.join()
}

private fun throwExceptionFromAsync() = runBlocking {
    val deferred: Deferred<Int> = GlobalScope.async {
        throw IllegalArgumentException()
    }

    deferred.await()
}

private fun throwExceptionFromLaunch() {
    println(Thread.getDefaultUncaughtExceptionHandler())
    runBlocking {
        val job =GlobalScope.launch {
            throw ArithmeticException()
        }
        job.join()
    }
}
