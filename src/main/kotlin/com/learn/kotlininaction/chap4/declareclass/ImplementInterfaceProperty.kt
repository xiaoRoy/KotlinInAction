package com.learn.kotlininaction.chap4.declareclass

/*
* 4.2.3 Implementing properties declared in interfaces
* */

interface IUser {
    val name: String
    val nickname: String
        get() = "Nickname"
}

class PrivateUser(override val name: String) : IUser

class SubscribingUser(val email: String) : IUser {
    override val name: String
//        get() = email.substringBeforeLast('@')
        get() {
            println("SubscribingUser.name.custom getter run every time")
            return email.substringBeforeLast('@')
        }

    fun printName() {
        println(name)
    }

}

class FacebookUser(val accountId: Int) : IUser {
    override val name: String = getFacebookName(accountId)

    private fun getFacebookName(accountId: Int): String {
        println("FacebookUser run when the class instances")
        return "Face book name";
    }

    override fun toString(): String {
        return super.toString() + accountId
    }
}

fun main(args: Array<String>) {
    val subscribingUser = SubscribingUser("abc@123.com")
    println(subscribingUser.name)
    println(subscribingUser.name)
    println(subscribingUser.name)
    subscribingUser.printName()

    val facebookUser = FacebookUser(1)
    facebookUser.name
    facebookUser.name
}

