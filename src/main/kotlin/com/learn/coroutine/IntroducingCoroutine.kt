package com.learn.coroutine

import kotlinx.coroutines.*


fun main() {
    demonstrateDispatcherSwitch()

}



private fun demonstrateDispatcherSwitch() {
    showCurrentThreadInfo("main")
    GlobalScope.launch(Dispatchers.Unconfined) {
        showCurrentThreadInfo("GlobalScope")
        println("before delay")
        stallForTime()
        println("after delay")
    }

    println("immediately")
    runBlocking {
        delay(3000)
    }
}

private suspend fun stallForTime() {
    withContext(Dispatchers.IO) {
        showCurrentThreadInfo("stallForTime")
        delay(2000)
    }
}

fun showCurrentThreadInfo(tag: String) {
    val currentThreadName = Thread.currentThread().name
    println("Tag:$tag CurrentThreadName:$currentThreadName")
}
