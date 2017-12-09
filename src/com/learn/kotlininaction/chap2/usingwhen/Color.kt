package com.learn.kotlininaction.chap2.usingwhen

enum class Color(val red: Int, val green: Int, val blue: Int) {
    RED(255, 0, 0), ORANGE(255, 165, 0),
    YELLOW(255, 255, 0), GREEN(0, 255, 0),
    BLUE(0, 0, 255), INDIGO(75, 0, 130), VIOLET(238, 130, 238);

    fun rgb() = (red * 256 + green) * 256 + blue
    fun rgbA(): Int = (red * 256 + green) * 256 + blue
    fun rgbB(): Int {
        return (red * 256 + green) * 256 + blue
    }
}

fun getMnemonic(color: Color) = when (color) {
    Color.RED -> "Richard"
    Color.ORANGE -> "Of"
    Color.YELLOW -> "York"
    Color.GREEN -> "Gave"
    Color.BLUE -> "Battle"
    Color.INDIGO -> "In"
    Color.VIOLET -> "Vain"
}

fun getWarmth(color: Color) = when (color) {
    Color.RED, Color.ORANGE, Color.YELLOW -> "warm"
    Color.GREEN -> "neutral"
    Color.BLUE, Color.INDIGO, Color.VIOLET -> "cold"
}

fun getWarmthNoArgument(color: Color) = when{
    color == Color.RED || color == Color.ORANGE || color == Color.YELLOW -> "warm"
    color == Color.GREEN -> "neutral"
    color == Color.BLUE || color == Color.INDIGO || color == Color.VIOLET -> "cold"
    else -> "plain"
}

fun mix(color: Color, anotherColor: Color) =
        when (setOf(color, anotherColor)) {
            setOf(Color.RED, Color.YELLOW) -> Color.YELLOW
            setOf(Color.YELLOW, Color.BLUE) -> Color.GREEN
            setOf(Color.BLUE, Color.VIOLET) -> Color.INDIGO
            else -> throw Exception("Dirty Color")
        }

fun main(args: Array<String>) {
    val mnemonic = getMnemonic(Color.VIOLET)
    println(mnemonic)
    val warmth = getWarmth(Color.BLUE)
    println(warmth)
}
