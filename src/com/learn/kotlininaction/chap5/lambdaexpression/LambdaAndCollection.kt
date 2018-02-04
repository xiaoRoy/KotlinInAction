package com.learn.kotlininaction.chap5.lambdaexpression


/*
* 5.1.1 Introduction to lambdas: blocks of code as method parameters
* */
data class Person(val name:String, val age:Int)

fun findTheOldest(people:List<Person>){
    var maxAge = 0
    var theOldest:Person? = null
    for (person in people){
        if(person.age > maxAge){
            maxAge = person.age
            theOldest = person
        }
    }
    println(theOldest)
}

fun findTheOldestLambda(people: List<Person>){
    val theOldest = people.maxBy { it.age }
    println(theOldest)
}


fun findTheOldestMemberReference(people: List<Person>){
    val theOldest = people.maxBy (Person::age)
    println(theOldest)
}
