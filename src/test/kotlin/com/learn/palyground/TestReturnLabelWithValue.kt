package com.learn.palyground

import org.junit.Test
import kotlin.test.assertEquals

class TestReturnLabelWithValue {

    @Test
    fun test_returnLabelWithValue() {
        val numbers = listOf(1, 2, 3, 5, 6)
        val result: String  = numbers.run {
            forEach {
                val number = it * it
                if (number > 10) {
                    return@run number.toString()
                }
            }
            throw NoSuchElementException()
        }
        assertEquals("25", result)
    }

    @Test
    fun test_returnWithLabel_continue() {
        var result = 0
        val numbers = listOf(1, 2, 3, 5, 6)
        numbers.forEach {
            result++
            if (it > 2) {
                return@forEach
            }
        }
        assertEquals(5, result)
    }

    @Test
    fun test_returnWithLabel_break() {
        var result = 0
        val numbers = listOf(1, 2, 3, 5, 6)
        run loop@{
            numbers.forEach {
                result++
                if (it > 2) {
                    return@loop
                }
            }
        }
        assertEquals(3, result)
    }
}