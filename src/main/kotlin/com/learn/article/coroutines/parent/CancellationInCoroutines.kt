package com.learn.article.coroutines.parent

import kotlinx.coroutines.*
import kotlin.math.pow


fun main() {
//    cancelMultipleCoroutines()
//    cancelOneChild()
//    demonstrateCoroutineCantBeStopped()
//    demonstrateCoroutineCantBeStoppedSecond()
//    checkJobActiveState()
//    ensureJobActiveState()
//    cancelDeferred()
//    cancelDeferredInAnotherScope()
//    cleanupInFinally()
    cleanupInNonCancellable()
}

private fun cancelMultipleCoroutines() = runBlocking {
    val scope = CoroutineScope(Job())
    val job = scope.launch {
        val childOne = launch {
            delay(1000)
            println("Child One Done!")
        }
        val childTwo = launch {
            delay(3000)
            println("Child Two Done!")
        }
    }
    delay(1500)
    job.cancel()
}

private fun cancelOneChild() = runBlocking {
    val scope = CoroutineScope(Job())
    val job = scope.launch {
        val childOne = launch {
            delay(1000)
            println("Child One Done!")
        }
        val childTwo = launch {
            delay(3000)
            println("Child Two Done!")
        }
        delay(300)
        childOne.cancel()
    }
    job.join()
}

/*
* coroutine can't be stopped
* */

private fun demonstrateCoroutineCantBeStopped() = runBlocking {
    val startTime = System.currentTimeMillis()
    val job = launch(Dispatchers.Default) {
        var next = startTime
        var index = 0
        while (index < 5) {
            if (System.currentTimeMillis() >= next) {
                println("Hello ${index++}")
                next += 500L
            }
        }
    }

    delay(1000L)
    println("Ready to cancel")
    job.cancel()
    println("End!")
}

private fun demonstrateCoroutineCantBeStoppedSecond() = runBlocking {
    val startTime = System.currentTimeMillis()
    val job = launch(Dispatchers.Default) {
        repeat(5000_000) { index ->
            println("index: $index")
            var number = 0.1234
            number = Math.PI.pow(number) / number
            println("Number: $number")
        }
    }

    delay(1000L)
    println("Ready to cancel")
    job.cancel()
    println("End!")
}

/*
* checking for job's active state
* */

private fun checkJobActiveState() = runBlocking {
    val startTime = System.currentTimeMillis()
    val job = launch(Dispatchers.Default) {
        var next = startTime
        var index = 0
        while (index < 5 && isActive) {
            if (System.currentTimeMillis() >= next) {
                println("Hello ${index++}")
                next += 500L
            }
        }
        println("Do some cleanup")
    }

    delay(1000L)
    println("Ready to cancel")
    job.cancel()
    println("End!")
}

private fun ensureJobActiveState() = runBlocking {
    val startTime = System.currentTimeMillis()
    val job = launch(Dispatchers.Default) {
        var next = startTime
        var index = 0
        while (index < 5) {
            ensureActive()
            if (System.currentTimeMillis() >= next) {
                println("Hello ${index++}")
                next += 500L
            }
        }
    }

    delay(1000L)
    println("Ready to cancel")
    job.cancel()
    println("End!")
}

private fun cancelDeferred() = runBlocking {
    val deferred: Deferred<Int> = async {
        delay(3000)
        12
    }
    deferred.cancel()
    val result = 24 + deferred.await()
    println("Result is:$result")
}

/*
* Why is there no JobCancellationException?
* Actually there is one but launch()
* ignores the JobCancellationException
* */
private fun cancelDeferredInAnotherScope() = runBlocking {
    val handler = CoroutineExceptionHandler { _, exception ->
        println("Exception in handler:$exception")
    }
    val scope = CoroutineScope(Job() + Dispatchers.Default + handler)
    val job = scope.launch {
        val deferred: Deferred<Int> = this.async {
            delay(3000)
            12
        }
        try {
            deferred.cancel()
            val result = 24 + deferred.await()
            println("Result is:$result")
        } catch (exception: Exception) {
            println("Catch Exception:$exception")
        }

    }
    job.join()
    println("End.")
}

private fun cleanupInFinally() = runBlocking {
    val scope = CoroutineScope(Job() + Dispatchers.Default)
    val job = scope.launch {
        try {
            doSomeWork()
        } catch (exception: CancellationException) {
            println("cancelled")
        } finally {
            println("cleanup")
        }
    }
    delay(1000L)
    job.cancel()
    println("End.")
}

private suspend fun doSomeWork() {
    delay(3000)
}

private fun cleanupInNonCancellable() = runBlocking {
    val scope = CoroutineScope(Job() + Dispatchers.Default)
    val job = scope.launch {
        try {
            doSomeWork()
        } catch (exception: CancellationException) {
            println("cancelled")
        } finally {
            withContext(NonCancellable) {
                cleanup()
            }
        }
    }
    delay(1000L)
    job.cancelAndJoin()
    println("End.")
}

private suspend fun cleanup() {
    delay(1000L)
    println("Cleanup done!")
}

