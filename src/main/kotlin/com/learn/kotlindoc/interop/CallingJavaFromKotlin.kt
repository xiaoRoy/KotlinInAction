package com.learn.kotlindoc.interop

import com.learn.kotlindoc.model.PhoneJ
import java.util.*
import kotlin.collections.ArrayList

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

/*
* Null-Safety and Platform Types
* */
fun platformTypeDemo() {
    val list = ArrayList<String>()
    list.add("first")
    val size = list.size
    val item: String = list[0]
    val anotherItem: String? = list[0]

    val phone = PhoneJ()
    val brandModelA = phone.brandModel
    val brandModelB: String = phone.brandModel
    val brandModelC: String? = phone.brandModel
}
