package com.learn.lab.field

class Cat(val nickName: String)

class CatA(nickName: String) {
    val nickName: String = nickName
}

class CatB(nickName: String) {
    val nickName: String

    init {
        this.nickName = nickName
    }
}
