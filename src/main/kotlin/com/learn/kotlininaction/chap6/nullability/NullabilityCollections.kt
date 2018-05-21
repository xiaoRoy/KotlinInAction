package com.learn.kotlininaction.chap6.nullability

import java.io.BufferedReader
import java.io.StringReader

fun allKindsOfList(): Unit{
    val listA: List<Int> = ArrayList<Int>()
    val listB: List<Int?> = ArrayList<Int?>()
    val listC: List<Int>? = null
    val ListD: List<Int?>? = ArrayList<Int?>()
}

fun readNumbers(reader: BufferedReader): List<Int?>{
    val result = ArrayList<Int?>()
    for(line in reader.lineSequence()){
        try {
            val number = line.toInt()
            result.add(number)
        }catch (exception: NumberFormatException){
            result.add(null)
        }
    }
    return result
}

fun validNumbers(numbers: List<Int?>): Unit{
    var validNumberSum = 0
    var invalidCount = 0
    for(number in numbers){
        if(number != null){
            validNumberSum += number
        } else {
            invalidCount ++
        }
    }
    println("Valid Number Sum:$validNumberSum")
    println("Invalid Count:$invalidCount")
}

fun validNumberShort(numbers: List<Int?>){
    val validNumberList: List<Int> = numbers.filterNotNull()
    println("Valid Number Sum:${validNumberList.sum()}")
    println("Invalid Count:${numbers.size - validNumberList.size}")

}

fun runNumbers(){
    val reader = BufferedReader(StringReader("1\nabc\n42"))
    validNumbers(readNumbers(reader))
}

fun main(args: Array<String>) {
    runNumbers()
}


fun <T> copyElements(source: Collection<T>, target: MutableCollection<T>){
    for(element in source){
        target.add(element)
    }
}

fun runCopyElements(){
    val source: Collection<Int> = arrayListOf(2, 3, 4, 5)
    val target: MutableCollection<Int> = arrayListOf(1)
    copyElements(source, target)
}

