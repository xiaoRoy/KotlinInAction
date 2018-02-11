package com.learn.kotlininaction.chap5.lambdaexpression

import kotlin.math.ceil

/*
* 5.1.5 Member references
* */

fun salute(){
    println("Salute")
}

fun main(args: Array<String>) {
    run (::salute)
}

fun createPerson(){
    val createPerson: (String, Int) -> Person = ::Person
    val createPersonA = ::Person
    val jack: Person = createPerson("Jack", 21)

    val isAdult: (Person) -> Boolean = Person::isAdult
}

fun Person.isAdult(): Boolean = this.age >= 18

