package com.learn.playground.collection

class Vehicle(val price: Double) {

    companion object {
        val VEHICLE_PRICE_LOW = "LOW"
        val VEHICLE_PRICE_MEDIUM = "MEDIUM"
        val VEHICLE_PRICE_HIGH = "HIGH"
        val VEHICLE_PRICE_UNDEFINED= "undefined"

        fun generateVehicleList(): MutableList<Vehicle> = mutableListOf(
                Vehicle(1200.0), Vehicle(450.0), Vehicle(100.0), Vehicle(0.0)
        )
    }
}