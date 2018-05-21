package com.learn.kotlininaction.chap4.classhierarchy

import java.io.ByteArrayOutputStream
import java.io.ObjectOutputStream
import java.io.Serializable

interface State : Serializable

interface View {
    fun getCurrentState(): State
    fun restoreState(state: State)
}

class InnerButton : View {
    override fun getCurrentState() = InnerButtonState()

    override fun restoreState(state: State) {
    }

    inner class InnerButtonState : State
}


class OuterClass{
    inner class InnerClass{
        fun getOuter(): OuterClass = this@OuterClass
    }
}

fun main(args: Array<String>) {
    var objectOutputStream: ObjectOutputStream? = null
    val innerButton = InnerButton()
    objectOutputStream = ObjectOutputStream(ByteArrayOutputStream())
    objectOutputStream.writeObject(innerButton.getCurrentState())
}
