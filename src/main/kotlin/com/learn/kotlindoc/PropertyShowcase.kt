package com.learn.kotlindoc

private class Coffee(id: String, val type: String, price: Double) {

    val id: String = id
    get() = field

    var price: Double = price //property initializer
    get() = field
    set(value) {
        /*
        * it won't get called in the property initializer
        *
        * */
        println("Set price")
        field = value
    }
}
