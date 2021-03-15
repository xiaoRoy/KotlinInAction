package com.learn.kotlindoc.coroutines.basic

import com.learn.tutorial.coroutine.showCurrentThreadName
import kotlinx.coroutines.*

fun main() = runBlocking(){
    showUserInfo()
}

private suspend fun showUserInfo() = coroutineScope {
    val fetchUserInfoJob = async(Dispatchers.IO) {
        showCurrentThreadName()
        getUserInfo()
    }
    withContext(Dispatchers.Main) {
        showCurrentThreadName()
        val userInfo = fetchUserInfoJob.await()
        println("User is $userInfo")
    }
}

private suspend fun getUserInfo(): String {
    delay(1000L)
    return "Mary Clay"
}
