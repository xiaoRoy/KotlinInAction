package com.learn.kotlininaction.chap6.nullability

fun unitMethod(): Unit{

}

interface Processor<T>{
    fun process(): T
}

class NoResultProcessor: Processor<Unit>{
    override fun process(): Unit{
    }
}


class Phone(val name: String?)

fun fail(message: String): Nothing{
    throw IllegalStateException(message)
}

fun main(args: Array<String>) {
    val phone = Phone(null)
    val phoneName = phone.name?.toUpperCase()?: fail("Unknown")
}
