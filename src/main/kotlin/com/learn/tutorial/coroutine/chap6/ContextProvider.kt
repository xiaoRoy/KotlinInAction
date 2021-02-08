package com.learn.tutorial.coroutine.chap6

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

interface ContextProvider {
    fun context():CoroutineContext
}

class FirstCoroutineContextProvider(
        private val context: CoroutineContext = Dispatchers.Default
): ContextProvider {
    override fun context(): CoroutineContext {
        return context
    }
}

