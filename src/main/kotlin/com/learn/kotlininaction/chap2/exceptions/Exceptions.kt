package com.learn.kotlininaction.chap2.exceptions

import java.io.BufferedReader


/*
*  2.5 Exceptions in Kotlin
* */
fun verifyPercentage(percentage: Int): Int =
        if (percentage in 0..100) {
            percentage
        } else {
            throw IllegalArgumentException("A percentage value must be between 0 and 100: $percentage")
        }

fun readNumber(reader: BufferedReader): Int?{
    var result: Int?
    try {
        val line = reader.readLine()
        result = Integer.parseInt(line)
    }
    catch (exception: NumberFormatException){
        result = null
    }
    finally {
        reader.close()
    }
    return result
}

fun  readNumberWithTryExpression(reader: BufferedReader): Int?{
    val result: Int? = try{
        val line = reader.readLine()
        Integer.parseInt(line)
    }catch (exception: NumberFormatException){
        null
    }
    return result
}
fun main(args: Array<String>) {
    verifyPercentage(1000)
}
