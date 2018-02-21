package com.learn.kotlininaction.chap7.arithmetic

import com.learn.kotlininaction.chap5.lambdaexpression.containerMap
import com.learn.kotlininaction.chap7.Point


/*
* 7.1 Overloading arithmetic operators
* */
fun pointPlus(): Unit{
    val pointA = Point(0, 0)
    val pointB = Point(12, 11)
    println(pointA + pointB)
}

operator fun Point.times(scale: Double): Point{
    return Point((x * scale).toInt(), (y * scale).toInt())
}

operator fun Char.times(count: Int): String{
    return this.toString().repeat(count)
}

fun pointScale(): Unit{
    val point = Point(3, 22)
    val scaledPoint: Point = point * 1.5
}


fun main(args: Array<String>) {
    pointPlus()
}
