package com.learn.kotlininaction.chap8.higherorderfunction

import java.lang.StringBuilder

fun functionType(){
    val sum: (Int, Int) -> Int = {x, y -> x + y}
    val action: () -> Unit = { println()}
    val returnNull: (Int, Int) -> Int? = {x, y -> null}
    val nullFunctionType: ((String) -> Boolean)? = null
    val functionTypeWithParameterName: (text: String) -> Boolean = {text -> text.isEmpty()}
    val functionTypeWithParameterNameAndChangeIt: (text: String) -> Boolean = { string -> string.isEmpty()}
}


fun twoAndThree(operation: (Int, Int) -> Int){
    val result = operation(2, 3)
    println(result)
}

fun runTwoAndThree(){
    twoAndThree {x, y -> x + y}
    twoAndThree {x, y -> x * y}
}

fun String.filter(predicate: (Char) -> Boolean): String{
    val stringBuilder = StringBuilder()
    for(index in 0 until length){
        val element = get(index)
        if(predicate(element)){
            stringBuilder.append(element)
        }
    }
    return stringBuilder.toString()
}

fun <T> Collection<T>.joinToString(separator: String = ", ", prefix: String = "", postfix: String = "",
                                   transform:(T) -> String = {it.toString()}): String{
    val result = StringBuilder(prefix)
    for((index, element) in withIndex()){
        if(index > 0){
            result.append(separator)
        }
        result.append(transform(element))
    }
    result.append(postfix)
    return result.toString()
}

fun <T> Collection<T>.joinToStringNull(separator: String = ", ", prefix: String = "", postfix: String = "",
                                   transform:((T) -> String)? = null): String{
    val result = StringBuilder(prefix)
    for((index, element) in withIndex()){
        if(index > 0){
            result.append(separator)
        }
        if(transform != null){
            result.append(transform(element))
        } else {
            result.append(element.toString())
        }
    }
    result.append(postfix)
    return result.toString()
}

fun <T> Collection<T>.joinToStringNullFunction(separator: String = ", ", prefix: String = "", postfix: String = "",
                                       transform:((T) -> String)? = null): String{
    val result = StringBuilder(prefix)
    for((index, element) in withIndex()){
        if(index > 0){
            result.append(separator)
        }
        val temp = transform?.invoke(element) ?: element.toString()
        result.append(temp)
    }
    result.append(postfix)
    return result.toString()
}

enum class Delivery{STANDARD, EXPEDITED}

class Order(val itemCount: Int)

fun getShippingCostCalculator(delivery: Delivery):(Order) -> Double {
    var result = {order: Order -> 1.2 * order.itemCount}
    if(delivery == Delivery.EXPEDITED){
        result = {order: Order ->  6 + 2.1 * order.itemCount }
    }
    return result
}

data class Contact constructor(val firstName: String, val lastName: String, val phoneNumber: String?)

class ContactListFilter{
    var prefix: String = ""
    var onlyWithNumber: Boolean = false

    fun getPredicate(): (Contact) -> Boolean{
        val startWithPrefix = {
            contact: Contact -> contact.firstName.startsWith(prefix)
                                && contact.lastName.startsWith(prefix)
        }
        if(!onlyWithNumber){
            return startWithPrefix
        }
        return {startWithPrefix(it) && it.phoneNumber != null}
    }
}

fun filterContact(){
    val contacts = listOf(Contact("Jack", "Blue", "123"),
                            Contact("Bill", "Kyle", null),
                            Contact("Ali", "Kate", "987"))
    val filter = ContactListFilter()
    with(filter){
        prefix = "b"
        onlyWithNumber = true
    }
    println(contacts.filter(filter.getPredicate()))
}

fun main(args: Array<String>) {
    val list = listOf("Alpha", "Beta")
    println(list.joinToString())
    println(list.joinToString{it.toUpperCase()})
    println(list.joinToString(transform = {it.toLowerCase()}))
}
