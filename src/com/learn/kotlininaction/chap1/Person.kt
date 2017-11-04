package com.learn.kotlininaction.chap1

data class Person (val name : String, val age : Int? = null)

fun main(args: Array<String>) {
    val persons = listOf(Person("Peter", 30), Person("Mary", 28))
    val oldest = persons.maxBy { it.age ?: 0 }
    println("The oldest is $oldest")
}
