package com.learn.kotlindoc.anoymous

import org.junit.Assert
import org.junit.Test

class TestAnonymous {

    @Test
    fun test_anonymous() {
        val result = listOf(1, 2, 3, 4).filter(fun(item) = item % 2 == 0).size
        Assert.assertEquals(2, result)
    }

    @Test
    fun test_anonymous_localReturnByDefault() {
        val result = mutableListOf<Int>()
        listOf(2, 3, 4, 5).forEach(
                fun(number) {
                    if (number % 2 != 0) {
                        return
                    } else {
                        result.add(number * 2)
                    }
                })
        Assert.assertEquals(listOf(4, 8), result)
    }
}
