package com.learn.lab.sort

import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test


class ChipTest {

    @Test
    fun test_sortChips() {
        Chip.chipList[2].addOffset()
        assertEquals(Chip.CREATE_CASH_FUND, Chip.chipList.sorted()[0].name)
    }
}