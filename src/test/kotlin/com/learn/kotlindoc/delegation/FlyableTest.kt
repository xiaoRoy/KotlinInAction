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
}
