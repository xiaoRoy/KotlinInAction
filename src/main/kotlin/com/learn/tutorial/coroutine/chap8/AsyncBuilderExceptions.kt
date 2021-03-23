package com.learn.tutorial.coroutine.chap8

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

fun main() {
    handleExceptionInAsync()
}

private fun handleExceptionInAsync() = runBlocking {
    val scope = this
    val deferred = GlobalScope.async<Int> {
        println("Throwing exception from async")
        throw IllegalArgumentException()
    }

    try {
//        deferred.await()
    } catch (exception: IllegalArgumentException) {
        println("Catch $exception")
    }
}
