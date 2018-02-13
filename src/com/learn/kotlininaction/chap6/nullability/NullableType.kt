package com.learn.kotlininaction.chap6.nullability

/*
* 6.1.1 Nullable types
* */

fun textLength(text: String) = text.length

fun textLengthSafe(text: String?) = if (text!= null) text.length else 0

fun printAllCaps(text: String?){
    val result: String? = text?.toUpperCase()
//    val result = if(text != null) text.toUpperCase() else null
    println(result)
}

class Employee(val name: String, val manager: Employee?)

fun managerName(employee: Employee) = employee.manager?.name

class Address(val streetAddress: String, val zipCode: Int,
              val city: String, val country: String)

class Company(val name: String, val address: Address?)

class Person(val name: String, val company: Company?)

fun Person.countryName(person: Person): String{
    val countryName = person.company?.address?.country
    return if (countryName == null) "Unknown" else countryName
}

fun main(args: Array<String>) {
    printAllCaps(null)
}
