package com.learn.kotlindoc.coroutines.exceptions

import kotlinx.coroutines.*
import java.lang.ArithmeticException
import java.lang.IllegalArgumentException


fun main() {
//    learnExceptionPropagation()
//    learnExceptionHandler()
    what()
}

/*
* Exception propagation
* */

private fun learnExceptionPropagation() {
    runBlocking {
        val job = GlobalScope.launch {
            println("Throwing Exception from launch")
            throw IndexOutOfBoundsException()
        }

        job.join()
        val deferred = GlobalScope.async<Int> {
            println("Throwing Exception from async")
            throw ArithmeticException()
        }

        try {
            deferred.await()
            println("Unreached")
        }catch (exception: ArithmeticException) {
            println("Caught ArithmeticException")
        }
    }
}
/*
* CoroutineExceptionHandler
* */
private fun learnExceptionHandler() = runBlocking {
    val handler = CoroutineExceptionHandler { _, exception ->
        println("CoroutineExceptionHandler got $exception")
    }

    val job = GlobalScope.launch(handler) {
        throw IndexOutOfBoundsException()
    }

    val deferred: Deferred<Unit> = GlobalScope.async(handler) {
        throw IllegalArgumentException()
    }

    joinAll(deferred, job)
}

/*
* Cancellation and exception
* */




private fun what() = runBlocking {
    val one: Deferred<Int> = async {
        delay(3000)
        println("running...")
        1
    }

    val two: Deferred<Int> = async {
        delay(1000L)
        throw IndexOutOfBoundsException()
    }

    val result = one.await() + two.await()
}
