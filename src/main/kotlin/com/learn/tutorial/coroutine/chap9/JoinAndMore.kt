package com.learn.tutorial.coroutine.chap9

import kotlinx.coroutines.*

fun main() {
//    learnJoin()
//    learnJoinAll()
//    stillRunning()
//    stillRunningWithSuspending()
    stillRunningGlobal()
}


private fun learnJoin() = runBlocking {
    val job = launch {
        println("Crunching numbers [1, 1, 1, 0, 1]")
        delay(4000)
    }
    job.join()
    println("main: Now I can quit.")
}

private fun learnJoinAll() = runBlocking {
    val firstJob = launch {
        delay(4000)
        println("first job")
    }

    val secondJob = launch {
        println("second job")
        delay(3000)
    }

    joinAll(firstJob, secondJob)
    println("main: Now I can quit.")
}

private fun stillRunning() = runBlocking {
    val job = launch {
        while (true) {
            println("running")
        }
    }
    delay(11)
    job.cancel()
    println("main:something is still running!")
}

private fun stillRunningWithSuspending() = runBlocking {
    val job = launch {
        while (true) {
            println("running")
            delay(1)
        }
//            delay(500)
    }
    delay(11)
    job.cancel()
    println("main:something is still running!")
}

//Global coroutine are like daemon threads
private fun stillRunningGlobal() = runBlocking {
    val job = GlobalScope.launch {
        while (true) {
            println("running")
        }
    }
    delay(11)
    job.cancel()
    // but it ends as the application ends.
    println("main:something still running!")
}
