package com.learn.tutorial.coroutine.chap9

import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun main() = runBlocking {
    val job = this.launch {
        repeat(1000) {
            println("$it. Crunching numbers [Beep.Boop.Beep]")
            delay(500)
        }
    }
    delay(1300L)
    println("main: I am tired of waiting!")
    job.cancelAndJoin()
    println("main: Not I can quit.")
}



