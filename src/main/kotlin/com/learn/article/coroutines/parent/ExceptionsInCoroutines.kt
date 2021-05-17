package com.learn.article.coroutines.parent

import kotlinx.coroutines.*

fun main() {
//    rootAwaitException()
//    learnSupervisorScope()
    learnFunctionCoroutineScope()
}

private fun rootAwaitException() = runBlocking {
    val scope = CoroutineScope(Job() + Dispatchers.IO)
    val deferred: Deferred<Int> = scope.async {
        delay(2000)
        throw IndexOutOfBoundsException()
    }
    try {
        val result = deferred.await()
    } catch (exception: IndexOutOfBoundsException) {
        println("Catch exception: $exception")
    }
}

private fun learnSupervisorScope() = runBlocking {
    supervisorScope {
        val deferred: Deferred<Int> = async {
            delay(2000)
            throw IndexOutOfBoundsException()
        }

        try {
            val result = deferred.await()
        } catch (exception: IndexOutOfBoundsException) {
            println("Catch exception: $exception")
        }
    }
}

private fun learnFunctionCoroutineScope() = runBlocking {
    coroutineScope {
        val deferred: Deferred<Int> = async {
            delay(2000)
            throw IndexOutOfBoundsException()
        }

        try {
            val result = deferred.await()
        } catch (exception: IndexOutOfBoundsException) {
            println("Catch exception: $exception")
        }
    }
}
