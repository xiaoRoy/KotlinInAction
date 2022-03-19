package com.learn.coroutine

import java.util.concurrent.Executors


private class Hike(
        val name: String,
        val miles: Float,
        val ascentInFeet: Int
)

private class Weather

private class HikeData(
        val hike: Hike,
        val weather: Weather?
)

private fun fetchHikes(id: String): List<Hike> {
    return emptyList()
}

private fun fetchWeather(hike: Hike): Weather {
    TODO()
}


private class HikeViewModel : ViewModel() {
    private val ioThreadPool = Executors.newWorkStealingPool(10)

    private val hikeDataList = mutableListOf<HikeData>()
    val hikeLiveData = MutableLiveData<List<HikeData>>()


    fun displayHikes(userId: String) {
        ioThreadPool.submit {
            val hikes = fetchHikes(userId)
            onHikesFetched(hikes)
        }
    }

    private fun onHikesFetched(hikes: List<Hike>) {
        //It's on worker thread.
        hikes.forEach {
            ioThreadPool.submit {
                val weather = fetchWeather(it)
                val hikeData = HikeData(it, weather)
                hikeDataList.add(hikeData)
                hikeLiveData.postValue(hikeDataList)
            }
        }
    }
}


//using handler
private class HikeViewModelV2 : ViewModel() {
    private val ioThreadPool = Executors.newWorkStealingPool(10)

    private val hikeDataList = mutableListOf<HikeData>()
    val hikeLiveData = MutableLiveData<List<HikeData>>()


    fun displayHikes(userId: String) {
        ioThreadPool.submit {
            val hikes = fetchHikes(userId)
            onHikesFetched(hikes)
        }
    }

    private fun onHikesFetched(hikes: List<Hike>) {
        //It's on worker thread.
        hikes.forEach {
            ioThreadPool.submit {
                val weather = fetchWeather(it)
                val hikeData = HikeData(it, weather)
                hikeDataList.add(hikeData)
                hikeLiveData.postValue(hikeDataList)
            }
        }
    }
}


/*
* Fake Android Framework
* */

private open class ViewModel

private class MutableLiveData<T> {

    fun postValue(t: T) {

    }
}

private class Looper {

    companion object {
        fun getMainLooper(): Looper {
            return Looper()
        }
    }
}

private class Handler(private val looper: Looper) {

}
