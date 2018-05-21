package com.learn.kotlininaction.chap7.operator

import com.learn.kotlininaction.chap7.Point
import org.omg.CosNaming.NameComponent

fun pointExample(){
    val point = Point(1, 2)
    val (x, y) = point
}

data class FileComponent(val name: String, val extension: String)

fun spiltFileName(fullName: String): NameComponent{
    val result = fullName.split(".", limit = 2)
    return NameComponent(result[0], result[1])
}

fun spiltFileNameDestructuring(fullName: String): NameComponent{
    val (name, extension) = fullName.split(".", limit = 2)
    return NameComponent(name, extension)
}

fun iterateEntries(maps: Map<String, String>){
    for((key, value) in maps){
        println("key:$key")
        println("value:$value")
    }
}
