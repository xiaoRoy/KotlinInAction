package com.learn.kotlindoc.coroutines

import com.learn.tutorial.coroutine.showCurrentThreadName
import kotlinx.coroutines.*


fun main() {
//    showDispatchers()
//    learnUnconfinedDispatcher()
//    jumpingBetweenThreads()
//    notCancelled()
//    cancelled()
//    cancelledWithContext()
    nameCoroutineForDebugging()
}

@ObsoleteCoroutinesApi
private fun showDispatchers() = runBlocking {
    launch {
        showCurrentThreadName()
        println()
    }

    launch(context = Dispatchers.Unconfined) {
        println("Dispatchers.Unconfined")
        showCurrentThreadName()
        println()
    }

    launch(context = Dispatchers.Default) {
        println("Dispatchers.Default")
        showCurrentThreadName()
        println()
    }

    launch(context = newSingleThreadContext("My own thread")) {
        println("My own thread")
        showCurrentThreadName()
        println()
    }
}

private fun showThreadMessage(message: String) {
    println("$message ${Thread.currentThread().name}")
}

private fun learnUnconfinedDispatcher() = runBlocking {
    launch(context = Dispatchers.Unconfined) {
        showThreadMessage("Unconfined:Before first suspension in thread")
        delay(500L)
        showThreadMessage("Unconfined:After first suspension in thread")
    }

    launch {
        showThreadMessage("main runBlocking:Before first suspension in thread,")
        delay(1000L)
        showThreadMessage("main runBlocking:After first suspension in thread,")
    }
}

/*
* jumping between threads
* */

private fun log(message: String) {
    println("[${Thread.currentThread().name}] $message")
}

private fun jumpingBetweenThreads() {
    runBlocking() {
        log("runBlocking in main")
        val scope = this
        withContext(Dispatchers.IO) {
            log("withContext in IO")
        }
        log("Back in main")
    }
}

/*
* Job in the context
* */
private fun notCancelled() = runBlocking {
    val mainJob = launch {
        GlobalScope.launch {
            delay(5000L)
            println("I am still running!")
        }
    }
    delay(1000L)
    mainJob.cancel()
    delay(10_000L)
}

private fun cancelled() = runBlocking {
    val outerScope = this
    val mainJob = outerScope.launch {
        val innerScope = this
        val childJob = innerScope.launch {
            delay(5000L)
            println("I am still running!")
        }
        println("Child Job: ${childJob.hashCode()}")
    }

    delay(1000L)
    mainJob.children.forEach {
        println("Outside child Job: ${it.hashCode()}")
    }
    mainJob.cancel()
    delay(10_000L)
}

private fun cancelledWithContext() = runBlocking {
    val outerScope = this
    val mainJob = outerScope.launch {
        val innerScope = this
        withContext(context = Dispatchers.IO) {
            delay(5000L)
            println("I am still running!")
        }
    }

    delay(1000L)
    mainJob.children.forEach {
        println("Outside child Job: ${it.hashCode()}")
    }
    mainJob.cancel()
    delay(10_000L)
}

/*
*Naming coroutine for debugging
* */

private fun nameCoroutineForDebugging() = runBlocking {
    log("Started main coroutine")
    val scope = this
    val first = scope.async(CoroutineName("first")) {
        delay(500L)
        log("Computing first")
        252
    }

    val second = scope.async(CoroutineName("second")) {
        delay(1000L)
        log("Computing second")
        144
    }
    val result = first.await() + second.await()
    log("The answer is $result")
}
