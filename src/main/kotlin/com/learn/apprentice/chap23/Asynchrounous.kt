package com.learn.apprentice.chap23

import kotlin.concurrent.thread


fun main() {
    startThread()
}

private fun startThread() {
    val showMessage = { index: Int ->
        val message = "Message $index from the ${Thread.currentThread().name}"
        println(message)
    }

    thread(start = true, name = "not a main thread") {
        (0..10).forEach (showMessage)
    }

    (0..10).forEach(showMessage)
}
