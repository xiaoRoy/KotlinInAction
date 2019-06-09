package com.learn.demo.couplebuilder

import kotlin.random.Random

class RegistryRetailer(
        val id: Long,
        val name: String,
        val isDeleted: Boolean,
        val isPartner: Boolean,
        val oneColSortOrder: Int = Int.MAX_VALUE - 1,
        val fourColSortOrder: Int
)