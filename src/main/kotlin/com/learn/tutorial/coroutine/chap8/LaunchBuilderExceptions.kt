package com.learn.tutorial.coroutine.chap8

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun main() {
    learnLaunchBuilderException()
}

private fun learnLaunchBuilderException() = runBlocking {
    val job = launch {
        println("1. Exception created vie launch coroutine.")
        throw IndexOutOfBoundsException()
    }

    job.invokeOnCompletion { exception ->
        println("2. Caught $exception")
    }

    job.join()
}
