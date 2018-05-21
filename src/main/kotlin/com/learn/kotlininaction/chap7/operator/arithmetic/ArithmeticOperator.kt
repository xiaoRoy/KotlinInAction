package com.learn.kotlininaction.chap7.operator.arithmetic

import com.learn.kotlininaction.chap7.Point
import java.math.BigDecimal


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

operator fun Point.unaryMinus() = Point(-x, -y)

operator fun Char.times(count: Int): String{
    return this.toString().repeat(count)
}

operator fun BigDecimal.inc() = this + BigDecimal.ONE

fun pointScale(): Unit{
    val point = Point(3, 22)
    val scaledPoint: Point = point * 1.5
}

fun plusAssign(){
    var point = Point(1, 6)
    point += Point(1, 3)
}


/*
*
* operator fun <T> MutableCollection<T>.plusAssign(element: T){
*   this.add(element)
* }
* */
fun compoundAssignment(){
    val numbers = ArrayList<Int>()
    numbers += 42
}

fun compoundAssignmentOnCollection(){
    val list = arrayListOf(1, 3)
    list += 5
    
    val newList = list + listOf(7 ,9)
}

fun unaryOperator(){
    val point = Point(3, 5)
    val negativePoint = -point
}

fun prePostSemantic(){
    var bigDecimal = BigDecimal.ZERO
    println(bigDecimal++)
    println(++bigDecimal)
}


fun main(args: Array<String>) {
    pointPlus()
}
