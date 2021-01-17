package com.learn.demo.productsku

class ProductDetailPresenter(
        private val productDetail: ProductDetail,
        private val viewRenderer: ProductDetailContract.ViewRenderer
) : ProductDetailContract.Presenter {

    private val allProductSkuVariantsGroupByVariantName: Map<String, List<String>> by lazy { generateAllProductSkuVariantsGroupByVariantName() }

    override fun viewProductSkus() {
        val allProductSkus = findProductSkuByVariants()
        val variantCount = allProductSkuVariantsGroupByVariantName.size
        val notAvailableProductSkuVariantsGroupByVariantName = mutableMapOf<String, MutableList<String>>()
        if (variantCount > 1) {
            val what: (ProductSku) -> Iterable<Map.Entry<String, String>> = { productSku: ProductSku -> productSku.variants.entries}
            val allProductSkuVariants = allProductSkus
                    .flatMap (what)
                    .distinct()
            allProductSkuVariants.forEach { variantEntry ->
                val hasAvailableVariant = allProductSkus
                        .filter { it.variants.entries.contains(variantEntry) }
                        .any { it.isActive }
                if (!hasAvailableVariant) {
                    notAvailableProductSkuVariantsGroupByVariantName
                            .getOrPut(variantEntry.key) { mutableListOf() }.add(variantEntry.value)
                }
            }
        }
    }

    private val what: (ProductSku) -> Iterable<Map.Entry<String, String>> = { productSku: ProductSku -> productSku.variants.entries}

    /*private fun generateAllVariantCombinations(){

    }*/

    private fun findProductSkuByVariants(selectedVariants: Map<String, String> = emptyMap()): List<ProductSku> {
        val allProductSkus = productDetail.skus.values
        var result = listOf<ProductSku>()
        if (selectedVariants.isNotEmpty()) {
            result = allProductSkus
                    .asSequence()
                    .filter { it.variants.entries.containsAll(selectedVariants.entries) }
                    .toList()
        }
        return result
    }

    private fun generateAllProductSkuVariantsGroupByVariantName(): Map<String, List<String>> {
        val allProductSkus = productDetail.skus.values
        val oneSku = 1
        val result = mutableMapOf<String, MutableList<String>>()
        if (allProductSkus.size > oneSku) {
            allProductSkus
                    .flatMap (what)
                    .distinct()
                    .groupByTo(result, { it.key }, { it.value })
        }
        return result
    }
}
