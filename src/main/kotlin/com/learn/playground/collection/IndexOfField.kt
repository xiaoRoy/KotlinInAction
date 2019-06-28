package com.learn.playground.collection

class IndexOfField {

    open class RegistryGift(val id: String) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is RegistryGift) return false

            if (id != other.id) return false

            return true
        }

        override fun hashCode(): Int {
            return id.hashCode()
        }
    }

    class CashFundRegistryGift(
            id: String,
            private val cashFundId: String
    ) : RegistryGift(id) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is CashFundRegistryGift) return false
            if (!super.equals(other)) return false

            if (cashFundId != other.cashFundId) return false

            return true
        }

        override fun hashCode(): Int {
            var result = super.hashCode()
            result = 31 * result + cashFundId.hashCode()
            return result
        }
    }
}