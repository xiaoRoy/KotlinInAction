package com.learn.coroutine

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/*
* Programming Android with Kotlin Achieving Structured Concurrency with Coroutines
*   Chapter 7 Coroutines Concepts
*       CoroutineScope and CoroutineContext
* */


fun main() {
//    learnDispatcher()
    learnContext()
}

private fun learnDispatcher() = runBlocking {
    val scope = this
    scope.launch(context = Dispatchers.IO) {
        delay(3000)
        println("Message#1")
    }
    println("Message#0")
}

private fun learnContext() {
    runBlocking(Dispatchers.Default) {
        showCurrentThreadInfo("Parent")
        val scope = this
        scope.launch(Dispatchers.IO) {
            showCurrentThreadInfo("Child")
        }
        delay(5000L)
    }
}
