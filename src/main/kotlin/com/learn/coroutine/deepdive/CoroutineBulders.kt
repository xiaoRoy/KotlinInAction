package com.learn.coroutine.deepdive

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

/*
*
* Coroutine builders
* https://kt.academy/article/cc-builders
* */


private fun learnAsyncBuilder() = runBlocking {
    val messageA = GlobalScope.async {
        delay(1000L)
        "Message A"
    }

    val messageB = GlobalScope.async {
        delay(3000L)
        "Message A"
    }

    val messageC = GlobalScope.async {
        delay(1000L)
        "Message A"
    }
}
