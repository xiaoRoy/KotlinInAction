package com.learn.kotlindoc.coroutines.basic

import kotlinx.coroutines.*

suspend fun main() {
//    first()
//    second()
//    third()
//    fourth()
//    thirdPlus()
//    fifth()
    likeDaemonThread()
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

//Structured concurrency
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
        val scope: CoroutineScope = this
        scope.launch {
            delay(200L)
            println("Task from runBlocking")
        }
        /*val block: suspend (CoroutineScope) -> Unit = {
            it.launch {
                delay(500L)
                println("Task from nested launch")
            }
            delay(100L)
            println("Task from coroutine scope")
        }
        coroutineScope(block)*/

        coroutineScope {
            val anotherScope = this
            anotherScope.launch {
                delay(500L)
                println("Task from nested launch")
            }
            delay(100L)
            println("Task from coroutine scope")
        }

        println("coroutine scope is over")
    }
}

fun firstSuspend() = runBlocking {
    val scope = this
    scope.launch {
        printWorld()
    }
    println(HELLO)
}

private suspend fun printWorld() {
    delay(1000L)
    println(WORLD)
}

private suspend fun printWorldWithScope(scope: CoroutineScope) {
    delay(1000L)
    println(WORLD)
}

private fun likeDaemonThread() {
    runBlocking {
        GlobalScope.launch {
            repeat(1000) { index ->
                println("I'm sleeping #$index")
                delay(500L)
            }
        }
        delay(1300L)
    }
}


