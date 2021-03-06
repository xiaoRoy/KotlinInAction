package com.learn.kotlininaction.chap7.operator.collectionrange

import com.learn.kotlininaction.chap7.Point
import java.time.LocalDate

/*
* 7.3 Conventions used for collections and ranges
* */

operator fun Point.get(index: Int): Int = when(index){
    0 -> x
    1 -> y
    else -> throw IndexOutOfBoundsException("Invalid coordinate index:$index")
}

class MutablePoint(var x: Int, var y: Int)

operator fun MutablePoint.set(index: Int, value: Int){
    when(index){
        0 -> x = value
        1 -> y = value
        else -> throw IndexOutOfBoundsException("Invalid coordinate index:$index")
    }
}

data class Rectangle(val upperLeft: Point, val lowerRight: Point)

operator fun Rectangle.contains(point: Point): Boolean{
    return point.x in upperLeft.x until lowerRight.x &&
            point.y in upperLeft.y until lowerRight.y
}

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate>{
    override fun compareTo(other: MyDate) = when {
        year != other.year -> year - other.year
        month != other.month -> month - other.month
        else -> dayOfMonth - other.dayOfMonth
    }
}


fun createRange(){
    val dateRange = MyDate(2000, 1, 2).rangeTo(MyDate(2000, 4, 5))
    val dataRangeB: ClosedRange<MyDate> = MyDate(2000, 1, 2)..(MyDate(2000, 4, 5))
}


operator fun ClosedRange<LocalDate>.iterator(): Iterator<LocalDate> = object : Iterator<LocalDate> {

    var current = start

    override fun hasNext() = current <= endInclusive

    override fun next() = current.apply { current = current.plusDays(1) }
}

fun iterateLocalDateRange(){
    val startLocalDate = LocalDate.ofYearDay(2017, 1)
    val endLocalDate = startLocalDate.plusDays(100)
    val range = startLocalDate..endLocalDate
    for(localDate in range){
        println(localDate)
    }
}

fun main(args: Array<String>) {
    iterateLocalDateRange()
}
