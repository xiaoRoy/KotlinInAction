package com.learn.kotlininaction.chap4.createinstance

/*
* 4.4.2 Companion objects: a place for factory methods and static members
* */
class Rice constructor(val id: String){

    private fun weight(): Float{
        return 3.14f
    }

    companion object {
        fun weight(): Float{
            val rice = Rice("100")
            return rice.weight()
        }
    }
}

class User{
    val nickname: String

    constructor(email: String){
        nickname = email.substringBefore("@")
    }

    constructor(facebookAccountId: Int){
        nickname = getFacebookNameById(facebookAccountId)
    }

    private fun getFacebookNameById(facebookAccountId : Int) = "3.14"
}

class UserCompanionObject(val nickname: String){
    companion object {
        fun newSubscribingUser(email: String) = User(email.substringBefore("@"))

        fun newFacebookUser(facebookAccountId: Int) = User("fb$facebookAccountId")
    }

    fun test(){
        newSubscribingUser("")
    }
}

fun accessPrivate(){
    val rice = Rice("100")
    // can't access private method
    //rice.weight()
    Rice.weight()
}

/*
*4.4.3 Companion objects as regular objects
* */


class Ticket constructor(val id: String){
    companion object JsonParser{
        fun fromJson(jsonText: String): Ticket = Ticket("10001")
    }
}

interface JsonFactory<T>{
    fun fromJson(jsonText: String): T
}

class Computer(val id: String){
    companion object : JsonFactory<Computer>{
        override fun fromJson(jsonText: String): Computer {
            return Computer("123")
        }
    }
}

fun <T>loadFormJson(factory: JsonFactory<T>, jsonText: String): T{
    return factory.fromJson(jsonText)
}

fun main(args: Array<String>) {
    loadFormJson(Computer.Companion, "null")
}

class Tea constructor(val id: String){

    companion object {

    }

    fun Tea.Companion.fromJson(jsonText: String): Tea{
        return Tea("223")
    }

    fun test() = Tea.fromJson("response")
}
