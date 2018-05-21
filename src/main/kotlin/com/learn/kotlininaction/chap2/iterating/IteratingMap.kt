package com.learn.kotlininaction.chap2.iterating

import java.util.*


/*
* 2.4.3 Iterating over maps
* */

fun iterateMap(){
    val binaryReps = TreeMap<Char, String>()
    for(char in 'A' .. 'F'){
        val binary = Integer.toBinaryString(char.toInt())
        binaryReps[char] = binary
    }
    for((letter, binary) in binaryReps){
        println("$letter:$binary")
    }
}

fun iterateListWithIndex(){
    val fruits = arrayListOf<String>("Apple", "Beach", "Pear")
    for((index, fruit) in fruits.withIndex()){
        println("$index -- $fruit")
    }
}

fun main(args: Array<String>) {
    iterateListWithIndex()
}
