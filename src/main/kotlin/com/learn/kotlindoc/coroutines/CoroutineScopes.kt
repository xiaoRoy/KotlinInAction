package com.learn.kotlindoc.coroutines

import kotlinx.coroutines.*

fun main() {
    learnScope()
}

private fun learnScope() = runBlocking {
    val fragment = Fragment()
    fragment.onActivityCreated()
    delay(500L)
    fragment.onDestroy()
    delay(1000L)
}

private class Fragment {

    private val mainScope = CoroutineScope(Dispatchers.Default)

    fun onActivityCreated() {
        repeat(10) { index ->
            mainScope.launch {
                delay((index + 1) * 200L)
                println("Coroutine#$index is done.")
            }
        }
    }

    fun onDestroy() {
        mainScope.cancel()
    }
}
