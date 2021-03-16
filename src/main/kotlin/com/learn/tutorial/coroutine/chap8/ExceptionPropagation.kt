package com.learn.tutorial.coroutine.chap8

import kotlinx.coroutines.*
import java.lang.ArithmeticException


fun main() {
    handleException()
}

private fun handleException() {
    runBlocking {
        val job = GlobalScope.launch {
            println("1. Exception created via launch coroutine")
            throw IndexOutOfBoundsException()
        }
        job.join()
        println("2. Joined failed job.")

        val deferred: Deferred<Unit> = GlobalScope.async {
            println("3. Exception created via async coroutine")
            throw ArithmeticException()
        }

        try {
            deferred.await()
            println("4. Unreachable, this statement is never executed")
        } catch (exception: Exception) {
            println("5. Caught ${exception.javaClass.name}")
        }
    }
}
