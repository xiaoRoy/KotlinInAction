package com.learn.kotlininaction.chap4.declareclass

/*
* 4.2.2 Secondary constructors: initializing the superclass in different ways
* */

open class Star{
    constructor(name: String, size: Double){

    }

    constructor(name: String){

    }
}

class Earth : Star{
    constructor(name: String, size: Double) : super(name, size)

    constructor(name: String) : super(name)
}

class Moon : Star{
    init{
        println("init block")
    }

    constructor(name: String) : this(name, 0.0){
        println("secondary constructor")
    }

    constructor(name: String, size: Double) : super(name, size)
}

class Person (val name: String){
    constructor(name: String, age: Int) : this(name){
        //do something
    }
}

fun main(args: Array<String>) {
    val moon = Moon("light")
}

