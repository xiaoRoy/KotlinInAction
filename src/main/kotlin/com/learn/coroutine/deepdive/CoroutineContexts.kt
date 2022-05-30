package com.learn.coroutine.deepdive

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Job


fun main() {
//    findingCoroutineContext()
//    addingContext()
    subtractingElements()
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
}
