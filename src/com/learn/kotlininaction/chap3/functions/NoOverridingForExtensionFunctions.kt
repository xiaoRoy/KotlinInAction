package com.learn.kotlininaction.chap3.functions


/*
* 3.3.4 No overriding for extension functions
* */
open class View {
    open fun click() = println("View Clicked")
}

class Button : View(){
    override fun click() {
        println("Button Click")
    }
}

fun View.showOff() {
    println("View showOff()")
}

fun Button.showOff() = println("Button showOff()")

fun main(args: Array<String>) {
    val view: View = Button()
    view.click()
    view.showOff()
}
