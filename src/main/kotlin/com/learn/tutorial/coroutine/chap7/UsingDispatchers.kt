package com.learn.tutorial.coroutine.chap7

import com.learn.tutorial.coroutine.showCurrentThreadName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    learnDefaultDispatcher()
}

private fun learnDefaultDispatcher() = runBlocking {
    val scope = this
    scope.launch(context = Dispatchers.Default) {
        showCurrentThreadName()
    }
    delay(100L)
}
