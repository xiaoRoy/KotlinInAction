package com.learn.kotlininaction.chap7

import com.learn.kotlininaction.chap6.nullability.personName
import java.beans.PropertyChangeListener
import java.beans.PropertyChangeSupport
import kotlin.properties.Delegates
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

open class PropertyChangeAware{
    protected val changeSupport = PropertyChangeSupport(this)

    fun addPropertyChangeListener(listener: PropertyChangeListener){
        //sam conversion, passing the lambda to java
//        changeSupport.addPropertyChangeListener{ print(it.propertyName)}
        changeSupport.addPropertyChangeListener(listener)
    }

    fun removePropertyChangeListener(listener: PropertyChangeListener){
        changeSupport.removePropertyChangeListener(listener)
    }

}

class PersonAware(val name: String, age :Int, salary: Int) : PropertyChangeAware() {
    var age = age
    set(value) {
        val oldValue = field
        field = value
        changeSupport.firePropertyChange("age", oldValue, value)
    }

    var salary = salary
    set(value) {
        val oldValue = field
        field = value
        changeSupport.firePropertyChange("salary", oldValue, value)
    }
}


private val propertyChangeListener = PropertyChangeListener { println("Property ${it.propertyName} changed") }

fun runPersonAware(){
    val person = PersonAware("Jack", 23, 19000)
    person.addPropertyChangeListener(propertyChangeListener)
    person.age++
}

class PersonObservableProperty(val name: String, age: Int, salary: Int) : PropertyChangeAware() {

    var _age = ObservableProperty("age", age, changeSupport)
    var age: Int
        get() = _age.getValue()
        set(value) {
            _age.setValue(value)
        }

    var _salary = ObservableProperty("salary", salary, changeSupport)
    var salary: Int
        get() = _salary.getValue()
        set(value) {
            _salary.setValue(value)
        }
}

class ObservableProperty(val propertyName: String, var propertyValue: Int,
                         val propertyChangeSupport: PropertyChangeSupport){
    fun getValue(): Int = propertyValue

    fun setValue(newValue: Int){
        val oldValue = propertyValue
        propertyValue = newValue
        propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue)
    }
}

fun runPersonObservableProperty(){
    val person = PersonObservableProperty("Smith", 12, 0)
    person.addPropertyChangeListener(propertyChangeListener)
    person.age ++
}

class PersonObservablePropertyByConvention(val name: String, age :Int, salary: Int) : PropertyChangeAware() {
    var age: Int by ObservablePropertyByConvention(age, changeSupport)
    var salary: Int by ObservablePropertyByConvention(salary, changeSupport)
}

class ObservablePropertyByConvention(var propertyValue: Int, val propertyChangeSupport: PropertyChangeSupport){

    operator fun getValue(person: PersonObservablePropertyByConvention, property: KProperty<*>) = propertyValue

    operator fun setValue(person: PersonObservablePropertyByConvention, property: KProperty<*>, newValue: Int){
        val oldValue = propertyValue
        propertyValue = newValue
        propertyChangeSupport.firePropertyChange(property.name, oldValue, newValue)
    }
}

fun runPersonObservablePropertyByConvention(){
    val person = PersonObservablePropertyByConvention("Lily", 22, 2300)
    person.addPropertyChangeListener(propertyChangeListener)
    person.age ++
}


class PersonFinalVersion(val name: String, age :Int, salary: Int) : PropertyChangeAware(){

    private val observer = {
        property: KProperty<*>, oldValue: Int, newValue: Int
            -> changeSupport.firePropertyChange(property.name, oldValue, newValue)
    }

    var age: Int by Delegates.observable(age, observer)
    var salary: Int by Delegates.observable(salary, observer)
}

fun runPersonFinalVersion(){
    val person = PersonFinalVersion("Roy", 32, 1900)
    person.age ++
}

fun main(args: Array<String>) {
    runPersonAware()
}

