package com.learn.kotlininaction.chap7

class Point constructor(val x: Int, val y: Int){

    operator fun plus(other: Point): Point{
        return Point(x + other.x, y + other.y)
    }

    override fun toString() = "point($x, $y)"

   /* operator fun Point.times(scale: Double): Point{
        return Point((x * scale).toInt(), (y * scale).toInt())
    }*/
}
