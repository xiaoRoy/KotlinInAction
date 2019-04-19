package com.learn.lab.strings

import org.junit.Assert
import org.junit.Test

class TestStrings {

    @Test
    fun test_isBlank() {
        Assert.assertTrue("".isBlank())
        Assert.assertTrue(" ".isBlank())
    }

    @Test
    fun test_isEmpty() {
        if(null is String) {

        } else {
            print("null is not string")
        }
        Assert.assertTrue("".isEmpty())
        Assert.assertFalse(" ".isEmpty())
    }
}