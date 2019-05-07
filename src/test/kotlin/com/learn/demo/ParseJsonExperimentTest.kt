package com.learn.demo

import com.google.gson.GsonBuilder
import com.learn.demo.json.Order
import com.learn.demo.json.OrderRaw
import org.junit.Assert
import org.junit.Test

class ParseJsonExperimentTest {

    private companion object {
        private const val JSON_EXAMPLE: String =
                """{"id":"123232",
                    "total":199.9,
                    "products":[{"id":"a1","name":"apple", "price":100, "description":null},{"id":"a2","name":"bread", "price":99.9, "description": "top good"}],
                    "memberId":null,
                    "payment":"cash",
                    "note":null
                    }"""
    }

    @Test
    fun test_parseOrder() {
        val gson = GsonBuilder().create()
        val order: Order = gson.fromJson(JSON_EXAMPLE, Order::class.java)
        Assert.assertEquals(2, order.products.size)
        println(order.id)
//        Assert.assertFalse(order.isMember)
    }

    @Test
    fun test_parseOrder_useRaw() {
        val gson = GsonBuilder().create()
        val orderRaw: OrderRaw = gson.fromJson(JSON_EXAMPLE, OrderRaw::class.java)
        val order = Order(orderRaw)
        Assert.assertEquals(2, order.products.size)
        println(order.id)
//        Assert.assertFalse(order.isMember)
    }

}
