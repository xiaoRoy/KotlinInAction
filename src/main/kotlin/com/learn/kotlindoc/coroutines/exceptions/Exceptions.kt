package com.learn.kotlindoc.coroutines.exceptions

import com.learn.tutorial.coroutine.showCurrentThreadName
import kotlinx.coroutines.*

fun main() {

//    demonstrateExceptionFromLaunch()
    demonstrateAsyncException()
}


private fun demonstrateExceptionFromLaunch() = runBlocking {
    val job = GlobalScope.launch {
        showCurrentThreadName()
        Thread.currentThread().uncaughtExceptionHandler =
                Thread.UncaughtExceptionHandler { thread, excepiton ->
                    println(thread.name)
                    println(excepiton)
                }
        println("Throwing exception from launch")
        throw IndexOutOfBoundsException()
    }

    job.join()
}

private fun demonstrateAsyncException() = runBlocking {
    val one: Deferred<Int> = async {
        delay(10000)
        throw IndexOutOfBoundsException()
    }
    try {
        val result = one.await()
    } catch (indexOutOfBoundsException: IndexOutOfBoundsException) {
        println("can not catch")
    }
}

private suspend fun doSomething(scope: CoroutineScope) {
    val one: Deferred<Int> = scope.async {
        delay(10000)
        throw IndexOutOfBoundsException()
    }
    try {
        one.await()
    } catch (indexOutOfBoundsException: IndexOutOfBoundsException) {
        println("can not catch")
    }
}
