package com.learn.kotlindoc.coroutines.basic

import kotlinx.coroutines.*
import java.lang.IllegalArgumentException
import kotlin.system.measureTimeMillis


fun main() {
//    defaultSequential()
//    concurrentUsingAsync()
//    lazilyStartedAsync()
//    asyncStyle()
//    asyncStyleWithError()
//    sumWithStructured()
//    failedConcurrentSum()
//    failedConcurrentSumNoScopeBuilder()
    stillRunning()
}


private suspend fun getToken(): String {
    delay(1000L)
    return "token"
}

private suspend fun getUserInfo(token: String): String {
    delay(1000L)
    return "Smith Black"
}

private fun defaultSequential() {
    runBlocking {
        val scope = this
        val result = measureTimeMillis {
            val token = getToken()
            val user = getUserInfo(token)
            println("The user is $user.")
        }
        println("Completed in $result")
    }
}

private suspend fun getFirstCount(): Int {
    delay(1000L)
    return 12
}

private suspend fun getSecondCount(): Int {
    delay(1000L)
    return 4
}

private fun concurrentUsingAsync() {
    runBlocking {
        /*
        Notice thw await() call, it suspends the coroutine.
        So the completed time is at least 2000L
        val result = measureTimeMillis {
            val one = async { getFirstCount() }.await()
            val two = async { getSecondCount() }.await()
            println("The answer is ${one + two}")
        }*/

        val result = measureTimeMillis {
            val one = async { getFirstCount() }
            val two = async { getSecondCount() }
            println("The answer is ${one.await() + two.await()}")
        }
        println("Completed in $result")
    }
}

private fun lazilyStartedAsync() {
    runBlocking {
        val time = measureTimeMillis {
            val one = async(start = CoroutineStart.LAZY) { getFirstCount() }
            val two = async(start = CoroutineStart.LAZY) { getSecondCount() }
            one.start()
            two.start()
            println("The answer is ${one.await() + two.await()}")
        }
        println("Completed in $time")
    }
}

private fun asyncStyle() {
    fun getOneAsync() = GlobalScope.async { getFirstCount() }
    fun getSecondAsync() = GlobalScope.async { getSecondCount() }

    val time = measureTimeMillis {
        val one = getOneAsync()
        val two = getSecondAsync()
        runBlocking {
            println("The answer is ${one.await() + two.await()}")
        }
    }
    println("Completed in $time")
}

private fun somethingWrongBetweenAsyncAndAwait(): Int {
    throw IllegalArgumentException()
}

private fun stillRunning() {
    try {
        asyncStyleWithError()
    } catch (exception: IllegalArgumentException) {
        println("Still running")
    }
    runBlocking {
        delay(10_000L)
    }
}

private fun asyncStyleWithError() {
    fun getOneAsync() = GlobalScope.async {
        delay(5000L)
        println("First one is still running!")
        12
    }

    fun getSecondAsync() = GlobalScope.async { getSecondCount() }

    val time = measureTimeMillis {
        val one = getOneAsync()
        somethingWrongBetweenAsyncAndAwait()
        val two = getSecondAsync()
        runBlocking {
            println("The answer is ${one.await() + two.await()}")
        }
    }
    println("Completed in $time")
}

private suspend fun concurrentSum(): Int = coroutineScope {
    val one = async { getFirstCount() }
    val two = async { getSecondCount() }
    one.await() + two.await()
}


private fun sumWithStructured() = runBlocking {
    val time = measureTimeMillis {
        println("The answer is ${concurrentSum()}")
    }
    println("Completed in $time")
}


private val getSumResultWithError: suspend CoroutineScope.() -> Int = {
    val scope = this
    val one = scope.async {
        try {
            delay(5000L)
            42
        } finally {
            println("First child was cancelled")
        }
    }
    val two = scope.async<Int> {
        println("Second child throws an exception")
        throw ArithmeticException()
    }
    one.await() + two.await()
}

private fun failedConcurrentSum() {
    runBlocking {
        try {
            coroutineScope(block = getSumResultWithError)
        } catch (exception: ArithmeticException) {
            println("Computation failed with ArithmeticException")
        }
    }
}

private fun failedConcurrentSumNoScopeBuilder() {
    runBlocking {
        try {
            val scope = this
            scope.getSumResultWithError()
        } catch (exception: ArithmeticException) {
            println("Computation failed with ArithmeticException")
        }
    }
}





