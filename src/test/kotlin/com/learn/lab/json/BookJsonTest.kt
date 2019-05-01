package com.learn.lab.json

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.google.gson.stream.JsonReader
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import kotlin.test.assertFailsWith

class BookJsonTest {

    private companion object {
        private const val BOOK_JSON: String = """{"id":"2322","title":"Harry Potter", "pageCount": 345, "author":null}"""
    }

    @Rule
    @JvmField
    val expectedException: ExpectedException = ExpectedException.none()

    @Test
    fun test_json_by_lazy() {
        val book = Gson().fromJson<Book>(BOOK_JSON, Book::class.java)
        Assert.assertTrue(book.title.isNotEmpty())
        Assert.assertEquals("Harry Potter", book.title)
        Assert.assertEquals(345, book.pageCount)
    }

    @Test(expected = NullPointerException::class)
    fun test_json_with_property_not_include_in_json_without_default_value() {
        val book = Gson().fromJson<BookB>(BOOK_JSON, BookB::class.java)
        Assert.assertTrue(book.title.isNotEmpty())
    }

    @Test
    fun test_json_with_property_not_include_in_json_without_default_value_using_rule() {
        val book = Gson().fromJson<BookB>(BOOK_JSON, BookB::class.java)
        expectedException.expect(NullPointerException::class.java)
        Assert.assertTrue(book.title.isNotEmpty())
    }

    @Test
    fun test_json_with_property_not_include_in_json_without_default_value_using_kotlin_test_library() {
        assertFailsWith<NullPointerException> {
            val book = Gson().fromJson<BookB>(BOOK_JSON, BookB::class.java)
            Assert.assertTrue(book.title.isNotEmpty())
        }
    }

    @Test
    fun test_parseJson_notUseBackingProperty() {
        val book = Gson().fromJson<BookC>(BOOK_JSON, BookC::class.java)
        Assert.assertNull(book.author)
    }
}
