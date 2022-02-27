package com.learn.coroutine

import kotlinx.coroutines.*


fun main() {
//    demonstrateDispatcherSwitch()
//    learnBasicBuilderLaunch()
    learnAsync()
}

//The Basic Builder, launch

private fun learnBasicBuilderLaunch() {
    runBlocking {
        val scope = this
        scope.launch {
            delay(2000)
            println("This is executed")
        }
    }
}

private fun learnAsync() {
    runBlocking {
        val scope = this
        val deferred = scope.async {
            delay(2000)
            println("This is executed after the delay")
            "not the result"
        }
        println("This is called alter calling async()")
        val result = deferred.await()
        println("This is the result: $result")
    }
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
