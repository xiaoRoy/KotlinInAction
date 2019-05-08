package com.learn.demo.json

import com.google.gson.annotations.SerializedName

interface IOrder {
    val id: String
    var total: Double
    val products: List<IProduct>
    val memberId: String?
    var payment: String
    var note: String
}

class OrderRaw(
        override val id: String = "",
        override var total: Double = 0.0,
        override val products: List<ProductRaw> = emptyList(),
        override val memberId: String? = null,
        override var payment: String = "",
        @SerializedName("note") private var _note: String? = ""
) : IOrder {
    override var note: String
        get() = _note ?: ""
        set(value) {
            _note = value
        }
}

class Order(iOrder: IOrder = OrderRaw(),
            override val products: List<Product> = emptyList()
) : IOrder by iOrder {
    val isMember: Boolean by lazy { !memberId.isNullOrEmpty() }

}

interface IProduct {
    val id: String
    val name: String
    var price: Double
    var description: String
}


class ProductRaw(
        override val id: String = "",
        override val name: String = "",
        override var price: Double = 0.0,
        @SerializedName("description") private var _description: String? = null
) : IProduct {

    override var description: String
        get() = _description ?: ""
        set(value) {
            _description = value
        }
}

class Product(iProduct: IProduct) : IProduct by iProduct {

    val hasDescription: Boolean = description.isNotEmpty()
}
