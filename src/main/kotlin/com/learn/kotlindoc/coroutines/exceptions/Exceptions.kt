package com.learn.kotlindoc.coroutines.exceptions

import com.learn.tutorial.coroutine.showCurrentThreadName
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {

    demonstrateExceptionFromLaunch()
}


private fun demonstrateExceptionFromLaunch() = runBlocking {
    val job = GlobalScope.launch {
        showCurrentThreadName()
        Thread.currentThread().uncaughtExceptionHandler =
                Thread.UncaughtExceptionHandler { thread, excepiton ->
                    println(thread.name)
                    println(excepiton)
                }
        println("Throwing exception from launch")
        throw IndexOutOfBoundsException()
    }

    job.join()
}
