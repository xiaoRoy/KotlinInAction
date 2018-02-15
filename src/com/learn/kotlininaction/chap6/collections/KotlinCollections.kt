package com.learn.kotlininaction.chap6.collections

fun printUppercase(texts: List<String>){
    println(CollectionUtilsJ.uppercaseAll(texts))
    println(texts.first())
}

fun main(args: Array<String>) {
    printUppercase(listOf("a", "b", "c"))
}
