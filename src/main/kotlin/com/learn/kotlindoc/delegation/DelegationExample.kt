package com.learn.kotlindoc.delegation

class StringDelegate {

    var value: String = ""

    fun setStringValue(value: String) {
        this.value = value
    }

    fun getStringValue() = value
}


class Ticket(
        price: Double,
        private var _statement: String? = null
) {
    private val stringDelegate: StringDelegate = StringDelegate()

    var destination: String
        set(value) {
            stringDelegate.value = value
        }
        get() = stringDelegate.value

    var statement: String
        set(value) {
            _statement = value
        }
        get() {
            return _statement ?: ""
        }

    var price: Double = price
        set(value) {
            field = if (value < 0.0) {
                0.0
            } else {
                value
            }
        }
        get() = field
}
