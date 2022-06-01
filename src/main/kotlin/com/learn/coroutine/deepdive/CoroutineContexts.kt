package com.learn.coroutine.deepdive

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job


fun main() {
//    findingCoroutineContext()
//    addingContext()
//    subtractingElements()
    learnFold()
}

/*
* Finding elements in CoroutineContext
* */
private fun findingCoroutineContext() {
    val coroutineContext = CoroutineName("what")
    val name = coroutineContext[CoroutineName]
    println(name)
}

/*
* Adding contexts
* */

private fun addingContext() {
    val coroutineName = CoroutineName("what")
    val job = Job()
    val result = coroutineName + job
    val name = result[CoroutineName]
    println(name)
}

/*
* Subtracting elements
* */
private fun subtractingElements() {
    val coroutineName = CoroutineName("what")
    val job = Job()
    val result = coroutineName + job

    val subtracted = result.minusKey(CoroutineName)
    //null
    println(subtracted[CoroutineName])

}


private fun learnFold() {
    val coroutineName = CoroutineName("what")
    val job = Job()
    val result = coroutineName + job

    result.fold("") { accumulate, element -> "$accumulate:$element" }
            .let(::println)
}


/*
* Coroutine context and builders
* */

private fun CoroutineScope.log(message: String) {
    coroutineContext[CoroutineName]?.let {
        println("[$it] $message")
    }
}

