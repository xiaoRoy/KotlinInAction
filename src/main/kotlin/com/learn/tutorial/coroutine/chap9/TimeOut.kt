package com.learn.tutorial.coroutine.chap9

import kotlinx.coroutines.*


fun main() {
//    firstTimeout()
    getResultFromWithTimeout()
}

private fun firstTimeout() = runBlocking {
    try {
        withTimeout(1500) {
            repeat(10) {
                println("I am #$it")
                delay(500)
            }
        }
    } catch (timeoutCancellationException: TimeoutCancellationException) {
        println("Caught $timeoutCancellationException")
    }

}


private fun getResultFromWithTimeout() = runBlocking {
    val result = withTimeoutOrNull(1300) {

        launch {  }
        repeat(1000) {
            println("I am #$it")
            delay(100L)
        }
        "Done"
    }
    println("Result is $result")
}
