package com.learn.kotlindoc.scopefunc

import kotlin.random.Random

private class Person(
        val name: String,
        var age: Int = 0,
        address: String = "unknown"
) {
    var address: String
        private set

    init {
        this.address = address
    }

    fun moveTo(address: String) {
        this.address = address
    }

    fun increaseAge() {
        age++
    }
}


private fun showAliceInfo() {
    val alice = Person("Alice", 20, "LA")
    println(alice)
    alice.increaseAge()
    alice.moveTo("New York")
    println(alice)
}

private fun showAliceInfoWithLet() {
    Person("Alice", 20, "LA").let {
        println(it)
        it.increaseAge()
        it.moveTo("New York")
        println(it)
    }
}

private fun showAliceUsingAlso() {
    Person("Alice", 20, "LA").also {
        println(it)
        it.increaseAge()
        it.moveTo("New York")
        println(it)
    }
}

private fun showAliceInfoWithRun() {
    Person("Alice", 20, "LA").run {
        println(this)
        increaseAge()
        moveTo("New York")
        println(this)
    }
}

private fun showAliceUsingWith() {
    with(Person("Alice", 20, "LA")) {
        println(this)
        increaseAge()
        moveTo("New York")
        println(this)
    }
}

private fun showAliceUsingApply() {
    Person("Alice", 20, "LA").apply {
        println(this)
        increaseAge()
        moveTo("New York")
        println(this)
    }
}

/*
* this (run, with and apply)is recommended for lambdas that mainly operate on the object members
* */

private val adam = Person("adam").apply {
    age = 11
    moveTo("Beijing")
}


/*
* hey can be included into call chains as side steps:
* you can continue chaining function calls on the same object after them.
* */
private fun generateAlphabet(): List<String> {
    val alphabet = mutableListOf<String>()
    alphabet.also { println("populating the list") }
            .apply {
                add("a")
                add("z")
                add("w")
                add("i")
            }
            .also { println("sort the list") }
            .sort()
    return alphabet
}

/*
* They also can be used in return statements of functions returning the context object.
* */
private fun getRandomInt(): Int {
    return Random.nextInt(100).also {
        print("$it value is generated")
    }
}

/*
* you can use them when assigning the result to a variable, chaining operations on the result
* */
private fun countNumberEndWithE(): Int {
    val numbers = mutableListOf("one", "two", "three")
    return numbers.run {
        add("four")
        add("five")
        count { it.endsWith("E") }
    }
}

/*
*  you can ignore the return value and use a scope function to create a temporary scope for variables
* */

private fun showFirstAndLast() {
    val numbers = listOf(1, 2, 3, 4, 5, 44)
    with(numbers) {
        val first = numbers.first()
        val last = numbers.last()
        println("First item:$first, last item:$last")
    }
}

