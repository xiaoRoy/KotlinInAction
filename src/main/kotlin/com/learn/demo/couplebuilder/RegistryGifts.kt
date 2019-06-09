package com.learn.demo.couplebuilder

open class RegistryGift(
        val id: String,
        val sortOrder: Long
) {
    open val type: String = REGISTRY_GIFT_TYPE_RETAILER_PRODUCT

    //todo use enum
    companion object {
        const val REGISTRY_GIFT_TYPE_RETAILER_PRODUCT = "RETAILER_PRODUCT"
        const val REGISTRY_GIFT_TYPE_CASH_FUND = "CASH_FUND"
        const val REGISTRY_GIFT_TYPE_UNIVERSAL_REGISTRY_PRODUCT = "UNIVERSAL_REGISTRY_PRODUCT"
        const val REGISTRY_GIFT_TYPE_CATALOG_PRODUCT = "CATALOG_PRODUCT"
    }
}

class CashFundRegistryGift(
        id: String,
        sortOrder: Long
) : RegistryGift(id, sortOrder) {

    override val type = REGISTRY_GIFT_TYPE_CASH_FUND
}

class UniversalRegistryGift(id: String,
                            sortOrder: Long
) : RegistryGift(id, sortOrder) {
    override val type = REGISTRY_GIFT_TYPE_UNIVERSAL_REGISTRY_PRODUCT
}

class ProductCatalogRegistryGift(
        id: String,
        sortOrder: Long
): RegistryGift(id, sortOrder) {
    override val type = REGISTRY_GIFT_TYPE_CATALOG_PRODUCT

}