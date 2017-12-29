package com.learn.kotlininaction.chap3.functions

class User(val id: Int, val name: String, val address: String)

fun saveUser(user: User){
    if(user.name.isEmpty()){
        throw IllegalArgumentException("Can't save user ${user.id}: by empty name")
    }
    if(user.address.isEmpty()){
        throw IllegalArgumentException("Can't save user ${user.id}: by empty address")
    }
}

fun saveUserLocalFunction(user: User){
    fun validate(user: User, value: String, fieldName: String){
        if(value.isEmpty()){
            throw IllegalArgumentException("Can't save user ${user.id}: by empty $fieldName")
        }
    }
    validate(user, user.name, "name")
    validate(user, user.address, "address")
}

fun saveUserLocalFunctionB(user: User){
    fun validate(value: String, fieldName: String){
        if(value.isEmpty()){
            throw IllegalArgumentException("Can't save user ${user.id}: by empty $fieldName")
        }
    }
    validate(user.name, "name")
    validate(user.address, "address")
}

fun User.validate(){
    fun validate(value: String, fieldName: String){
        if(value.isEmpty()){
            throw IllegalArgumentException("Can't save user $id: by empty $fieldName")
        }
    }
    validate(name, "name")
    validate(address, "address")
}

fun saveUserLocalFunctionC(user: User){
    user.validate()
}

fun main(args: Array<String>) {
    saveUserLocalFunctionC(user = User(1, "", ""))
}
