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
