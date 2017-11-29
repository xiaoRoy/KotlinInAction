package com.learn.kotlininaction.chap2.other

import com.learn.kotlininaction.chap2.createRandomRectangle

fun main(args: Array<String>) {
    val rectangle = createRandomRectangle()
    println("is square:${rectangle.isSquare}")
}
