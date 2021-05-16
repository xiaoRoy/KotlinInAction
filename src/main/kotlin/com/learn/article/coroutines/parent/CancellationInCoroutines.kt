package com.learn.article.coroutines.parent

import kotlinx.coroutines.*
import kotlin.math.pow


fun main() {
//    cancelMultipleCoroutines()
//    cancelOneChild()
//    demonstrateCoroutineCantBeStopped()
    demonstrateCoroutineCantBeStoppedSecond()
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
            if(System.currentTimeMillis() >= next) {
                println("Hello ${index ++}")
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
          number =  Math.PI.pow(number) / number
          println("Number: $number")
      }
    }

    delay(1000L)
    println("Ready to cancel")
    job.cancel()
    println("End!")
}
