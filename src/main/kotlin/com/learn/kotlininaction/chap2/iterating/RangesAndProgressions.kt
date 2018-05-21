package com.learn.kotlininaction.chap2.iterating
/*
* 2.4.2 Iterating over numbers: ranges and progressions
*
* */

fun fizzBuzz(number: Int) = when{
    number % 15 == 0 -> "FizzBuzz"
    number % 3 == 0 -> "Fizz"
    number % 5 == 0 -> "Buzz"
    else -> "$number"
}

fun normalRange(){
    for(number in 1 .. 100){
        println(fizzBuzz(number))
    }
}

fun reverseRange(){
    for(number in 100 downTo 1){
        println(fizzBuzz(number))
    }
}

fun stepRange(){
    for (number in 1 .. 100 step 2){
        println(fizzBuzz(number))
    }
}

fun reverseStepRange(){
    for (number in 100 downTo 1 step 2){
        println(fizzBuzz(number))
    }
}

fun main(args: Array<String>) {
    reverseStepRange()
}
