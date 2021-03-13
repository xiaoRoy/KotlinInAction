package com.learn.apprentice.chap23

import kotlinx.coroutines.*
import java.lang.IllegalArgumentException

fun main() {

    handleError()
}

private fun handleError() = runBlocking {
    val scope = CoroutineScope(Dispatchers.IO)
    val handler = CoroutineExceptionHandler { coroutineContext, exception ->
        println(exception.message)
    }

    scope.launch(context = handler) {
        delay(3000)
        throw IllegalArgumentException("error")
    }
    delay(5000L)
}
