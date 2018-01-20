package com.learn.kotlininaction.chap4.createinstance

class Client(val name: String, val postalCode: Int){
    override fun toString() = "Client(name=$name, postalCode=$postalCode)"

    override fun equals(other: Any?): Boolean {
        if(other == null || other !is Client){
            return false
        }
        return name == other.name && postalCode == other.postalCode
    }

    override fun hashCode(): Int = name.hashCode() * 31 + postalCode
}


fun main(args: Array<String>) {
    val clientAlex = Client("Alex", 523535)
    val clientSet = hashSetOf(clientAlex)
    //SingletonSet
    setOf("A")
    //not singleSet
    setOf("A", "B")
    //so is true no matter it is override hashCode()
    val isSingleSetIncluded = setOf(clientAlex).contains(Client("Alex", 523535))
    println("isSingleSetIncluded$isSingleSetIncluded")
    //false if the hashCode() is override
    val isIncluded = clientSet.contains(Client("Alex", 523535))
    println("result:$isIncluded")
}
