package com.learn.tutorial.coroutine.chap6

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

private fun firstContext() {
    val defaultDispatcher = Dispatchers.Default
    val coroutineErrorHandler = CoroutineExceptionHandler { context, error ->
        println("Problems with coroutine: $error")
    }
    val emptyParentJob = Job()

    val firstContext = defaultDispatcher + coroutineErrorHandler + emptyParentJob
}

