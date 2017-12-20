package com.learn.kotlininaction.chap3.properties

/*
* 3.3.5 Extension properties
* */

val String.lasChar: Char get() = get(length - 1)

val StringBuilder.last: Int get() = length - 1

var StringBuilder.lastChar: Char
    get() = get(last)
    set(lastChar: Char){
        setCharAt(last, lastChar)
    }
