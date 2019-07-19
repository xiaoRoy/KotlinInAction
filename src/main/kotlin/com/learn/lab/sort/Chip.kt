package com.learn.lab.sort

class Chip(val name: String, var order: Int) : Comparable<Chip>{
    override fun compareTo(other: Chip): Int {
       return order.compareTo(other.order)
    }

    fun addOffset() {
        order -= OFFSET;
    }

    companion object {
        const val OFFSET = 3
        const val ADD_GIFT = "Add Gift"
        const val CHOOSE_STORE = "Choose Store"
        const val CREATE_CASH_FUND = "Create Cash Fund"
        val chipList = listOf(
                Chip(ADD_GIFT, 0), Chip(CHOOSE_STORE, 1), Chip(CREATE_CASH_FUND, 2)
        )
    }

}