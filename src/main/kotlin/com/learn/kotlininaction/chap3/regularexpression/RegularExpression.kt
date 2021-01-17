package com.learn.kotlininaction.chap3.regularexpression


/*
* 3.5.2 Regular expressions and triple-quoted strings
* 3.5.3 Multiline triple-quoted strings
* */
const val TEXT_TO_SPLIT = "12.345-6.A"
const val FILE_PTAH = "/Users/yole/kotlin-book/chapter.txt"

fun splitExample() {
    val result = TEXT_TO_SPLIT.split("\\.|-".toRegex())
    print(result)
}

fun splitExampleA() {
    val result = TEXT_TO_SPLIT.split(".", "-")
    print(result)
}

fun parsePath(path: String): Triple<String, String, String> {
    val directory = path.substringBeforeLast("/")
    val fullName = path.substringAfterLast("/")

    val fileName = fullName.substringBeforeLast(".")
    val extension = fullName.substringAfterLast(".")

    return Triple(directory, fileName, extension)
}

fun parsePathRegex(path: String) {
    val regex = """(.+)/(.+)\.(.+)""".toRegex()
    val matchResult = regex.matchEntire(path)
    if (matchResult != null) {
        val (directory, fileName, extension) = matchResult.destructured
        println("Directory:$directory, File Name:$fileName, Extension:$extension")
    }
}

fun printKotlinLogo() {
    val kotlinLogo = """| //
                       .|//
                       .|/ \"""
    println(kotlinLogo.trimMargin("."))
}

fun printPrice() {
    val price = """${'$'}99.9"""
    println(price)
}

fun main(args: Array<String>) {
    parsePath(FILE_PTAH)
    parsePathRegex(FILE_PTAH)
    printKotlinLogo()
    printPrice()
}
