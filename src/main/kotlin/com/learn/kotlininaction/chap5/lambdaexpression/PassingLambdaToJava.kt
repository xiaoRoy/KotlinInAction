package com.learn.kotlininaction.chap5.lambdaexpression

import java.util.function.Predicate

fun runTask() {
    UseJavaFunctionalInterface.runTask { -> println("running task") }
    testRunnable(Runnable { println() })
}


/*
* it seems that if you pass the lambda to java, it triggers the sam conventions.
* but if you invoke the method has a functional interface, you need to use
* SAM constructor to make explicit conversion of lambda to functional interface.
* */
fun testRunnable(runnable: Runnable) {
    runnable.run()
}

val runnable = Runnable { println("running task") }

fun runTaskEquivalent() {
    UseJavaFunctionalInterface.runTask(runnable)
}

fun runTaskCaptureOutside(taskName: String) {
    UseJavaFunctionalInterface.runTask { println("running task $taskName") }
}

fun runTaskAnonymous() {
    UseJavaFunctionalInterface.runTask(object : Runnable {
        override fun run() {
            println("running task")
        }
    })
}

fun runTaskWithLocalRunnable() {
    // val runnable: () -> Unit = { println("local Running task") }
    val runnable = { println("local Running task") }
    UseJavaFunctionalInterface.runTask(runnable)
}

fun createAllDoneRunnable(): Runnable {
    //compile error, you can not return a lambda
    //return { -> println("All Done")}
    return Runnable { println("All Done") }
}

fun filterLagerThanThree() {
    val filter = Predicate<Int> { number -> number > 3 }
    val filterB = Predicate { number: Int -> number > 3 }
    //if a function literal has only one parameter, its declaration may be omitted (along with the -
    //>),and its name will be it(kotlin doc p98)
    val filterC = Predicate<Int> { it > 3 }
    //if the lambda parameter is unused, you can place an underscore instead of its name(kotlin doc p98)
    val filterD = Predicate<Int> { _ -> true }
    //we could omit it if we don't use it. but not recommended
    val filterE = Predicate<Int> { true }

    UseJavaFunctionalInterface.filer(12, filter)
    UseJavaFunctionalInterface.filer(11, filterB)
    UseJavaFunctionalInterface.filer(11) {
        true
    }
}

fun main(args: Array<String>) {
    runTask()
    runTaskAnonymous()
}
