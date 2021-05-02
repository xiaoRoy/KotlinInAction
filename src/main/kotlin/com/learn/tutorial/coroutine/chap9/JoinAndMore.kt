package com.learn.tutorial.coroutine.chap9

import kotlinx.coroutines.*
import java.io.IOException

fun main() {
//    learnJoin()
//    learnJoinAll()
//    stillRunning()
//    stillRunningWithSuspending()
//    stillRunningGlobal()
//    learnCancelAndJoin()
    cancelChildren()
//    throwExceptionFromOneOfChildren()
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

private fun learnCancelAndJoin() = runBlocking {
    val job = launch {
        repeat(100) {
            println("#$it is running")
            delay(2)
        }
    }
    delay(50)
    println("main: I am tired of waiting!!")
    job.cancelAndJoin()
    println("main: now i can quit.")
}

private fun cancelChildren() = runBlocking {
    val parentJob = launch {
        val childOne = launch {
            repeat(1000) {
                println("child one coroutine")
                println("I am #$it in child one coroutine.")
                delay(1000)
            }
        }
        childOne.invokeOnCompletion { exception ->
            println("Child one: $exception")
        }

        val childTwo = launch {
            repeat(1000) {
                println("child two coroutine")
                println("I am #$it in child two coroutine.")
                delay(1000)
            }
        }
        childTwo.invokeOnCompletion { exception ->
            println("Child two: $exception")
        }
    }
    delay(5000L)
    parentJob.cancelChildren()
}

private fun throwExceptionFromOneOfChildren() = runBlocking {
    val parentJob = launch {
        val childOne = launch {
            repeat(1000) {
                println("child one coroutine")
                println("I am #$it in child one coroutine.")
                if(it == 10) {
                    throw IOException()
                }
                delay(1000)
            }
        }
        childOne.invokeOnCompletion { exception ->
            println("Child one: $exception")
        }
        async {  }
        val childTwo = launch {
            repeat(1000) {
                println("child two coroutine")
                println("I am #$it in child two coroutine.")
                delay(1000)
            }
        }
        childTwo.invokeOnCompletion { exception ->
            println("Child two: $exception")
        }

    }
    parentJob.join()
}
