package com.learn.kotlininaction.chap11.receiver

import org.junit.Assert
import org.junit.Test
import java.lang.StringBuilder

class TestLearnReceiver {

    @Test
    fun test_buildString() {
        val result = buildString {
            it.append("Hello").append("World")
        }
        Assert.assertEquals("HelloWorld", result)
    }

    @Test
    fun test_buildStringB() {
        //the function type of this ano
        val result = buildStringB(fun StringBuilder.() {
            append("Hello").append("World")
        })
        Assert.assertEquals("HelloWorld", result)
    }
}
