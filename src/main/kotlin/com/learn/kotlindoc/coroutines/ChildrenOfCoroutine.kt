package com.learn.kotlindoc.coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/*
* Children of a coroutine
* */

fun main() {
//    noParentInGlobalScope()
    showParentalResponsibility()
}

private fun noParentInGlobalScope() = runBlocking {
    val request = launch {
        GlobalScope.launch {
            println("job1: I run in GlobalScope and execute independently.")
            delay(1000L)
            println("job1: I am not affected by cancellation of the request.")
        }
        launch {
            delay(100L)
            println("job2: I am a child of the request coroutine.")
            delay(1000L)
            println("job2: I will not execute this line if my parent request is cancelled.")
        }
    }
    delay(500L)
    request.cancel()
    delay(1000L)
    println("main: wha has survived request cancellation?")
}

/*
* parental responsibility
* */

private fun showParentalResponsibility() = runBlocking {
    val request = launch {
        repeat(3) { index ->
            launch {
                delay((index + 1) * 200L)
                println("Coroutine#$index is done.")
            }
        }
        println("request: I'm done and I don't explicitly join my children that are still active.")
    }
    request.join()
    println("Now processing of the request is complete.")
}
