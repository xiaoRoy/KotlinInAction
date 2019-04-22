package com.learn.kotlindoc.delegation

interface Flyable {
    fun fly(): String

    fun anotherWayToFly(): String
}

class Bird (private val name: String): Flyable {

    override fun fly(): String = "Most of us could fly"

    override fun anotherWayToFly(): String {
        return ""
    }

    override fun toString(): String {
        return name
    }
}

class Swallow(flyable: Flyable) : Flyable by flyable {
    override fun anotherWayToFly(): String {
        return "We could fly very fast"
    }
}
