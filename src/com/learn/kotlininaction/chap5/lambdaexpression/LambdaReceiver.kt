package com.learn.kotlininaction.chap5.lambdaexpression


/*
* 5.5 Lambdas with receivers: with and apply
* */
fun alphabet(): String{
    val result = StringBuilder()
    for(letter in 'A'..'Z'){
        result.append(letter)
    }
    result.append("\nNow I know the alphabet!")
    return result.toString()
}

fun alphabetWithReceiver(): String{
    val result = StringBuilder()
    return with(result){
        for(letter in 'A'..'Z'){
            this.append(letter)
        }
        append("\nNow I know the alphabet!")
        toString()
    }
}

fun alphabetMoreConcise() = with(StringBuilder()){
    for(letter in 'A'..'Z'){
        this.append(letter)
    }
    append("\nNow I know the alphabet!")
    toString()
}

fun alphabetApply():String = StringBuilder().apply {
    for(letter in 'A'..'Z'){
        this.append(letter)
    }
    append("\nNow I know the alphabet!")
}.toString()

fun main(args: Array<String>) {
    println(alphabet())
}
