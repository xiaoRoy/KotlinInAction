package com.learn.tutorial.coroutine.chap3

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

fun main() {
    (1..10000).forEach {
        GlobalScope.launch {
            val threadName = Thread.currentThread().name
            println("$it printed on $threadName")
        }

       /* Thread {
            val threadName = Thread.currentThread().name
            println("$it printed on $threadName")
        }.run()*/
    }
    Thread.sleep(1000L)
}
