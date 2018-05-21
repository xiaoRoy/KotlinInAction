package com.learn.tutorial.unittest.business

import com.learn.tutorial.unittest.bean.User

class SayHello (private val user: User){

    fun sayHello() = "Hello, ${user.fullName()}"
}