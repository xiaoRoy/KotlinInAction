package com.learn.kotlininaction.chap8.higherorderfunction

fun functionType(){
    val sum: (Int, Int) -> Int = {x, y -> x + y}
    val action: () -> Unit = { println()}
    val returnNull: (Int, Int) -> Int? = {x, y -> null}
    val nullFunctionType: ((String) -> Boolean)? = null
    val functionTypeWithParameterName: (text: String) -> Boolean = {text -> text.isEmpty()}
    val functionTypeWithParameterNameAndChangeIt: (text: String) -> Boolean = { string -> string.isEmpty()}
}
