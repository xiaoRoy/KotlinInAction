package com.learn.kotlininaction.chap7

import kotlin.reflect.KProperty


/*
* 7.5 Reusing property accessor logic: delegated properties
* */


class Example{
    var property: String by Delegate()
}

class Delegate{

    operator fun getValue(thisRef: Any?, property: KProperty<*>): String{
        return "$thisRef, thank you for delegating '${property.name}' to me!"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String){
        println("$value has been assigned to '${property.name}' in $thisRef")
    }
}

class Email

class Person(val name: String){

    private var _emails: List<Email>? = null

    val emails: List<Email>
    get() {
        if(_emails == null){
            _emails == loadEmails(this)
        }
        return _emails!!
    }
}

class PersonWithLazy(val name: String){
    val email by lazy { loadEmails(this) }
}

fun loadEmails(person: PersonWithLazy): List<Email>{
    return listOf()
}

fun loadEmails(person: Person): List<Email>{
    return listOf()
}


