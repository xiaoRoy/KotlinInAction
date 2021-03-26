package com.learn.tutorial.coroutine.chap8

import kotlinx.coroutines.runBlocking
import java.lang.IndexOutOfBoundsException
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine


fun main() {
    runBlocking {
        try {
            val result = fetchDataAsync(true)
            println("result: $result")
        } catch (exception: Exception) {
            println("Caught exception:$exception")
        }
    }
}

private suspend fun fetchDataAsync(triggerError: Boolean): String {
    return suspendCoroutine { continuation: Continuation<String> ->
        fetchData(object : Callback {
            override fun onSuccess(result: String) {
                continuation.resumeWith(Result.success(result))
//                continuation.resume(result)
            }

            override fun onError(exception: Exception) {
                continuation.resumeWith(Result.failure(exception))
//                continuation.resumeWithException(exception)
            }
        }, triggerError)
    }
}

private fun fetchData(callback: Callback, triggerError: Boolean) {
    try {
        Thread.sleep(3000)
        if (triggerError) throw IndexOutOfBoundsException()
        callback.onSuccess("who are you?")
    } catch (exception: Exception) {
        callback.onError(exception)
    }
}


private interface Callback {
    fun onSuccess(result: String)
    fun onError(exception: Exception)
}

