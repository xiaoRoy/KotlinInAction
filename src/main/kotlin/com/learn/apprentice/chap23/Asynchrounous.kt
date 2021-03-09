package com.learn.apprentice.chap23

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.thread


fun main() {
//    startThread()
    startCoroutine()
}

private val showMessage = { index: Int ->
    val message = "Message $index from the ${Thread.currentThread().name}"
    println(message)
}

private fun startThread() {


    thread(start = true, name = "not a main thread") {
        (0..10).forEach(showMessage)
    }

    (0..10).forEach(showMessage)
}

private fun startCoroutine() = runBlocking {
    launch(context = Dispatchers.Default) {
        (0..10).forEach(showMessage)
    }
    (0..10).forEach(showMessage)
}


