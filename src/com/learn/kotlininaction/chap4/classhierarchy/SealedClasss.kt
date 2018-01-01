package com.learn.kotlininaction.chap4.classhierarchy

/*
* 4.1.5 Sealed classes: defining restricted class hierarchies
* */

interface Expr

class Num(val value: Int) : Expr

class Sum(val left: Expr, val right: Expr) : Expr


fun eval(expr: Expr): Int = when(expr){
    is Num -> expr.value
    is Sum -> eval(expr.left) + eval(expr.right)
    else -> throw IllegalArgumentException("Unknown Expression")
}


sealed class ExprSealed{
    class NumSealed(val value: Int) : ExprSealed()

    class SumSealed(val left: ExprSealed, val right: ExprSealed) : ExprSealed()

//    class MulSealed(val left: ExprSealed, val right: ExprSealed) : ExprSealed()
}

fun evalSealed(expr: ExprSealed): Int = when(expr){
    is ExprSealed.NumSealed -> expr.value
    is ExprSealed.SumSealed -> evalSealed(expr.left) + evalSealed(expr.right)
}
