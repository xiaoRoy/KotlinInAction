package com.learn.kotlininaction.chap2

class Book(val title: String, val isEnglish: Boolean)


fun main(args: Array<String>) {
    val book = Book("Three Pigs", true)
    println("Book Title:${book.title}")
    println("is English:${book.isEnglish}")
}
