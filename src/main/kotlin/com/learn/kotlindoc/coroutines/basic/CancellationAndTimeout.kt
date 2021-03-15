package com.learn.kotlindoc.coroutines.basic

import kotlinx.coroutines.*


fun main() {
//    cancelCoroutine()
//    showCancellation()
//    checkJobActive()
//    runNonCancellable()
//    runTimeout()
//    runTimeoutOrNull()
//    releaseResourceNotRight()
//    withTimeoutRight()
    withTimeoutWithTimeoutCancellationException()
}

private fun cancelCoroutine() = runBlocking {
    val job = launch {
        var result = 99.1232
        repeat(10000000) { index ->
            result /= 99.12323
            println("I am sleeping #$index")
//            delay(500L)
        }
    }
    delay(1300L)
    releaseMain(job)
}

private suspend fun releaseMain(job: Job) {
    println("main: I am tired of waiting.")
//    job.cancel()
//    job.join()
    job.cancelAndJoin()
    println("main: Now I can quit.")
}

private fun showCancellation() = runBlocking {
    val startTime = System.currentTimeMillis()
    val job = launch {
        var next = startTime
        var index = 0
        while (index < 100) {
            if (System.currentTimeMillis() >= next) {
                println("job: I'm sleeping #${index++}")
                next += 500L
                // delay(1L) // can't be canceled without it
            }
        }
    }
    delay(1300L)
    releaseMain(job)
}

private fun checkJobActive() = runBlocking {
    val scope = this
    val job = scope.launch(Dispatchers.Default) {
        try {
            running()
        } finally {
            println("job: I'm running finally.")
        }
    }
    delay(1300L)
    releaseMain(job)
}

private fun runNonCancellable() = runBlocking {
    val scope = this
    val job = scope.launch(Dispatchers.Default) {
        try {
            running()
        } finally {
            withContext(NonCancellable) {
                println("job: I'm running finally.")
                delay(1000L)
                println("job: And I've just delay for 1 second because I'm non-cancellable.")
            }
        }
    }
    delay(1300L)
    releaseMain(job)
}

private fun CoroutineScope.running() {
    var next = System.currentTimeMillis()
    var index = 0
    while (isActive && index < 100) {
        if (System.currentTimeMillis() >= next) {
            println("job: I'm sleeping #${index++}")
            next += 500L
        }
    }
}

private fun runTimeout() = runBlocking {
    val scope = this
    withTimeout(1300L) {
        repeat(1000) { index ->
            println("job: I'm sleeping #$index")
            delay(500L)
        }
    }
}

private fun runTimeoutOrNull() = runBlocking {
    val scope = this
    val result: String? = withTimeoutOrNull(1300L) {
        repeat(1000) { index ->
            println("job: I'm sleeping #$index")
            delay(500L)
        }
        "Done!"
    }
    println("Result is $result")
}

private var acquiredCount = 0

class Resource {
    init {
        println("Resource init")
        acquiredCount++
    }

    fun close() {
        println("Resource close")
        acquiredCount--
    }
}

private fun releaseResourceNotRight() {
    runBlocking {
        val scope = this
        println("scope: $scope")
        repeat(100_000) { index ->
//            println("#$index")
            scope.launch {
                val resource = withTimeout(60) {
                    val another = this
//                    println("another: $another")
                    delay(50)
                    Resource()
                }
//                val resource = coroutineScope {
//                    delay(50)
//                    Resource()
//                }
//                println("after index#$index")
                resource.close()
            }
        }

    }
    println("Acquired count:$acquiredCount")
}


/*
* ????
* */
private fun withTimeoutWithTimeoutCancellationException() {
    runBlocking {
        repeat(100_000) {
            println("index:$it")
            var resource: Resource? = null
            try {
                withTimeout(60) {
                    delay(50)
                    resource = Resource()
                }
            } finally {
                resource?.close()
            }
        }
    }
    println("Acquired count:$acquiredCount")
}

private fun withTimeoutRight() {
    runBlocking {
        repeat(100_000) {
            launch {
                println("index:$it")
                var resource: Resource? = null
                try {
                    withTimeout(60) {
                        delay(50)
                        resource = Resource()
                    }
                } finally {
                    resource?.close()
                }
            }

        }
    }
    println("Acquired count:$acquiredCount")
}

