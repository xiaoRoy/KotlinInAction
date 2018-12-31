package com.learn.demo.productsku

class ProductDetail(val skus:Map<String, ProductSku>)


class ProductSku(val variants: Map<String, String>, val isActive: Boolean)
