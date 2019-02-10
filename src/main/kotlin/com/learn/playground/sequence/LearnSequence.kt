package com.learn.playground.sequence


//Map<String, String> to Map<String, List<String>>
fun mapToMaps() {
    val mapA = mapOf("size" to "L", "color" to "red")
    val mapB = mapOf("size" to "L", "color" to "yellow")
    val mapC = mapOf("size" to "M", "color" to "red")
    val mapD = mapOf("size" to "M", "color" to "yellow")
    val variants = listOf(mapA, mapB, mapC, mapD)
    val resultMap = mutableMapOf<String, MutableList<String>>()

    val result:Map<String, List<String>> = variants
            .flatMap { it.entries }
            .distinct()
            .groupByTo(resultMap, {it.key}, {it.value})

    variants.flatMap { it.entries }.forEach{
        println("${it.key}: ${it.value}")
    }
    print(result)
}


fun doCombination() {
    val variantColor = listOf("Red", "Yellow", "Blue", "White")
    val variantSize = listOf("L", "M", "S")
    val result = variantColor.windowed(2, 3, true)
    print(result)
}
fun main(args: Array<String>) {
    doCombination()
}