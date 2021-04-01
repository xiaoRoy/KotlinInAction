package com.learn.tutorial.coroutine.chap10

import com.learn.kotlininaction.chap3.workingwithcollection.intListOfA

private fun eagerCollection() {
    listOf(1, 2, 3).filter{
        println("filter,")
        it > 0
    }.map {
        println("map,")
        it * 2
    }.forEach{ _ ->
        println("forEach")
    }
}

private fun lazySequence() {
    listOf(1, 2, 3).asSequence().filter{
        println("filter,")
        it > 0
    }.map {
        println("map,")
        it * 2
    }.forEach{ _ ->
        println("forEach")
    }
}

private fun fibGenerator() = sequence<Int> {
}
