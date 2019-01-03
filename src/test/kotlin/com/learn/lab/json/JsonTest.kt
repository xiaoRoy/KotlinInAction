package com.learn.lab.json

import com.google.gson.Gson
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException

class JsonTest {

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
}
