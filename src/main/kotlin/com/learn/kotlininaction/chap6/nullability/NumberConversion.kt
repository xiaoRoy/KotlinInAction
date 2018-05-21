package com.learn.kotlininaction.chap6.nullability


fun numberConversion(){
    val aInt = 1
    //error
    //val aLong: Long = aInt
    val aLong: Long = aInt.toLong()
}

fun notInList(){
    val aInt = 1
    val longList = listOf<Long>(1L, 2L, 3L)
    println(aInt.toLong() in longList)
}

fun printLong(number: Long){
    println(number)
}

fun autoLiteralConvert(){
    //Int -> Byte
    val aByte: Byte = 1
    //Byte -> Long
    val aLong: Long = aByte + 1L
    //Int -> Long
    printLong(42)
}
