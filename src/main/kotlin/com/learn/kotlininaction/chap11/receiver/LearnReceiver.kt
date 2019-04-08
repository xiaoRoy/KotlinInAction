package com.learn.kotlininaction.chap11.receiver

import java.lang.StringBuilder

fun buildString(
        action: (StringBuilder) -> Unit
): String {
    val stringBuilder = StringBuilder()
    action(stringBuilder)
    return stringBuilder.toString()
}

fun buildStringB(
        //StringBuilder.() Function Literals with Receiver
        action: StringBuilder.() -> Unit
): String {
    val stringBuilder = StringBuilder()
    stringBuilder.action()
    "".apply {  }
    return stringBuilder.toString()
}

val sum: Int.(Int) -> Int = { other -> plus(other) }
