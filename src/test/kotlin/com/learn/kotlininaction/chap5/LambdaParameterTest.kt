package com.learn.kotlininaction.chap5

import org.junit.Assert
import org.junit.Test


/*
* A lambda expression or an anonymous function is a "function literal"
* kotlin doc P98
*
* */
class LambdaParameterTest {

    @Test
    fun test_no_parameter() {
        val noParameter: () -> String = { "Hello Kotlin" }
        Assert.assertTrue(noParameter() == "Hello kotlin")
    }

    @Test
    fun test_no_parameter_with_arrow() {
        val noParameter = { -> "Hello Kotlin" }
        Assert.assertTrue(noParameter() == "Hello kotlin")
    }

    @Test
    fun test_no_parameter_explicit() {
        val noParameter = { "Hello Kotlin Again" }
        Assert.assertTrue(noParameter() == "Hello Kotlin Again")
    }
}
