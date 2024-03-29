package com.learn.coroutine

import kotlinx.coroutines.*


/*
*
* Using Coroutines and Suspending Functions: A Practical Example
* */


private class Profile(val id: String)

private suspend fun fetchProfile(id: String): Profile {
    delay(3000) // mocking the long-run process
    return Profile(id)
}

private fun displayProfile(profile: Profile) {
    println(profile)
}


private fun learnSuspendingFunction(scope: CoroutineScope) {
    val id = "id"
    //dispatches coroutines on the main thread
    scope.launch {
        val childScope = this
        val profileDeferred = childScope.async(Dispatchers.Default) {
            fetchProfile(id)
        }
        val profile = profileDeferred.await()
        displayProfile(profile)
    }
}

private fun learnSuspendingFunctionSecond(scope: CoroutineScope) {
    val id = "id"
    scope.launch {
        val profile = fetchProfileWithContext(id)
        displayProfile(profile)
    }
}

private suspend fun fetchProfileWithContext(id: String): Profile {
    return withContext(Dispatchers.Default) {
        delay(1000L)
        Profile(id)
    }
}
