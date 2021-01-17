package com.learn.kotlindoc.coroutines.basic

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main() {
    first()
}

private fun first() {
    GlobalScope.launch {
        delay(1000)
        println("world")
    }

}