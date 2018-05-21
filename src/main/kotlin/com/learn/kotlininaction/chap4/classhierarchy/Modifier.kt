package com.learn.kotlininaction.chap4.classhierarchy

open class RichButton : Clickable{

    fun disable(){}

    open fun animate(){}

    override fun click() {
    }
}

abstract class Animated{

    abstract fun animate()

    open fun stopAnimate(){}

    fun animateTwice(){

    }
}

internal open class TalkativeButton{
    private fun yell() = println("Hey!")

    protected fun whisper() = println("Let's talk!")
}

/**
 * 'public' member exposes its 'internal' receiver type TalkativeButton
 * fun TalkativeButton.giveSpeech(){
 * }
 */
internal fun TalkativeButton.giveSpeech(){
    /*
    * cannot access 'yell': it is 'private' in 'TalkativeButton'
    * this.yell()
    * */

    /*
    * cannot access 'whisper': it is 'protected' in 'TalkativeButton'
    * this.whisper()
    * */
}

