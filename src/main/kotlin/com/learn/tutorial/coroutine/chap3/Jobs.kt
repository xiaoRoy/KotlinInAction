package com.learn.tutorial.coroutine.chap3

import kotlinx.coroutines.*

fun main() {
//    first()
//    learnJoin()
//    learnJobHierarchy()
    learnRetry()
}

fun first() = runBlocking {
    GlobalScope.launch {
        println("Hello coroutine!")
        delay(500L)
        println("Right back at ya!")
    }
    delay(1000L)
}

fun learnJoin() {
    val firstJob = GlobalScope.launch(start = CoroutineStart.LAZY) {
        delay(200)
        println("pong")
        delay(200)
    }

    GlobalScope.launch {
        delay(200)
        println("ping")
        firstJob.join()
        println("ping")
        delay(200)
    }
    runBlocking {
        delay(1000L)
    }
}

fun learnJobHierarchy() {
    with(GlobalScope) {
        val parentJob = launch {
            delay(200)
            println("I am the parent.")
            delay(200)
        }
        launch(context = parentJob) {
            delay(200)
            println("I am a child.")
            delay(200)
        }

        if (parentJob.children.iterator().hasNext()) {
            println("The job has children ${parentJob.children}.")
        } else {
            println("The job has no children.")
        }
    }
    runBlocking {
        delay(3000)
    }
}

fun learnRetry() {
    var isDoorOpen = false
    println("Unlocking the door... please wait.\n")

    GlobalScope.launch {
        delay(3000)
        isDoorOpen = true
    }

    GlobalScope.launch {
        repeat(4) {
            println("Trying to open the door...\n")
            delay(800)
            if(isDoorOpen) {
                println("Opened the door")
            } else {
                println("The door is still locked\n")
            }
        }
    }

    runBlocking {
        delay(5000L)
    }
}
