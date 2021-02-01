package com.learn.tutorial.coroutine.chap5

import kotlinx.coroutines.GlobalScope
import java.util.concurrent.Executors
import java.util.concurrent.Future

fun main() {
    handleNumber()
}

private fun handleNumber() {
    val result = parseNumber("345")
    while (result.isDone) {

    }
    val actual = result.get()
    println("The number is $actual")
}

private fun parseNumber(input: String): Future<Int> {
    val executor = Executors.newSingleThreadExecutor()
    return executor.submit<Int> {
        Thread.sleep(1000L)
        Integer.parseInt(input)
    }
}
