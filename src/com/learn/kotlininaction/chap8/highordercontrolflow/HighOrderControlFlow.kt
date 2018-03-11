package com.learn.kotlininaction.chap8.highordercontrolflow


data class Person constructor(val name:String)


fun lookForAliceNormal(people: List<Person>){
    for(person in people){
        if(person.name == "Alice"){
            println("Alice found!")
            return
        }
    }
    println("Not Found!")
}

fun lookForAlice(people: List<Person>){
    people.forEach {
        if(it.name == "Alice"){
            println("Alice found!")
            return
        }
    }
    println("Not Found!")
}

fun lookForAliceWithLabelName(people: List<Person>){
    people.forEach label@ {
        if(it.name == "Alice") {
            return@label
        }
    }
    println("Not Found!")
}


fun lookForAliceWithLabelFunctionName(people: List<Person>){
    people.forEach {
        if(it.name == "Alice") {
            return@forEach
        }
    }
    println("Not Found!")
}

fun main(args: Array<String>) {
    val people = listOf(Person("Smith"), Person("Alice"), Person("Sky"))
    lookForAliceWithLabelFunctionName(people)
}
