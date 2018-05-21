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
    println("Alice might be somewhere")
}


fun lookForAliceWithLabelFunctionName(people: List<Person>){
    people.forEach {
        if(it.name == "Alice") {
            return@forEach
        } else{
            println("${it.name} is not Alice")
        }
    }
    println("Alice might be somewhere")
}

fun labelThis(){
    val result = StringBuilder().apply label@{
        listOf(1, 2, 3).apply {
            this@label.append(this.toString())
        }
    }
    println(result)
}

fun lookForAliceAnonymous(people: List<Person>) {
    people.forEach(fun(person) {
        if (person.name == "Alice") return
        println("${person.name} is not Alice")
    })
}

fun main(args: Array<String>) {
    val people = listOf(Person("Smith"), Person("Alice"), Person("Sky"))
    lookForAliceWithLabelFunctionName(people)
    labelThis();
}
