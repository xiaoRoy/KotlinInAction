package com.learn.kotlindoc.coroutines.basic

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis


fun main() {
//    defaultSequential()
    concurrentUsingAsync()
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


