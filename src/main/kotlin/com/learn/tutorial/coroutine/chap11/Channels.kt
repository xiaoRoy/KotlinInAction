package com.learn.tutorial.coroutine.chap11

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun main() {
    firstChannel()
}

private fun firstChannel() {
    val fruitArray = arrayOf("Apple", "Banana", "Pear", "Grapes", "Strawberry")
    val channel = Channel<String>()
    runBlocking {
        val scope = this
        scope.launch {
            for (fruit in fruitArray) {
                channel.send(fruit)
                if(fruit == "Pear") {
                    channel.close()
                    break;
                }
            }
        }
        for (element in channel) {
            println(element)
        }
        println("Done!")
    }
}
