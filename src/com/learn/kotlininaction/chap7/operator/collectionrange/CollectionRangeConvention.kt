package com.learn.kotlininaction.chap7.operator.collectionrange

import com.learn.kotlininaction.chap7.Point

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
