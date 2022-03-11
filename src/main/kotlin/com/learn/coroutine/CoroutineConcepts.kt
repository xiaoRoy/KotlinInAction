package com.learn.coroutine

import kotlinx.coroutines.*

/*
* Programming Android with Kotlin Achieving Structured Concurrency with Coroutines
* Chapter 7 Coroutines Concepts
* */


fun main() {
//    firstCoroutine()
    learnAsyncBuilder()
}

private fun firstCoroutine() {
    runBlocking {
        val scope = this
        val job = scope.launch {
            var index = 0
            while (true) {
                println("#$index is working.")
                index ++
                delay(10)
            }
        }
        delay(30)
        job.cancel()
    }
}

private fun firstCoroutineCantCancel() {
    runBlocking {
        val scope = this
        val job: Job = scope.launch {
            var index = 0
            while (true) {
                println("#$index is working.")
                index ++
                Thread.sleep(10)
            }
        }
        delay(30)
        job.cancel()
    }
}

private fun learnAsyncBuilder() = runBlocking {
    val scope = this
    val slow = scope.async {
        var result = 0
        delay(1000)
        repeat(1000) {
            result += it
        }
        println("Result is: $result")
        result
    }

    val quick = scope.async {
        delay(100)
        println("Call complete for quick: 4")
        5
    }
    val result = quick.await() + slow.await()
    println(result)
}
