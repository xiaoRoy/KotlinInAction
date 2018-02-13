package com.learn.kotlininaction.chap5.lambdaexpression

import java.util.concurrent.Callable

fun runTask(){
    UseJavaFunctionalInterface.runTask { -> println("running task") }
}

val runnable = Runnable { println("running task")}

fun runTaskEquivalent(){
    UseJavaFunctionalInterface.runTask(runnable)
}

fun runTaskCaptureOutside(taskName: String){
    UseJavaFunctionalInterface.runTask{ println("running task $taskName")}
}

fun runTaskAnonymous(){
    UseJavaFunctionalInterface.runTask(object : Runnable{
        override fun run() {
            println("running task")
        }
    })
}

fun createAllDoneRunnable(): Runnable{
    //compile error, you can not return a lambda
    //return { -> println("All Done")}
    return Runnable { -> println("All Done")}
}

fun main(args: Array<String>) {
    runTask()
    runTaskAnonymous()
}
