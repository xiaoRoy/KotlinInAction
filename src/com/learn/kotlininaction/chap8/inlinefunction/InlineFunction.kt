package com.learn.kotlininaction.chap8.inlinefunction

import java.util.concurrent.locks.Lock

inline fun <T> lock(lock: Lock, action: () -> T): T{
    lock.lock()
    try {
        return action()
    }finally {
        lock.unlock()
    }
}

fun actionToLock(lock: Lock){
    println("before")
    lock(lock){ println("action")}
    println("after")
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
}

