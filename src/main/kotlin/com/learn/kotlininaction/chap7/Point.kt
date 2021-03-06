package com.learn.kotlininaction.chap7

data class Point constructor(val x: Int, val y: Int){

    operator fun plus(other: Point): Point{
        return Point(x + other.x, y + other.y)
    }

    override fun toString() = "point($x, $y)"

    override fun equals(other: Any?): Boolean {
        if(other === this){
            return true
        }
        if(other !is Point){
            return false
        }
        return x == other.x && y == other.y
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        return result
    }


    /* operator fun Point.times(scale: Double): Point{
        return Point((x * scale).toInt(), (y * scale).toInt())
    }*/
}
