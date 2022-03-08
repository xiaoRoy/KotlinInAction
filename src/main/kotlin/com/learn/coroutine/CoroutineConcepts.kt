package com.learn.coroutine

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/*
* Programming Android with Kotlin Achieving Structured Concurrency with Coroutines
* Chapter 7 Coroutines Concepts
* */


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
