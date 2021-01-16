package com.learn.regex

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
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
        assertTrue(foundSecond)
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
    fun test_matchWhat() {
//        val regex = """<a class='anchor-tag' name='[1-9][0-9]'></a>\\n""".toRegex()
        val regex = """<a class='anchor-tag' name='[1-9][0-9]*'></a>\\n""".toRegex()
        val first = """what <a class='anchor-tag' name='1'></a>\n is"""
        val matchResult = regex.find(first)
        assertNotNull(matchResult)
        matchResult.value
    }

    @Test
    fun test_what() {
        val regex = """'[1-9][0-9]'""".toRegex()
        val first = """abc'12'abc"""
        val matchResult = regex.containsMatchIn(first)
        assertTrue(matchResult)
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

    @Test
    fun test_space() {
        val what = "**CD:&nbsp;** I get"
        val result = what.contains("&nbsp;**")
        assertTrue(result)

        val where = "**CD: ** I get"
        val test = "**CD: ** I waited until my daughter was two months old to start the business. If I had to do it over again, I would have started the minute we had the idea."
        val how = " **"
        val secondResult = test.contains(how)
        assertTrue(secondResult)
    }

    @Test
    fun test_replaceAnyCharacter() {
        val regex = """[&,’-]""".toRegex()
        val result = regex.replace("abc-", "")
        assertEquals("abc", result)
    }

    @Test
    fun test_H2() {
        val regex = """^##[^#]""".toRegex()
        val target = "#### what"
        val result = regex.containsMatchIn(target)
        assertFalse(result)
    }

    @Test
    fun test_matchSpecificCharacter() {
        val regex = """[cmf]an""".toRegex()
        val targets = listOf("can", "man", "fan", "dan", "ran", "pan")
        val result = targets.map { regex.matches(it) }.count { it }
        assertEquals(3, result)
    }

    @Test
    fun test_excludeSpecificCharacter() {
        val regex = """[^b]og""".toRegex()
        val target = "bog"
        val result = regex.matches(target)
        assertFalse(result)
    }

    @Test
    fun test_matchCharacterRange() {
        val regex = """[A-C][^a-c][a-c]""".toRegex()
        val target = "Adb"
        val result = regex.matches(target)
        assertTrue(result)
    }

    @Test
    fun test_matchRepetition() {
        val regex = """.{2,6}""".toRegex()
        val target = listOf("he", "has", "four","pears","apples")
        val result = target.all { regex.matches(it) }
        assertTrue(result)

        val second = listOf("a", "difficult")
        val secondResult = second.none { regex.matches(it) }
        assertTrue(secondResult)
    }

    @Test
    fun test_matchZeroOneMore() {
        val regex = """aa*b*c+""".toRegex()
        val target = listOf("aaaabcc", "aabbbbc", "aacc")
        val result = target.all { regex.matches(it) }
        assertTrue(result)

        val another = "a"
        val anotherResult = regex.matches(another)
        assertFalse(anotherResult)
    }

    @Test
    fun test_matchOptionalCharacter() {
        val regex = """\d+ files? found\?""".toRegex()
        val targets = listOf("1 file found?", "2 files found?", "24 files found?")
        val result = targets.all { regex.matches(it) }
        assertTrue(result)
    }

    @Test
    fun test_matchWhitSpace() {
        val regex = """a\sb""".toRegex()
        val targets = listOf("a b", "a\u0020b", "a\nb", "a\rb", )
        val result = targets.all { regex.matches(it) }
        assertTrue(result)

        val secondTarget = "a\u00a0b"//$#nbsp
        val secondResult = regex.matches(secondTarget)
        assertFalse(secondResult)
    }

    @Test
    fun test_matchStarting() {
        val regex = """^M""".toRegex()
        val first = "Miss Liu"
        val result = regex.containsMatchIn(first)
        assertTrue(result)

        val anotherRegex = """^M.*""".toRegex()
        val isAllMatch = anotherRegex.matches(first)
        assertTrue(isAllMatch)
    }


    @Test
    fun test_matchGroup() {
        val regex = """(^file_.+)\.pdf$""".toRegex()
        val targets = listOf("file_record_transcript.pdf", "file_07241999.pdf")
        val result = targets.all { regex.containsMatchIn(it) }
        assertTrue(result)

        val secondRegex = """(file_.+)\.pdf""".toRegex()
        val secondTarget = "what file_record_transcript.pdf file_07241999.pdf"
        val secondResult = secondRegex.containsMatchIn(secondTarget)
        assertTrue(secondResult)
    }
}