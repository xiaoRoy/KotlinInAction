package com.learn.article

import com.learn.kotlindoc.coroutines.Product
import com.learn.kotlindoc.coroutines.showProductInfo
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito
import java.lang.IllegalArgumentException

class TestLowerCoroutineScopeDocumentation {

    private val doSomeWork: () -> Unit = {
        // do something
    }


    /*
    * 1.showSomeData returns as soon as the data is loaded and displayed in the UI.
    * */
    @Test
    fun test_showProduct() = runBlocking {
        val product = Product()
        val productSpy = Mockito.spy(product)
        val loadProductAction: suspend () -> Product = {
            delay(3000L)
            productSpy
        }
        showProductInfo(loadProductAction, doSomeWork)
        delay(3000L)
        Mockito.verify(productSpy).showProductInfo()
    }

    @Test(expected = IllegalArgumentException::class)
    fun test_showProduct_throwExceptionInOneCoroutine() = runBlocking{
        val product = Product()
        val productSpy = Mockito.spy(product)

        val doSomeWork: () -> Unit = {
            throw IllegalArgumentException()

        }

        val loadProductAction: suspend () -> Product = {
            delay(3000L)
            productSpy
        }

        showProductInfo(loadProductAction, doSomeWork)
        delay(3000L)
        Mockito.verify(productSpy, Mockito.never()).showProductInfo()
    }

    @Test(expected = IndexOutOfBoundsException::class)
    fun test_showProduct_throwExceptionInOneCoroutine_second() = runBlocking{
        val product = Product()
        val productSpy = Mockito.spy(product)

        val loadProductAction: suspend () -> Product = {
            delay(1000L)
            throw IndexOutOfBoundsException()
        }
        showProductInfo(loadProductAction, doSomeWork)
        delay(3000L)
        Mockito.verify(productSpy, Mockito.never()).showProductInfo()
    }
}
