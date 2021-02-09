package com.learn.kotlindoc.coroutines.basic

import kotlinx.coroutines.*

suspend fun main() {
//    first()
//    second()
//    third()
//    fourth()
//    thirdPlus()
    fifth()
}


private const val WORLD = "World!"


private const val HELLO = "Hello,"

private fun first() {
    GlobalScope.launch {
        delay(1000L)
        println(WORLD)
    }
    println(HELLO)
    Thread.sleep(2000L)
}

private fun second() {
    GlobalScope.launch {
        delay(1000L)
        println(WORLD)
    }
    println(HELLO)
    runBlocking {
        delay(2000L)
    }
}

private fun third() = runBlocking {
    GlobalScope.launch {
        delay(1000L)
        println(WORLD)
    }
    println(HELLO)
    delay(2000L)
}

private fun thirdPlus() = runBlocking {
    val scope: CoroutineScope = this
    scope.launch {
        delay(1000L)
        println(WORLD)
    }
    println(HELLO)
}

private suspend fun fourth() {
    val job = GlobalScope.launch {
        delay(1000L)
        println(WORLD)
    }
    println(HELLO)
    job.join()
}

/*
* scope builder
* */

fun fifth() {
    runBlocking {
        launch {
            delay(200L)
            println("Task from runBlocking")
        }

        coroutineScope {
            launch {
                delay(500L)
                println("Task from nested launch")
            }
            delay(100L)
            println("Task from coroutine scope")
        }

        println("coroutine scope is over")
    }
}


