package com.learn.article.coroutines.parent

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.lang.IndexOutOfBoundsException
import kotlin.system.measureTimeMillis


fun main() {
//    doTwoTaskParallel()
    while (true){
        doTwoTaskParallelInRunBlocking()
    }
}

private fun doTwoTaskParallel() = runBlocking {
    val time = measureTimeMillis {
        val result = concurrentSum()
        println("Result is $result")
    }
    println("Completed in $time")
}

private suspend fun concurrentSum(): Int {
    return coroutineScope {
        val one = async { doFirstTask() }
        val two = async { doSecondTask() }
        one.await() + two.await()
    }
}

private suspend fun doFirstTask(): Int {
    delay(3000L)
    return 12
}

private suspend fun doSecondTask(): Int {
    delay(1200)
    return 24
}

/*
* exception in second task, not using coroutineScope, in the blockingRun
* */

private suspend fun doSecondTaskWithError(): Int {
    delay(1200)
    throw IndexOutOfBoundsException()
}

//crash
private fun doTwoTaskParallelInRunBlocking() = runBlocking {
    val scope = this
    val first = scope.async { doFirstTask() }
    val second = scope.async { doSecondTaskWithError() }
    val firstResult = try {
        first.await()
    } catch (exception: Exception) {
        println(exception)
        0
    }
    val secondResult = try {
        second.await()
    } catch (exception: Exception) {
        println(exception)
        0
    }
    val result = firstResult + secondResult
    println("Result is $result")
}
