package com.learn.tutorial.unittest.business

import com.learn.tutorial.unittest.bean.Coordinate

interface TemperatureContract {

    interface LocationProvider{

        fun getLastKnownLocation(): Coordinate

        fun getExactLocation(): Coordinate
    }

    interface TemperatureProvider{

        fun getCelsiusTemperatureAt(coordinate: Coordinate): Float

    }

    interface ViewRenderer{

        fun displayTemperature(temperature: Float)

    }

    interface Presenter{

        fun viewTemperature()
    }
}
