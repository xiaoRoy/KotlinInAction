package com.learn.tutorial.coroutine.chap10


fun main() {
    showSequence()
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
