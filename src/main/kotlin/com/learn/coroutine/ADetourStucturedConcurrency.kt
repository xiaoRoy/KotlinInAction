package com.learn.coroutine

import java.lang.IllegalArgumentException
import kotlin.concurrent.thread

fun main() {
    throwUncaughtException()
}

private fun throwUncaughtException() {
    val thread = thread {
        throw IllegalArgumentException()
    }
    println(thread.uncaughtExceptionHandler)

    Thread.sleep(5000L)
}
