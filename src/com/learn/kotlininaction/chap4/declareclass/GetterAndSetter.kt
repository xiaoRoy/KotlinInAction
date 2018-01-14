package com.learn.kotlininaction.chap4.declareclass


/*
* 4.2.4 Accessing a backing field from a getter or setter
* */

class AppleUser(val name: String) {
    var address: String = "unspecified"
    set(value) {
        println("""
            Address was changed for $name:
            "$field" -> "$value".""".trimIndent())
        field = value
    }
}

fun main(args: Array<String>) {
    val appleUser = AppleUser("apple")
    appleUser.address = "xxRoad, xxTown"
    val counter = Counter()
    counter.printlnCount()
}


class Customer(name: String){
    val customerKey: String = name.toUpperCase()
}

class Food(val name: String)

class Counter {
    var count = 0
        set(value) {
            println("setter")
            field = value
        }
        get() {
            println("getter")
            return field
        }

    fun printlnCount() {
        count = 3
        println(count)
    }
}

/*
* 4.2.5 Changing accessor visibility
* */

class LengthCounter{
    var count: Int = 0
    private set

    fun addWork(word: String){
        count += word.length
    }
}


