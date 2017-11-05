package com.learn.kotlininaction.chap2

fun main(args: Array<String>) {
    println("Hello World!")
    println(max(1, 5))
}


fun max(firstOne : Int, secondOne : Int) : Int{
    return if(firstOne > secondOne) firstOne else secondOne
}

fun maxB(firstOne : Int, secondOne : Int) : Int =
        if(firstOne > secondOne) firstOne else secondOne

fun maxC(firstOne : Int, secondOne : Int) =
        if(firstOne > secondOne) firstOne else secondOne
