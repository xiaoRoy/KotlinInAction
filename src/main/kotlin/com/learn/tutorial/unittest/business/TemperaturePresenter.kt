package com.learn.tutorial.unittest.business

import com.learn.tutorial.unittest.business.TemperatureContract.LocationProvider
import com.learn.tutorial.unittest.business.TemperatureContract.TemperatureProvider
import com.learn.tutorial.unittest.business.TemperatureContract.ViewRenderer

class TemperaturePresenter(
        private val locationProvider: LocationProvider,
        private val temperatureProvider: TemperatureProvider,
        private val viewRenderer: ViewRenderer): TemperatureContract.Presenter {

    override fun viewTemperature() {
        val lastLocation = locationProvider.getLastKnownLocation()
        val inaccurateTemperature = temperatureProvider.getCelsiusTemperatureAt(lastLocation)
        viewRenderer.displayTemperature(inaccurateTemperature)

        val exactLocation = locationProvider.getExactLocation()
        val accurateTemperature = temperatureProvider.getCelsiusTemperatureAt(exactLocation)
        viewRenderer.displayTemperature(accurateTemperature)
    }
}
