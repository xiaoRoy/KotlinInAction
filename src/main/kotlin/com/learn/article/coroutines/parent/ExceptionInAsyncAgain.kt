package com.learn.article.coroutines.parent

import kotlinx.coroutines.*
import java.lang.IndexOutOfBoundsException
import kotlin.system.measureTimeMillis


fun main() {
//    doTwoTaskParallel()
    while (true) {
//        doTwoTaskParallelInRunBlocking()
//        runGlobalAsync()
//        runGlobalAsyncHandleError()
//        errorInCoroutineScope()
//        runConcurrentSumWithErrorHandleInside()
        errorInCoroutineScopeHandleOutside()
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
* exception in second task, using coroutineScope
* */

private fun errorInCoroutineScope() = runBlocking {
    concurrentSumWithError()
}

private suspend fun concurrentSumWithError(): Int {
    return coroutineScope {
        val one = async { doFirstTask() }
        val two = async { doSecondTaskWithError() }
        one.await() + two.await()
    }
}

/*
* exception in second task, using coroutineScope,
* handle the exception inside CoroutineScope,
* still CRASH
* */
private suspend fun concurrentSumWithErrorHandleInside(): Int {
    return coroutineScope {
        val first = async { doFirstTask() }
        val second = async { doSecondTaskWithError() }
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
        firstResult + secondResult
    }
}

private fun runConcurrentSumWithErrorHandleInside() = runBlocking {
    val result = concurrentSumWithErrorHandleInside()
    println("Result is $result")
}

/*
* exception in second task, using coroutineScope,
* handle the exception outside CoroutineScope,
* still CRASH
* */

private fun errorInCoroutineScopeHandleOutside() = runBlocking {
    val result = try {
        concurrentSumWithError()
    } catch (exception: Exception) {
        println("Caught Exception $exception")
        0
    }
    println("Result is $result")
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

//global async
private fun runGlobalAsync() = runBlocking {
    val first = GlobalScope.async { doFirstTask() }
    val second = GlobalScope.async { doSecondTaskWithError() }
    val result = first.await() + second.await()
    println("Result is: $result")
}

/*
* exception in second task, using GlobalScope,
* not using coroutineScope, try catch the await
* NO CRASH
* */
private fun runGlobalAsyncHandleError() = runBlocking {
    val first = GlobalScope.async { doFirstTask() }
    val second = GlobalScope.async { doSecondTaskWithError() }

    val firstResult = try {
        first.await()
    } catch (exception: Exception) {
        println("Catch exception:$exception")
        0
    }
    val secondResult = try {
        second.await()
    } catch (exception: Exception) {
        println("Catch exception:$exception")
        0
    }
    //result is 12
    val result = firstResult + secondResult
    println("Result is: $result")
}
