package com.learn.kotlininaction.chap3.functions

/*
* 3.3 Adding methods to other people’s classes: extension functions and properties
* */

fun String.lastChar(): Char = this.get(this.length - 1)

fun String.lastCharOmitThis(): Char = get(length - 1)

fun main(args: Array<String>) {
    println("Kotlin".lastChar())
}
