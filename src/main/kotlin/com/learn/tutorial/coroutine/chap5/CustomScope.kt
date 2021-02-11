package com.learn.tutorial.coroutine.chap5

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

class CustomScope: CoroutineScope {

    private var parentJob = Job()

    fun onStart() {
        parentJob = Job()
    }

    fun onStop() {
        parentJob.cancel()
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + parentJob
}
