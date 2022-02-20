package com.learn.article

import kotlinx.coroutines.*
import org.junit.Before
import org.junit.Test
import java.lang.AssertionError
import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.CoroutineContext

class TestLowerCoroutineScope {

    @Before
    fun setup() {
        clearCoroutineExceptions()
    }

    private fun doSomethingWithException(): Int = throw IndexOutOfBoundsException()

    @Test(expected = IndexOutOfBoundsException::class)
    fun throwExceptionFromCoroutineScopeFunction() = doRunTest {
        coroutineScope {
            doSomethingWithException()
        }
    }

    @Test(expected = IndexOutOfBoundsException::class)
    fun throwExceptionFromLaunchInCoroutineScopeFunction() = doRunTest {
        coroutineScope {
            launch {
                doSomethingWithException()
            }
        }
    }

    @Test(expected = IndexOutOfBoundsException::class)
    fun throwExceptionFromAsyncInCoroutineScopeFunction() = doRunTest {
        coroutineScope {
            async { doSomethingWithException() }
        }
    }

    @Test(expected = IndexOutOfBoundsException::class)
    fun throwExceptionInChildJob() = doRunTest {
        coroutineScope {
            Job(coroutineContext[Job]).completeExceptionally(IndexOutOfBoundsException())
        }
    }

    @Test(expected = IndexOutOfBoundsException::class)
    fun throwExceptionInSupervisorScope() = doRunTest {
        supervisorScope {
            doSomethingWithException()
        }
    }

    private inline fun doRunTest(crossinline block: suspend () -> Unit) = runBlocking {
        block()
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
        coroutineExceptions += exception
    }
}
