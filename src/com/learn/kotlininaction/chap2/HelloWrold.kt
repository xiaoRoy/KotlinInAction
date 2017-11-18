package com.learn.kotlininaction.chap2

fun main(args: Array<String>) {
    println("Hello World!")
    println(max(1, 5))
    languages.add("Kotlin")
    helloWorld(languages)
}


fun max(firstOne : Int, secondOne : Int) : Int{
    return if(firstOne > secondOne) firstOne else secondOne
}

fun maxB(firstOne : Int, secondOne : Int) : Int =
        if(firstOne > secondOne) firstOne else secondOne

fun maxC(firstOne : Int, secondOne : Int) =
        if(firstOne > secondOne) firstOne else secondOne

val question = "The ultimate question of lief, the universe, and  everything."
val answer = 42
val answerB : Int = 42

val languages = arrayListOf<String>("Java");

fun helloWorld(names : List<String>){
    val name : String  = if(names.isNotEmpty()) names[0] else "Kotlin"
    println("Hello $name!")
    println("Hello ${if(names.isNotEmpty()) names[0] else "Kotlin"}!")
}
