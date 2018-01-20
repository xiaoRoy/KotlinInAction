package com.learn.kotlininaction.chap4.createinstance

data class ClientData constructor(val name: String, val postalCode: Int)

fun main(args: Array<String>) {
    val clientSmith = ClientData("Smith", 321221)
    println(clientSmith.copy(postalCode = 523525))
}
