package com.learn.kotlindoc.coroutines.exceptions

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.ArithmeticException


fun main() {
    learnExceptionPropagation()
}

/*
* Exception propagation
* */

private fun learnExceptionPropagation() {
    runBlocking {
        val job = GlobalScope.launch {
            println("Throwing Exception from launch")
            throw IndexOutOfBoundsException()
        }

        job.join()
        val deferred = GlobalScope.async<Int> {
            println("Throwing Exception from async")
            throw ArithmeticException()
        }

        try {
            deferred.await()
            println("Unreached")
        }catch (exception: ArithmeticException) {
            println("Caught ArithmeticException")
        }
    }
}
