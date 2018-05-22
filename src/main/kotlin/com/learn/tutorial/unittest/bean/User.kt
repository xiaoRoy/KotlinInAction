package com.learn.tutorial.unittest.bean

 class User(private val firstName: String,
           private val lastName: String){

    fun fullName() = "$firstName $lastName"
}