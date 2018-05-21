package com.learn.kotlininaction.chap4.classhierarchy

/*
* 4.1.1 Interfaces in Kotlin: methods with default implementations
* 4.1.2 Open, final, and abstract modifiers: final by default
* 4.1.3 Visibility modifiers: public by default
* */

interface Clickable{
    fun click()

    fun showOff() = println("I am clickable.")
}

interface Focusable{
    fun setFocus(isFocused: Boolean) = println("I ${if(isFocused) "get" else "lose"} focus.")

    fun showOff() = println("I am focusable.")
}

class Button : Clickable, Focusable{
    override fun click() {
        println("I was clicked")
    }

    override fun showOff() {
        super<Clickable>.showOff()
        super<Focusable>.showOff()
    }

//    override fun showOff() = super<Clickable>.showOff()
}


fun main(args: Array<String>) {
    val button = Button()
    button.click()
    button.showOff()
    button.setFocus(true)
}
