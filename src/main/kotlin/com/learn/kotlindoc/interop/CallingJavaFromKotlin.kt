package com.learn.kotlindoc.interop

import java.util.*

/*
* Getters and Setters
* */


fun calendarDemo() {
    val calendar = Calendar.getInstance()
    if (calendar.firstDayOfWeek == Calendar.SUNDAY) {
        calendar.firstDayOfWeek == Calendar.MONDAY
    }
    if (!calendar.isLenient) {
        calendar.isLenient = true
    }
}
