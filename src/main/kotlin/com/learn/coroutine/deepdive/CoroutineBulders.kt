package com.learn.coroutine.deepdive

import kotlinx.coroutines.*

/*
*
* Coroutine builders
* https://kt.academy/article/cc-builders
* */


fun main() {
//    learnAsyncBuilder()
    learnStructureConcurrency()
}

private fun learnAsyncBuilder() = runBlocking {
    val messageA = GlobalScope.async {
        delay(1000L)
        "Message A"
    }

    val messageB = GlobalScope.async {
        delay(3000L)
        "Message B"
    }

    val messageC = GlobalScope.async {
        delay(1000L)
        "Message C"
    }
    println(messageA.await())
    println(messageB.await())
    println(messageC.await())
}

private fun learnStructureConcurrency()  = runBlocking {
    val parentScope = this
    parentScope.launch {
        delay(4000L)
        launch {
            delay(2000L)
            println("Done!")
        }
        println("Last but Second!")

    }
    parentScope.launch {
        println("Deeper")
    }
    println("Complete?")
}

/*
*
*
* */
private fun what() = runBlocking {
    GlobalScope.launch {
        delay(1000L)
        println(" world!")
    }
    println("Hello")
}

private fun where() = runBlocking {
    launch {
        delay(1000L)
        println(" world!")
    }
    println("Hello")
}


