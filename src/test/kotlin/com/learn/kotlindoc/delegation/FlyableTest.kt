package com.learn.kotlindoc.delegation

import org.junit.Assert
import org.junit.Test

class FlyableTest {

    @Test
    fun testSwallow_fly() {
        val swallow = Swallow(Bird("Swallow"))
        Assert.assertEquals("Most of us could fly", swallow.fly())
    }

    @Test
    fun testSwallow_anotherWayToFly() {
        val swallow = Swallow(Bird("Swallow"))
        Assert.assertEquals("Most of us could fly", swallow.fly())
        Assert.assertEquals("We could fly very fast", swallow.anotherWayToFly())
    }

    @Test
    fun testSwallow_speed() {
        val swallow = Swallow(Bird("Swallow"))
        Assert.assertEquals("fast", swallow.speed)
    }

    @Test
    fun testSwallow_showSpeed() {
        val swallow = Swallow(Bird("Swallow"))
        Assert.assertNotEquals("My speed is fast", swallow.showSpeed())
        Assert.assertEquals("My speed is medium", swallow.showSpeed())
    }
}
