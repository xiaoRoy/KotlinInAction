package com.learn.kotlininaction.chap3.functions


/*
* 3.2 Making functions easier to call
* */
import java.lang.StringBuilder

var count = 0

const val DEFAULT_COUNT = 0

fun <T> joinString(
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

fun <T> joinStringDefaultParameterValue(
        collection: Collection<T>,
        separator: String = ", ",
        prefix: String = "(",
        postfix: String = ")"): String{
    return joinString(collection, separator, prefix, postfix)
}

fun <T> Collection<T>.joinStringFinal(
        separator: String = ", ",
        prefix: String = "(",
        postfix: String = ")"): String{
    val result = StringBuilder(prefix)
    for((index, element) in this.withIndex()){
        if(index > 0){
            result.append(separator)
        }
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}

fun Collection<String>.joinStringOnly(
        separator: String = ", ",
        prefix: String = "(",
        postfix: String = ")"
): String {
    val result = StringBuilder(prefix)
    for ((index, element) in withIndex()) {
        if (index > 0) {
            result.append(separator)
        }
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}


fun main(args: Array<String>) {
    val fruits = listOf<String>("Apple", "Pear", "Orange")
    println(joinString(fruits, "," ,"(", ")"))
    //name arguments
    println(joinString(fruits, separator = ",", prefix = "(", postfix = ")"))
    println(joinStringDefaultParameterValue(fruits))
    println(joinStringDefaultParameterValue(fruits, "^"))
    println(joinStringDefaultParameterValue(fruits, prefix = "{", postfix = "}"))
}
