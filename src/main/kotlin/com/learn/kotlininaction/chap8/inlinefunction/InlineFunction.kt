package com.learn.kotlininaction.chap8.inlinefunction

import java.io.BufferedReader
import java.io.FileReader
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

inline fun <T> lock(lock: Lock, action: () -> T): T{
    lock.lock()
    try {
        return action()
    }finally {
        lock.unlock()
    }
}

fun actionToLock(lock: Lock){
    println("before lock")
    lock(lock){ println("action")}
    println("after lock")
}

fun howInlineWork(lock: Lock){
    println("before lock")
    lock.lock()
    try {
        println("action")
    }finally {
        lock.unlock()
    }
    println("after lock")
    val lock: Lock = ReentrantLock()
    lock.withLock {  }

}

class LockOwner(val lock: Lock){
    fun runUnderLock(body: () -> Unit){
        lock(lock, body)
    }
}

fun readFirstLineFromFile(path: String): String{
    BufferedReader(FileReader(path))
            .use { bufferedReader -> return bufferedReader.readLine() }
}
