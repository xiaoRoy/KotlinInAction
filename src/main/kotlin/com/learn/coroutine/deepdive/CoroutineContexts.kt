package com.learn.coroutine.deepdive

import kotlinx.coroutines.*
import java.util.concurrent.Executors
import kotlin.concurrent.thread
import kotlin.coroutines.CoroutineContext


fun main() {
//    findingCoroutineContext()
//    addingContext()
//    subtractingElements()
//    learnFold()
    useCoroutineInAndroid()
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


/*
* My understanding about the suspending function
*
* */

private fun fakeAndroidEnvironment(action: (CoroutineScope) -> Unit) {
    val executor = Executors.newSingleThreadExecutor()
    val mainDispatcher = executor.asCoroutineDispatcher()
    executor.execute {
        action(obtainMainScope(mainDispatcher))
        while (true);
    }
}

private class User

private fun useCoroutineInAndroid() {

    suspend fun fetchUserCoroutineScope(email: String): String {
        println("Message")
        delay(3000)
        return "123token"
    }

    fun displayMessage(message: String) {
        println(message)
    }

    fakeAndroidEnvironment {
        val scope = it
        scope.launch {
            println("launch")
            val token = withContext(Dispatchers.IO) {
                fetchUserCoroutineScope("ab@gmail.com")
            }
            displayMessage(token)
        }
    }
}

private fun obtainMainScope(main: CoroutineDispatcher): CoroutineScope {
    return object : CoroutineScope{
        override val coroutineContext: CoroutineContext
            get() = main + Job()
    }
}
