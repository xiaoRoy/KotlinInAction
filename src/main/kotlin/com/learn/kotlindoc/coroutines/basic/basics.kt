package com.learn.kotlindoc.coroutines.basic

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

suspend fun main() {
//    first()
//    second()
//    third()
//    fourth()
    thirdPlus()
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
    launch {
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
