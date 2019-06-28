package com.learn.palyground.collection

import com.learn.playground.collection.IndexOfField
import org.junit.Assert.assertEquals
import org.junit.Test

class IndexOfFieldTest {

    @Test
    fun test_indexOf() {
        val registryGifts = mutableListOf<IndexOfField.RegistryGift>()
        val registryGift = IndexOfField.RegistryGift("123")
        val cashFundRegistryGift = IndexOfField.CashFundRegistryGift("111", "669")
        registryGifts.add(registryGift)
        registryGifts.add(cashFundRegistryGift)
        val index = registryGifts.indexOf(cashFundRegistryGift)
        assertEquals(1, index)
    }
}