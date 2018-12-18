package com.learn.kotlindoc.playground.date

import org.junit.Assert
import org.junit.Test
import java.time.Duration
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

class DateTest {

    @Test
    fun test_daysBetween_Duration() {
        val result = Duration.between(LocalDateTime.now(), LocalDateTime.now().plusDays(1).minusHours(2)).toDays()
        Assert.assertEquals(0, result)
    }

    @Test
    fun test_daysBetween_ChronoUnit() {
        val result= ChronoUnit.DAYS.between(LocalDateTime.now(), LocalDateTime.now().plusDays(1).minusHours(2))
        Assert.assertEquals(0, result)
    }
}