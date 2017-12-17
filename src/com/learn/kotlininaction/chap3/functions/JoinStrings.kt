package com.learn.kotlininaction.chap3.functions


/*
* 3.2 Making functions easier to call
* */
import java.lang.StringBuilder

var count = 0

const val DEFAULT_COUNT = 0

fun <T> joinToString(
        collection: Collection<T>,
        separator: String,
        prefix: String,
        postfix: String): String {
    val result = StringBuilder(prefix)
    for((index, element) in collection.withIndex()){
        if(index > 0) {
            result.append(separator)
        }
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}

fun <T> joinToStringDefaultParameterValue(
        collection: Collection<T>,
        separator: String = ", ",
        prefix: String = "(",
        postfix: String = ")"):String{
    return joinToString(collection, separator, prefix, postfix)
}


fun main(args: Array<String>) {
    val fruits = listOf<String>("Apple", "Pear", "Orange")
    println(joinToString(fruits, "," ,"(", ")"))
    //name arguments
    println(joinToString(fruits, separator = ",", prefix = "(", postfix = ")"))
    println(joinToStringDefaultParameterValue(fruits))
    println(joinToStringDefaultParameterValue(fruits, "^"))
    println(joinToStringDefaultParameterValue(fruits, prefix = "{", postfix = "}"))
}
