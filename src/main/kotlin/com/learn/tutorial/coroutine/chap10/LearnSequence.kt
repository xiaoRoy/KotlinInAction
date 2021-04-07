package com.learn.tutorial.coroutine.chap10

import kotlinx.coroutines.delay


fun main() {
//    showSequence()
    iterateSimpleYield()
}


private fun eagerCollection() {
    listOf(1, 2, 3).filter {
        println("filter,")
        it > 0
    }.map {
        println("map,")
        it * 2
    }.forEach { _ ->
        println("forEach")
    }
}

private fun lazySequence() {
    listOf(1, 2, 3).asSequence().filter {
        println("filter,")
        it > 0
    }.map {
        println("map,")
        it * 2
    }.forEach { _ ->
        println("forEach")
    }
}

private fun fibGenerator() = sequence<Int> {
    val sequenceScope: SequenceScope<Int> = this
    print("Suspending...")
    this.yield(0)
    var current = 0
    var next = 1
    while (true) {
        print("Suspending...")
        yield(next)
        val temp = current + next
        current = next
        next = temp
    }
}

private fun showSequence() {
    val sequence = fibGenerator().take(14)
    showFib(sequence, 10)
    println("another round")
    showFib(sequence, 4)
}


private fun showFib(sequence: Sequence<Int>, count: Int) {
    sequence.take(count).forEach {
        println(it)
    }
}

private fun iterateSimpleYield() {
    val fruits = simpleYield()
    fruits.forEach {
        println(it)
    }
}

private fun simpleYield() = sequence<String> {
    yield("Apple")
    yield("Orange")
    yield("Banana")
}

private fun learnYieldAll() = sequence<Int> {
    yieldAll(1..5)
}

private fun learnYieldAllWithSequence() = sequence<Int> {
    yieldAll(generateSequence(2) { it * 2 })
}
