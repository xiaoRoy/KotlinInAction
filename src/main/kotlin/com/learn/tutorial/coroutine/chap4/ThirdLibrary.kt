package com.learn.tutorial.coroutine.chap3

import java.io.File

fun readFileByPath(path: String, onReady: (File) -> Unit){
    Thread.sleep(1000L)
    onReady(File(path))
}
