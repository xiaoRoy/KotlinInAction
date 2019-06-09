package com.learn.demo.couplebuilder

class RegistryRetailerBuilder {

}

class RegistryGiftFactory {
    companion object {

        fun generatePartnerRegistryGiftList(size: Int = 5): List<RegistryGift> {
            return (0 until size).map {
                RegistryGift(it.toString(), it.toLong())
            }
        }

        fun generatePartnerCashFundRegistryGiftList(size: Int = 5): List<RegistryGift> {
            return (0 until size).map {
                CashFundRegistryGift(it.toString(), it.toLong())
            }
        }
    }
}