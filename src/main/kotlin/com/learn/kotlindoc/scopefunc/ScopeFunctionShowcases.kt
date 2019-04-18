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

    fun increaseAge(): Int {
        return ++age
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
        val first = first()
        val last = last()
        println("First item:$first, last item:$last")
    }
}


//start of let
private fun showFirstAndLastNotUsingScopeFunction() {
    val numbers = listOf(1, 2, 3, 4, 5, 44)
    val first = numbers.first()
    val last = numbers.last()
    println("First item:$first, last item:$last")
}

private fun showNumberMoreThanThreeLetters() {
    val numbers = mutableListOf("one", "two", "three", "four", "five")
    numbers.filter { it.length > 3 }.let {
        print(it)
    }
}

private fun printMessage(message: String?) {
    message?.let {
        print(it)
    }
}

/*
* One important thing how to decide use let or run?  Depend on
* whether the context object call its functions or assign properties.
* */

private fun printMessageLength(message: String?) {
    message?.run {
        print(length)
    }
}

//end of let


//start of with


/*
* We recommend with for calling functions on the context object without providing the lambda result.
* In the code, with can be read as “with this object, do the following.”
* */
private fun withThisObjectDoTheFollowing() {
    val numbers = listOf(1, 3, 4)
    with(numbers) {
        println("'with' is called with argument $this")
        println("It contains $size elements")
    }
}

private fun runComparedToWith() {
    // we don't need to introduce a new variable
    listOf(1, 3, 4).run {
        println("'run' is called with argument $this")
        println("It contains $size elements")
    }
}

/*
* Another use case for with is introducing a helper object
* whose properties or functions will be used for calculating a value.
* */

private fun getTheFirstAndLastInfo(list: List<String>): String {
    return with(list) {
        "The first element is ${first()}, " +
                " the last element is ${last()}"
    }

}

private fun getTheFirstAndLastInfoUsingRun(list: List<String>): String {
    return list.run {
        "The first element is ${first()}, " +
                " the last element is ${last()}"
    }
}

//end of with

//start of run

private fun showRunWithInitializationAndComputation() {
    val currentAge = Person("Smith", 21).run {
        moveTo("London")
        increaseAge()
    }
}

private fun showLetWithInitializationAndComputation() {
    val currentAge = Person("Smith", 21).let {
        it.moveTo("London")
        it.increaseAge()
    }
}

/*
* Non- extension run lets you execute a block of several statements where an expression is required.
* */
private fun hexNumberRegexNotUsingRun(): Regex {
    val digits = "0-9"
    val hexDigits = "A-Fa-f"
    val sign = "+-"
    return Regex("[$sign]?[$digits$hexDigits]+")
}

private fun hexNumberRegex() = run {
    val digits = "0-9"
    val hexDigits = "A-Fa-f"
    val sign = "+-"
    Regex("[$sign]?[$digits$hexDigits]+")
}

//end of run

//start of apply

private fun getPersonInfo(): Person = Person("Black").apply {
    age = 22
    moveTo("Beijing")
}

//end of apply

//start of also
private fun showNumber() {
    val numbers = mutableListOf("one", "two", "three")
    numbers
            .also { println("The size of list before adding new one: {${it.size}}") }
            .add("four")
}

//end of also
