package com.learn.kotlininaction.chap6.nullability

import com.learn.kotlininaction.chap5.lambdaexpression.alphabetMoreConcise

/*
* 6.1.1 Nullable types
* */

fun textLength(text: String) = text.length

fun textLengthSafe(text: String?) = if (text!= null) text.length else 0

fun textLengthSafeOperator(text: String?): Int? = text?.length

fun textLengthElvisOperator(text: String?): Int = text?.length ?: 0

fun printAllCaps(text: String?){
    val result: String? = text?.toUpperCase()
    //equivalent to text?.toUpperCase()
    //val result = if(text != null) text.toUpperCase() else null
    println(result)
}

class Employee(val name: String, val manager: Employee?)

fun managerName(employee: Employee) = employee.manager?.name

class Address(val streetAddress: String, val zipCode: Int,
              val city: String, val country: String)

class Company(val name: String, val address: Address?)

class Person(val name: String, val company: Company?)

class Student(val firstName: String, val lastName: String){

    override fun equals(other: Any?): Boolean {
        val otherStudent = (other as? Student) ?: return false
        return firstName == otherStudent.firstName &&
                lastName == otherStudent.lastName
    }

    override fun hashCode() = firstName.hashCode() * 37 + lastName.hashCode()
}

fun Person.countryName(person: Person): String{
    val countryName = person.company?.address?.country
    return if (countryName == null) "Unknown" else countryName
}

fun Person.countryNameElvis(person: Person): String{
    val countryName = person.company?.address?.country
    return countryName ?: "Unknown"
}

fun rightSideOfElvisOperator(text: String?): Int?{
    val result = text?.length ?: return null
    return result
}

fun printShippingAddress(person: Person){
    val address: Address = person.company?.address ?: throw IllegalArgumentException("No Address")
    with(address){
        println(streetAddress)
        println("$zipCode $city, $country")
    }
}

fun ignoreNull(text: String?){
    val textNotNull: String = text!!
}

fun sendEmail(email: String){
    println("email to $email send")
}

private fun doSendEmail(){
    val email: String? = null;
    if(email != null){
        sendEmail(email)
    }
}

private fun doSendEmailLet(){
    val email: String? = null
    email?.let { sendEmail(it) }
}

class TestSubject{
    fun testMethod(){

    }
}

class MyTest{

    private lateinit var testSubject: TestSubject

    fun setUp(){
        testSubject = TestSubject()
    }

    fun test(){
        testSubject.testMethod()
    }
}

fun verifyUserInput(input: String?){
    if(input.isNullOrBlank()){
        println("Please fill in the required filed.")
    }
}

fun String?.isNullOrBlankCustom(): Boolean{
    return this == null || this.isBlank()
}

fun <T> printHashCode(t: T){
    /*
    * compile error
    * println(t.hashCode())
    * */
    println(t?.hashCode())
}

fun <T: Any> printHashCodeNonNull(t: T){
    println(t.hashCode())
}

fun main(args: Array<String>) {
    println(rightSideOfElvisOperator(null))
}
