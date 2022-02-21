package com.learn.coroutine

import kotlinx.coroutines.*


fun main() {

    GlobalScope.launch(Dispatchers.Main) {
        println("before delay")
        stallForTime()
        println("after delay")
    }

    println("immediately")

}

private suspend fun stallForTime() {
    withContext(Dispatchers.Default) {
        delay(2000)
    }
}
