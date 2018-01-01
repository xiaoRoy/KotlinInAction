package com.learn.kotlininaction.chap4.declareclass

class EmptyUser{

}

class EmptyUserWithoutCurlyBrace


class UserWithPrimaryConstructor constructor(nickname: String){
    val nickname: String

    init {
        this.nickname = nickname
        //do more initialization
    }
}

class UserWithNoInitBlock constructor(nickname: String){
    val nickname: String = nickname
}

//"val" means the corresponding property is generated for the constructor parameter.
open class User(val nickname: String)

class TwitterUser(nickname: String) : User(nickname)

class UserWithDefaultValue(val nickname: String, val isSubscribed: Boolean = true)



fun main(args: Array<String>) {
    val user = UserWithDefaultValue("Jane")
    println(user.nickname)
    println(user.isSubscribed)
    val apple = Apple()
    println(apple.color)
    println(apple.size)
}



class Apple (var color: String = "Red", var size: String = "normal")

open class Animal

class Cat : Animal()

class SecretiveA private constructor()

class SecretiveB {
    private constructor()
}

class Book private constructor(val title: String)
