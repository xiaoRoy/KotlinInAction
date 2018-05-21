package com.learn.kotlininaction.chap2.usingwhen


interface Expression

class Number(val value: Int) : Expression

class Sum(val left: Expression, val right: Expression) : Expression

fun evalWithJavaStyle(expression: Expression): Int {
    if (expression is Number) {
        //not smart cast
        val number = expression as Number
        return number.value
    }
    if (expression is Sum) {
        //smart cast
        return evalWithJavaStyle(expression.left) + evalWithJavaStyle(expression.right)
    }
    throw IllegalArgumentException("Unknown Expression")
}

fun eval(expression: Expression): Int =
        when (expression) {
            is Number ->
                expression.value
            is Sum ->
                eval(expression.left) + eval(expression.right)
            else -> throw IllegalArgumentException("Unknown Expression")
        }

fun evalBlockWithJavaStyle(expression: Expression): Int {
    //always throws the exception, block body always needs a explicit return or throw
    if (expression is Number) {
        val number = expression as Number
        number.value
    }
    if (expression is Sum) {
        evalBlockWithJavaStyle(expression.left) + evalBlockWithJavaStyle(expression.right)
    }
    throw IllegalArgumentException("Unknown Expression")
}

fun evalBlock(expression: Expression): Int =
        when (expression) {
            is Number -> {
                println("number ${expression.value}")
                expression.value
            }
            is Sum -> {
                val left = evalBlock(expression.left)
                val right: Int = evalBlock(expression.right)
                println("Sum: $left + $right")
                left + right
            }
            else -> throw IllegalArgumentException("Unknown Expression")
        }

fun blockBody(numberA: Int, numberB: Int): Int {
    return numberA + numberB
}

fun expressionBodyA(numberA: Int, numberB: Int): Int =
        numberA + numberB

fun expressionBodyB(numberA: Int, numberB: Int) =
        numberA + numberB

/*
fun notExpressionBody(numberA: Int, numberB: Int): Int =
    println()
    numberA + numberB
}
*/

fun main(args: Array<String>) {
    evalWithJavaStyle(Sum(Number(1), Number(1)))
}

fun testLastExpressionInBlockIsTheResult(): Int {
    //"if" must have both main and 'else' branch when use as an expression
    return if (true) {
        1
    } else {
        2
    }
}
