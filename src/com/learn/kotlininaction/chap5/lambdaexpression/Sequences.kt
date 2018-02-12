package com.learn.kotlininaction.chap5.lambdaexpression


/*
* 5.3 Lazy collection operations: sequences
* */
fun sequences(people: List<Person>){
    people.map(Person::name)
            .filter{name -> name.startsWith("A")}
    people.asSequence()
            .map(Person::name)
            .filter{name -> name.startsWith("A")}
            .toList()
}

private val numbers = listOf<Int>(1, 2, 3, 4)

fun collectionOrder(){
    numbers.map { it * it }
            .find { it > 3 }
}

fun sequenceOrder(){
    numbers.asSequence()
            .map { it * it }
            .find { it > 3 }
}

private val people = listOf(Person("Alice", 29), Person("Bob", 31), Person("Charles", 31), Person("Dan", 21))

fun filterByName(){
    people.map(Person::name)
            .filter { it.length < 4 }
    people.asSequence()
            .filter { it.name.length < 4 }
            .map (Person::name)
            .toList()
}

fun createSequence(){
    val numbers = generateSequence(1){it + 1}
    val numbersToOneHundred = numbers.take(100)
    println(numbersToOneHundred.sum())
}

fun main(args: Array<String>) {
    createSequence()
}
