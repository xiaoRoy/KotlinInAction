package com.learn.kotlininaction.chap4.createinstance

/*
* 4.4.4 Object expressions: anonymous inner classes rephrased
* */
class View{

    fun performClick(onClickListener: OnClickListener){
        onClickListener.onClicked(this)
    }

}

open class SingleOnClickListener : OnClickListener{
    override fun onClicked(view: View) {
    }
}

interface OnClickListener {
    fun onClicked(view: View)
}

fun main(args: Array<String>) {
    val viewA = View()
    viewA.performClick(object : OnClickListener{
        override fun onClicked(view: View) {
        }
    })

    var clickCount = 0
    val viewB = View()
    viewB.performClick(object : SingleOnClickListener(){
        override fun onClicked(view: View) {
            clickCount ++
            super.onClicked(view)
        }
    })
}
