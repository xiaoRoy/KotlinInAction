package com.learn.kotlininaction.chap3.workingwithcollection

fun arbitraryNumber(vararg values: Int){
    println(values.size)
}

fun main(args: Array<String>) {
    arbitraryNumber(1, 3, 5)
}

fun intListOfA(values: Array<Int>): List<Int> {
    return listOf(* values)
}

fun intListOfB(values: Array<Int>): List<Int> = listOf(* values)

fun intListOfC(values: Array<Int>) = listOf(* values)
