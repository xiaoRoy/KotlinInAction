package com.learn.kotlininaction.chap2.iterating

/*
*2.4.4 Using an 'in' check
* */

fun isLetter(char: Char) = char in 'a' .. 'z' || char in 'A' .. 'Z'

fun isNotDigit(char: Char) = char !in '0' .. '9'

fun recognize(char: Char) = when(char){
    in '0' .. '9' -> "It is a digit!"
    in 'a' .. 'z', in 'A' .. 'Z' -> "It is a letter!"
    else -> "I don't know!"
}
