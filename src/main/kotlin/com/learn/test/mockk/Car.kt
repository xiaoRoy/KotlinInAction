package com.learn.test.mockk

class Car (private val engine: Engine){

    fun star() {
        engine.active()
    }
}