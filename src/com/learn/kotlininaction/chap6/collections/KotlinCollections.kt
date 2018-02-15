package com.learn.kotlininaction.chap6.collections

import com.learn.kotlininaction.chap6.nullability.Person
import java.io.File

fun printUppercase(texts: List<String>){
    println(CollectionUtilsJ.uppercaseAll(texts))
    println(texts.first())
}

class FileIndexer: FileContentProcessor{
    override fun processContents(path: File, binaryContent: ByteArray?, textContents: List<String>?) {
    }
}

class PersonParser: DataParserJ<Person>{
    override fun parseData(input: String, output: MutableList<Person>, errors: MutableList<String?>) {
    }
}

fun main(args: Array<String>) {
    printUppercase(listOf("a", "b", "c"))
}
