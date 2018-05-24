package com.learn.tutorial.unittest.business

import com.learn.tutorial.unittest.bean.Coordinate
import org.junit.Before
import org.mockito.Mock

import com.learn.tutorial.unittest.business.TemperatureContract.LocationProvider
import com.learn.tutorial.unittest.business.TemperatureContract.TemperatureProvider
import com.learn.tutorial.unittest.business.TemperatureContract.ViewRenderer
import org.junit.Test
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class TemperaturePresenterTest {

    @Mock
    private lateinit var locationProvider: LocationProvider

    @Mock
    private lateinit var temperatureProvider: TemperatureProvider

    @Mock
    private lateinit var viewRenderer: ViewRenderer

    private lateinit var temperaturePresenter: TemperaturePresenter

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        temperaturePresenter = TemperaturePresenter(locationProvider, temperatureProvider, viewRenderer)
    }

    @Test
    fun test_show_temperature() {
        val lastKnownLocation = Coordinate(112.1, 89.2)
        val exactLocation = Coordinate(122.1, 90.2)

        Mockito.`when`(locationProvider.getLastKnownLocation()).thenReturn(lastKnownLocation)
        Mockito.`when`(temperatureProvider.getCelsiusTemperatureAt(lastKnownLocation)).thenReturn(28.0f)

        Mockito.`when`(locationProvider.getExactLocation()).thenReturn(exactLocation)
        Mockito.`when`(temperatureProvider.getCelsiusTemperatureAt(exactLocation)).thenReturn(19f)

        temperaturePresenter.viewTemperature()

        val inOrder = Mockito.inOrder(temperatureProvider, viewRenderer)
        inOrder.verify(temperatureProvider).getCelsiusTemperatureAt(lastKnownLocation)
        inOrder.verify(viewRenderer).displayTemperature(28.0f)

        inOrder.verify(temperatureProvider).getCelsiusTemperatureAt(exactLocation)
        inOrder.verify(viewRenderer).displayTemperature(19f)
    }

    @Test
    fun test_show_temperature_improved() {
        val lastKnownLocation = Coordinate(112.1, 89.2)
        val exactLocation = Coordinate(122.1, 90.2)

        Mockito.`when`(locationProvider.getLastKnownLocation()).thenReturn(lastKnownLocation)
        Mockito.`when`(temperatureProvider.getCelsiusTemperatureAt(lastKnownLocation)).thenReturn(28.0f)

        Mockito.`when`(locationProvider.getExactLocation()).thenReturn(exactLocation)
        Mockito.`when`(temperatureProvider.getCelsiusTemperatureAt(exactLocation)).thenReturn(19f)

        temperaturePresenter.viewTemperature()

        Mockito.inOrder(temperatureProvider, viewRenderer).apply {
            verify(temperatureProvider).getCelsiusTemperatureAt(lastKnownLocation)
            verify(viewRenderer).displayTemperature(28.0f)

            verify(temperatureProvider).getCelsiusTemperatureAt(exactLocation)
            verify(viewRenderer).displayTemperature(19f)
        }

    }
}
