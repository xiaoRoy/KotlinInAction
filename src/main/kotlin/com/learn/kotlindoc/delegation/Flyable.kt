package com.learn.kotlindoc.delegation

interface Flyable {

    val speed: String

    fun fly(): String

    fun anotherWayToFly(): String

    fun showSpeed(): String
}

class Bird (private val name: String): Flyable {

    override val speed: String = "medium"

    override fun fly(): String = "Most of us could fly"

    override fun anotherWayToFly(): String {
        return ""
    }

    override fun showSpeed(): String {
        return "My speed is $speed"
    }

    override fun toString(): String {
        return name
    }
}

class Swallow(flyable: Flyable) : Flyable by flyable {

    override val speed: String = "fast"

    override fun anotherWayToFly(): String {
        return "We could fly very fast"
    }
}
