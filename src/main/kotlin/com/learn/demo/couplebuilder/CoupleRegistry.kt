package com.learn.demo.couplebuilder

class CoupleRegistry(
        val id: String,
        val registryGift: RegistryGift?,
        val registryType: Int
) {

    companion object{
        const val REGISTRY_TYPE_PARTNER = 1
        const val REGISTRY_TYPE_MANUAL = 2
        const val REGISTRY_TYPE_THE_KNOT = 3
    }
}