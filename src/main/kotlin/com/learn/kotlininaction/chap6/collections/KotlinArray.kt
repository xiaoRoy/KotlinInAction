package com.learn.kotlininaction.chap6.collections

fun creationOfArray(){
    val numberArray: Array<Int> = arrayOf(1, 3, 4, 5)
    val elementNullableArray: Array<String?> = arrayOfNulls(5)
    val alphabetArray = Array<String>(26, {('a' + it).toString()})
}

fun passCollectionToVarArgs(){
    val textList = listOf("A", "B", "C")
    val result = "%s/%s/%s".format(*textList.toTypedArray())
    println(result)
}

fun creationOfPrimitiveType(){
    val fiveZeroA: IntArray = IntArray(5)
    val fiveZeroB: IntArray = intArrayOf(0, 0, 0, 0, 0)
    val squareArray: IntArray = IntArray(10, {it * it})
}

fun arrayOperations(){
    val squareArray: IntArray = IntArray(10, {it * it})
    squareArray.forEachIndexed({index, element -> println("index $index, element $element")})
}

fun main(args: Array<String>) {
    arrayOperations()
}
