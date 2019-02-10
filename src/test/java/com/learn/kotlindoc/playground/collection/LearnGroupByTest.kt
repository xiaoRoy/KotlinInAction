package com.learn.kotlindoc.playground.collection

import com.learn.playground.collection.Vehicle
import org.junit.Assert
import org.junit.Test

class LearnGroupByTest {

    @Test
    fun test_groupByTo() {
        val vehicleList = Vehicle.generateVehicleList()
        val result = mutableMapOf<String, MutableList<Vehicle>>()
        vehicleList.groupByTo(result) {
            val price = it.price
            when {
                price > 0.0 && price < 100.0 -> Vehicle.VEHICLE_PRICE_LOW
                price >= 100.0 && price < 500.0 -> Vehicle.VEHICLE_PRICE_MEDIUM
                price >= 500.0 -> Vehicle.VEHICLE_PRICE_HIGH
                else -> Vehicle.VEHICLE_PRICE_UNDEFINED
            }
        }
        Assert.assertEquals(3, result.size)
    }
}