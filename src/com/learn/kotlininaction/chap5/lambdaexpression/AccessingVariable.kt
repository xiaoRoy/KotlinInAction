package com.learn.kotlininaction.chap5.lambdaexpression


/*
*5.1.4 Accessing variables in scope
* */

fun printMessageWithPrefix(messages: Collection<String>, prefix: String){
    messages.forEach { println("$prefix $it")}
}


fun printProblemCounts(responses: Collection<String>){
    var clientErrors = 0;
    var serverErrors = 0;
    responses.forEach {
        if(it.startsWith("4")){
            clientErrors ++
        } else if(it.startsWith("3")){
            serverErrors ++
        }
    }
    println("$clientErrors client errors, $serverErrors server error")
}
