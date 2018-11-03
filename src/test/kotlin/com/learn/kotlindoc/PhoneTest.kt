package com.learn.kotlindoc

import com.learn.kotlindoc.model.PhoneJ
import org.junit.Test
import kotlin.test.assertFalse

class PhoneTest {

    @Test
    fun test_phone_brand() {
        val phone = PhoneJ()
        phone.brand = "apple"
        assert(phone.brand == "apple")
    }

    @Test
    fun test_phone_full_screen() {
        val phone = PhoneJ()
        assertFalse { phone.isFullScreen }
    }
}
