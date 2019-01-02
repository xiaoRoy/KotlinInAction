package com.learn.lab.json

import com.google.gson.Gson
import org.junit.Assert
import org.junit.Test

class JsonTest {

    private companion object {
        private const val BOOK_JSON: String = """{"id":"2322","title":"Harry Potter", "pageCount": 345, "author":null}"""
    }

    @Test
    fun test_json_by_lazy() {
        val book = Gson().fromJson<Book>(BOOK_JSON, Book::class.java)
        Assert.assertTrue(book.title.isNotEmpty())
        Assert.assertEquals("Harry Potter", book.title)
        Assert.assertEquals(345, book.pageCount)
    }
}
