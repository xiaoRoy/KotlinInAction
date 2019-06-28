package com.learn.test.mockk

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

import org.junit.Assert.*

class CarTest {

    @Test
    fun test_start() {
        val engine = mockk<Engine>()
        every { engine.active() } returns Power()
        val car = Car(engine)
        car.star()
        verify(exactly = 1) { engine.active() }
    }
}