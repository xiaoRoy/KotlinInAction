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
    for(index in 0..length -1){
        val element = get(index)
        if(predicate(element)){
            stringBuilder.append(element)
        }
    }
    return stringBuilder.toString()
}
