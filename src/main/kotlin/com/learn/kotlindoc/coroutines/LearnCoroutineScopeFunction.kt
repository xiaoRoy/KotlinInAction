package com.learn.kotlindoc.coroutines

import kotlinx.coroutines.*

 suspend fun showProductInfo(
    loadProductAction: suspend () -> Product,
    doSomeWork: () -> Unit
) = coroutineScope {
    val productDeferred: Deferred<Product> = async(Dispatchers.IO) { loadProductAction() }
     productDeferred.invokeOnCompletion { exception: Throwable? ->
         exception?.run {
             println("Caught exception: $this")
         }
     }
    withContext(Dispatchers.Default) {
        doSomeWork()
        val product = productDeferred.await()
        doShowProduct(product)
    }
}

private fun doShowProduct(product: Product) {
    product.showProductInfo()
}

class Product {
    fun showProductInfo() {}
}
