package com.learn.kotlininaction.chap3

import com.learn.kotlininaction.chap3.regularexpression.parsePath
import org.junit.Test
import kotlin.test.assertEquals

class RegularExpressionTest {

    @Test
    fun test_splitString() {
        val input = "12.345-6.A"
        val regex = """[.\-]""".toRegex()
        val result = input.split(regex)
        val expected = listOf("12", "345", "6", "A")
        assertEquals(expected, result)

        val resultSecond = input.split('.', '-')
        assertEquals(expected, resultSecond)
    }

    @Test
    fun test_parsePath() {
        val path = "/Users/me/kotlin/regular.kt"
        val result = parsePath(path)
        val expected = Triple("/Users/me/kotlin", "regular", "kt")
        assertEquals(expected, result)
    }
}