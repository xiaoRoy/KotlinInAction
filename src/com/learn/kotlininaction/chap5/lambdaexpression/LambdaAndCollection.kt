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

fun findTheOldestThreeFormsLambda(people: List<Person>){
    //no shortcut
    val theOldestA = people.maxBy ({ person: Person -> person.age })
    //last argument of the function call
    val theOldestB = people.maxBy() { person: Person -> person.age }
    //only argument to a function
    val theOldestC = people.maxBy { person: Person -> person.age}
    //inferred the type
    val theOldestD = people.maxBy { person -> person.age }
    //default argument name
    val theOldestE = people.maxBy { it.age }
    //member reference
    val theOldestF = people.maxBy (Person::age)
}

fun findTheOldestMemberReference(people: List<Person>){
    val theOldest = people.maxBy (Person::age)
    println(theOldest)
}


fun sumLambda(){
    val sum = {x: Int, y:Int ->
        println("result of $x + $y")
        x + y
    }
}

fun sumFunction(x: Int, y: Int): Int{
    println("result of $x + $y")
    return x + y
}

fun sumLambdaType(){
    val sum: (Int, Int) -> Int = {x, y -> x + y}
}

fun sumLambdaDirectly(){
    {x: Int, y:Int -> x + y}(1, 1)
}

fun sumLambRun(){
    run{}
}

fun main(args: Array<String>) {
    val person = Person("Jack", 23)
    val age = Person::age
    println(age)
}

