package com.learn.tutorial.coroutine.chap3

import kotlinx.coroutines.*

fun main() {
//    first()
    learnJoin()
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
