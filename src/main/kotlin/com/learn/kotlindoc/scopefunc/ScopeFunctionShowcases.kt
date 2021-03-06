package com.learn.kotlindoc.scopefunc

import java.awt.Rectangle
import java.lang.StringBuilder
import java.util.*
import java.util.Arrays.sort
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
* they can be included into call chains as side steps:
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


//start of takeIf and takeUnless
private fun displaySubStringPosition(input: String, sub: String) {
    val index = input.indexOf(sub)
    if (index > -1) {
        println("The sub-string $sub is found in $input")
        println("Its start position is $index")
    }
}

private fun displaySubStringPositionUsingScopeFunction(input: String, sub: String) {
    input.indexOf(sub).takeIf { it >= 0 }?.let {
        println("The sub-string $sub is found in $input")
        println("Its start position is $it")
    }
}
//end of takeIf and takeUnless

/*
when to ues let
Introducing an expression as a variable in local scope
*/


private fun addThePostfix(input: String, suffix: String = "..."): String {
    val transformed = input.let {
        val isMoreThanFive = isMoreThanFive(it)
        if (isMoreThanFive) repeat(it) else reverse(it)
    }
    return "$transformed$suffix"
}

private fun addThePostfixNotUsingLet(input: String, suffix: String = "..."): String {
    val isMoreThanFive = isMoreThanFive(input)
    val transformed = if (isMoreThanFive) repeat(input) else reverse(input)
    return "$transformed$suffix"

}

private fun repeat(input: String): String {
    return StringBuilder(input).repeat(2)
}

private fun reverse(input: String): String {
    return StringBuilder(input).reverse().toString()
}

private fun isMoreThanFive(input: String): Boolean = input.length > 5

class Article(
        val title: String,
        val author: String,
        var content: String = "",
        var category: String = ""
) {
    val count: Int
        get() = content.length
}

private val article = Article(title = "Scope function", author = "Not me").apply {
    content = "I do not know..."
    category = "Kotlin"
}

class Paper(
        val articles: MutableList<Article>,
        val category: String
) {

    fun addArticle(title: String, author: String) {
        articles.add(Article(title, author).apply {
            category = this@Paper.category
        })
    }
}

fun alphabet(): String = StringBuilder().run {
    for (letter in 'A'..'Z') {
        if (letter != 'A') {
            append(",")
        }
        append(letter)
    }
    toString()
}

fun alphabetNotUseRun(): String {
    val stringBuilder = StringBuilder()
    for (letter in 'A'..'Z') {
        if (letter != 'A') {
            stringBuilder.append(",")
        }
        stringBuilder.append(letter)
    }
    return stringBuilder.toString()
}

private fun loadPhoneNumber(): String? = null

private fun callSomeOne(): Unit {
    val isConnected: Boolean = loadPhoneNumber()?.let { phoneNumber ->
        makePhoneCall(phoneNumber)
    } ?: false
}

private fun makePhoneCall(phoneNumber: String): Boolean {

    return false
}

private fun generateRandomNumberList(size: Int = 5): List<Int> =
        mutableListOf<Int>().apply {
            for (index in 0 until size) {
                add(Random.nextInt())
            }
            sort()
        }

private fun generateRandomNumberListUsingRepeat(size: Int = 5): List<Int> =
        mutableListOf<Int>().apply {
            repeat(size) { add(Random.nextInt()) }
            sort()
        }

private fun generateRandomNumberListNotUsingApply(size: Int = 5): List<Int> {
    val numbers = mutableListOf<Int>()
    for (index in 0 until size) {
        numbers.add(Random.nextInt())
    }
    numbers.sort()
    return numbers
}

fun calculateScore(scores: List<Double>): Double = scores.sorted().run {
    sum() - first() - last()
}


fun calculateScoreNotUsingRun(scores: List<Double>): Double {
    val sortedList = scores.sorted()
    return sortedList.sum() + sortedList.first() + sortedList.last()
}

fun showArticle(article: Article) {
    /*...*/
    with(article) {
        val hasContent = content.isNotEmpty()
        if (hasContent) {
            val articleInfo = "The title is $title and belongs to category:$category"
            showArticleInfo(articleInfo)
        }
    }
    /*...*/
}

private fun showArticleInfo(articleInfo: String) = Unit
