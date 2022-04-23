package com.learn.coroutine

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.nio.file.WatchEvent
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
    return Weather()
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
                updateHikeData(it)
            }
        }
    }

    private fun updateHikeData(hike: Hike) {
        val weather = fetchWeather(hike)
        val hikeData = HikeData(hike, weather)
        hikeDataList.add(hikeData)
        hikeLiveData.postValue(hikeDataList)
    }

    fun addHike(hike: Hike) {
        ioThreadPool.submit {
            updateHikeData(hike)
        }
    }
}


//using handler
private class HikeViewModelV2 : ViewModel() {
    private val ioThreadPool = Executors.newWorkStealingPool(10)
    private val handler = Handler(Looper.getMainLooper())

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
                updateHikeData(it)
            }
        }
    }

    private fun updateHikeData(hike: Hike) {
        val weather = fetchWeather(hike)
        val hikeData = HikeData(hike, weather)
        handler.post {
            hikeDataList.add(hikeData)
            hikeLiveData.setValue(hikeDataList)
        }
    }

    fun addHike(hike: Hike) {
        ioThreadPool.submit {
            updateHikeData(hike)
        }
    }
}

private class HikeViewModelV3 : ViewModel() {


    private suspend fun fetchHikeForUser(userId: String): List<Hike> {
        return withContext(Dispatchers.IO) {
            fetchHikes(userId)
        }
    }

    private suspend fun fetchWeatherForHike(hike: Hike) {
        return withContext(Dispatchers.IO) {
            fetchWeather(hike)
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

    fun setValue(t: T) {

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

    fun post(runnable: Runnable) {
        runnable.run()
    }

}
