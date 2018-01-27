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
}

fun accessPrivate(){
    val rice = Rice("100")
    // can't access private method
    //rice.weight()
    Rice.weight()
}
