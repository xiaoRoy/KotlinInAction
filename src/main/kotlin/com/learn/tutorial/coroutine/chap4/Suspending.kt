package com.learn.tutorial.coroutine.chap4

import com.learn.tutorial.coroutine.chap3.readFileByPath
import com.learn.tutorial.coroutine.showCurrentThreadName
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.File
import kotlin.concurrent.thread
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

fun main() {
//    daemonThread()
//    showUser("44")
    displayUserSecond()
}

private fun daemonThread() {
    val thread = Thread {
        Thread.sleep(1L)
        println("what")
    }
    thread.isDaemon = true
    thread.start()
}

class User(
        val id: String,
        val name: String
)


private fun getUser(userId: String): User {
    Thread.sleep(1000L)
    return User(userId, "no one")
}

private fun getUserSecond(userId: String,
                          onUserReady: (User) -> Unit,
                          onError: (Throwable) -> Unit) {
    thread {
        try {
            Thread.sleep(1000)
            val user = User(userId, "no one")
            onUserReady(user)
        } catch (exception: Exception) {
            onError(exception)
        }

    }
}

private suspend fun getUserThird(userId: String): User {
    delay(1000L)
    return User(userId, "no one")
}

private fun showUser(userId: String) {
    GlobalScope.launch {
        val user = getUserThird(userId)
        println("I am ${user.name}!")
    }
    runBlocking {
        delay(2400L)
    }
}

private suspend fun readFile(path: String): File {
    return suspendCoroutine<File> {
        readFileByPath(path) { file ->
            it.resume(file)
        }
    }
}

private fun displayUserSecond() {
    executeInBackground {
        showCurrentThreadName()
        val user = getUser("32")

        executeInMain {
            showCurrentThreadName()
            println("I am ${user.name}!")
        }
    }
    runBlocking {
        delay(2000L)
    }
}
