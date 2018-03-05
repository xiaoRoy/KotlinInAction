package com.learn.kotlininaction.chap8.higherorderfunction

import java.lang.StringBuilder

fun functionType(){
    val sum: (Int, Int) -> Int = {x, y -> x + y}
    val action: () -> Unit = { println()}
    val returnNull: (Int, Int) -> Int? = {x, y -> null}
    val nullFunctionType: ((String) -> Boolean)? = null
    val functionTypeWithParameterName: (text: String) -> Boolean = {text -> text.isEmpty()}
    val functionTypeWithParameterNameAndChangeIt: (text: String) -> Boolean = { string -> string.isEmpty()}
}


fun twoAndThree(operation: (Int, Int) -> Int){
    val result = operation(2, 3)
    println(result)
}

fun runTwoAndThree(){
    twoAndThree {x, y -> x + y}
    twoAndThree {x, y -> x * y}
}

fun String.filter(predicate: (Char) -> Boolean): String{
    val stringBuilder = StringBuilder()
    for(index in 0 until length){
        val element = get(index)
        if(predicate(element)){
            stringBuilder.append(element)
        }
    }
    return stringBuilder.toString()
}

fun <T> Collection<T>.joinToString(separator: String = ", ", prefix: String = "", postfix: String = "",
                                   transform:(T) -> String = {it.toString()}): String{
    val result = StringBuilder(prefix)
    for((index, element) in withIndex()){
        if(index > 0){
            result.append(separator)
        }
        result.append(transform(element))
    }
    result.append(postfix)
    return result.toString()
}

fun <T> Collection<T>.joinToStringNull(separator: String = ", ", prefix: String = "", postfix: String = "",
                                   transform:((T) -> String)? = null): String{
    val result = StringBuilder(prefix)
    for((index, element) in withIndex()){
        if(index > 0){
            result.append(separator)
        }
        if(transform != null){
            result.append(transform(element))
        } else {
            result.append(element.toString())
        }
    }
    result.append(postfix)
    return result.toString()
}

fun <T> Collection<T>.joinToStringNullFunction(separator: String = ", ", prefix: String = "", postfix: String = "",
                                       transform:((T) -> String)? = null): String{
    val result = StringBuilder(prefix)
    for((index, element) in withIndex()){
        if(index > 0){
            result.append(separator)
        }
        val temp = transform?.invoke(element) ?: element.toString()
        result.append(temp)
    }
    result.append(postfix)
    return result.toString()
}

fun main(args: Array<String>) {
    val list = listOf("Alpha", "Beta")
    println(list.joinToString())
    println(list.joinToString{it.toUpperCase()})
    println(list.joinToString(transform = {it.toLowerCase()}))
}
