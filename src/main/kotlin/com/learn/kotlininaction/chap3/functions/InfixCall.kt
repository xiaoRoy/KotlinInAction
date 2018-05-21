package com.learn.kotlininaction.chap3.functions

/*
* 3.4.3 Working with pairs: infix calls and destructuring declarations
* */

val numberMap: Map<Int, String> = mapOf(1 to "One", 3 to "three", 10 to "ten")

val numberMapB = mapOf(1.to("One"), 3.to("three"), 10.to("ten"))


/*
* 'infix' modifier is applicable on member function or extension function
* */
infix fun Int.display(number: String): String{
    val stringBuilder = StringBuilder(this.toString())
    stringBuilder.append(":").append(number)
    return stringBuilder.toString()
}


fun destructuringDeclaration(){
    val(number, name) = 1 to "one"
    println(number)
    println(name)

    for((numberInMap, nameInMao) in numberMap){
        println("key:$numberInMap")
        println("value:$nameInMao")
    }
}

fun main(args: Array<String>) {
    val oneDisplay = 1 display "one"
    println(oneDisplay)

    val oneDisplayB = 1.display("one")
    println(oneDisplayB)
}
