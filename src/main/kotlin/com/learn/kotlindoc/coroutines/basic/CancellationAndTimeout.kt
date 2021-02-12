package com.learn.kotlindoc.coroutines.basic

import kotlinx.coroutines.*


fun main() {
//    cancelCoroutine()
//    showCancellation()
//    checkJobActive()
    runNonCancellable()
}

private fun cancelCoroutine() = runBlocking {
    val job = launch {
        var result = 99.1232
        repeat(10000000) { index ->
            result /= 99.12323
            println("I am sleeping #$index")
//            delay(500L)
        }
    }
    delay(1300L)
    releaseMain(job)
}

private suspend fun releaseMain(job: Job) {
    println("main: I am tired of waiting.")
//    job.cancel()
//    job.join()
    job.cancelAndJoin()
    println("main: Now I can quit.")
}

private fun showCancellation() = runBlocking {
    val startTime = System.currentTimeMillis()
    val job = launch {
        var next = startTime
        var index = 0
        while (index < 100) {
            if (System.currentTimeMillis() >= next) {
                println("job: I'm sleeping #${index++}")
                next += 500L
                // delay(1L) // can't be canceled without it
            }
        }
    }
    delay(1300L)
    releaseMain(job)
}

private fun checkJobActive() = runBlocking {
    val scope = this
    val job = scope.launch(Dispatchers.Default) {
        try {
            running()
        } finally {
            println("job: I'm running finally.")
        }
    }
    delay(1300L)
    releaseMain(job)
}

private fun runNonCancellable() = runBlocking {
    val scope = this
    val job = scope.launch(Dispatchers.Default) {
        try {
            running()
        }finally {
            withContext(NonCancellable) {
                println("job: I'm running finally.")
                delay(1000L)
                println("job: And I've just delay for 1 second because I'm non-cancellable.")
            }
        }
    }
    delay(1300L)
    releaseMain(job)
}

private fun CoroutineScope.running() {
    var next = System.currentTimeMillis()
    var index = 0
    while (isActive && index < 100) {
        if (System.currentTimeMillis() >= next) {
            println("job: I'm sleeping #${index++}")
            next += 500L
        }
    }
}
