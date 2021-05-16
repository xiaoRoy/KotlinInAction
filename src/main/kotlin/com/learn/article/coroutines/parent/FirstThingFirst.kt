package com.learn.article.coroutines.parent

import kotlinx.coroutines.*


fun main() {
    createCoroutine()
}

private fun createCoroutine() {
    val originJob = Job()
    val scope = CoroutineScope(originJob + Dispatchers.Default)
    val job = scope.launch {
        println("Do something.")
        delay(3000L)
        println("Done!")
    }
    println("originJob:$originJob")
    println("job:$job")
}

private fun learnCoroutineHierarchy() {
    val scope = CoroutineScope(Job() + Dispatchers.IO)
    val parentJob = scope.launch {
        val child = this.async {
            delay(3000)
            "Success!"
        }
        child.await()
    }
}


