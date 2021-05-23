package com.learn.article

import kotlinx.coroutines.CoroutineExceptionHandler
import org.junit.Before
import java.lang.AssertionError
import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.CoroutineContext

class TestLowerCoroutineScope {

    @Before
    fun setup() {

    }
}

private val coroutineExceptions = mutableListOf<Throwable>()

fun clearCoroutineExceptions() {
    coroutineExceptions.clear()
}

fun throwCoroutineExceptions() {
    val exceptions = consumeExceptions()
    if (exceptions.isNotEmpty()) {
        throw AssertionError().apply {
            exceptions.forEach {
                addSuppressed(it)
            }
        }
    }
}

private fun consumeExceptions(): List<Throwable> {
    return coroutineExceptions.toList().also {
        coroutineExceptions.clear()
    }
}

class TestCoroutineExceptionHandler :
        AbstractCoroutineContextElement(key = CoroutineExceptionHandler.Key),
        CoroutineExceptionHandler {

    override fun handleException(context: CoroutineContext, exception: Throwable) {
        val key: CoroutineExceptionHandler.Key = CoroutineExceptionHandler
        coroutineExceptions += exception
    }
}

class What : CoroutineContext.Key<CoroutineExceptionHandler>
