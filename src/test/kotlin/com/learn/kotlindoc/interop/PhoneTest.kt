package com.learn.kotlindoc.interop

import com.learn.kotlindoc.model.PhoneJ
import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

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

    @Test
    fun test_phone_check() {
        val phone = PhoneJ()
        val what = phone.check()
        assertTrue { what.javaClass.typeName == "kotlin.Unit" }
    }

    @Test
    fun test_phone_size_int_array() {
        val phone = PhoneJ()
        assertTrue { phone.size is IntArray }
    }

    @Test
    fun test_phone_what_string_array() {
        val phone = PhoneJ()
        assertTrue { phone.what is Array<String> }
    }

    @Test
    fun test_collection_mapped_type() {
        val phone = PhoneJ()
        assertTrue { phone.colors is Set<String> }
        assertTrue { phone.colors is MutableSet<String> }
    }

    @Test
    fun test_collection_mapped_type_set() {
        val phone = PhoneJ()
        //can not add item since it is kotlin.collections.Set(immutable)
        val colors: Set<String> = phone.colors
        assertTrue { colors.isEmpty() }
    }

    @Test
    fun test_collection_mapped_type_mutableSet() {
        val phone = PhoneJ()
        val colors: MutableSet<String> = phone.colors
        colors.add("red")
        assertTrue { colors.isNotEmpty() }
    }
}
