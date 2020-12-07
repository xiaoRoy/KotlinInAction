package com.learn.regex

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class TestRegex {

    @Test
    fun test_FirstRegex() {
        val regex = """abc""".toRegex()
        val first = "abc"
        val found = regex.matches(first)
        assertTrue(found)

        val second = "abcde"
        val foundSecond = regex.containsMatchIn(second)
        assertTrue (foundSecond)
    }

    @Test
    fun test_matchNumber() {
        val regex = """\d""".toRegex()
        val first = "4"
        val foundOnlyNumber = regex.matches(first)
        assertTrue(foundOnlyNumber);

        val matchResult = regex.findAll("abc123xyz")
        val result = matchResult.map { it.value }.joinToString(separator = ",")
        assertEquals("1,2,3", result)
    }

    @Test
    fun test_matchNonNumber() {
        val regex = """\D""".toRegex()
        val allNumbers = "44123"

        val notFound = regex.containsMatchIn(allNumbers)
        assertFalse(notFound)

        val oneText = "I am 33 now."
        val found = regex.containsMatchIn(oneText)
        assertTrue(found)
    }

    @Test
    fun test_matchAnyCharacter() {
        val regex = """...\.""".toRegex()

        val matched = regex.containsMatchIn("I am fine.")
        assertTrue(matched)

        val notMatched = regex.containsMatchIn("How are you?")
        assertFalse(notMatched)
    }
}