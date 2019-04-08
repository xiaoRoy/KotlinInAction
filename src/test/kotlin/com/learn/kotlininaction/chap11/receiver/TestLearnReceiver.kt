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
        val result = buildStringB {
            append("Hello").append("World")
        }
        Assert.assertEquals("HelloWorld", result)
    }

    @Test
    fun test_buildStringB_withAnonymous() {
        //the function type of this anonymous
        val result = buildStringB(fun StringBuilder.() {
            append("Hello").append("World")
        })
        Assert.assertEquals("HelloWorld", result)
    }

    @Test
    fun test_invokeFunctionTypeWithReceiver() {
        val result = 1.sum(2)
        Assert.assertEquals(3, result)

        val anotherResult = sum(2, 2)
        Assert.assertEquals(4, anotherResult)
    }

}
