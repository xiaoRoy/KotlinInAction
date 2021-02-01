package com.learn.tutorial.coroutine


fun showCurrentThreadName() {
    val threadName = Thread.currentThread().name
    println("Current thread is $threadName")
}

