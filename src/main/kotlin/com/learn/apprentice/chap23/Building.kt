package com.learn.apprentice.chap23

import kotlinx.coroutines.*


private fun speakThroughBullhorn(message: String) {
    println(message)
}

class Building(val name: String) {

    private fun sleep(millis: Long) {
        Thread.sleep(millis)
    }

    fun makeFoundation() {
        sleep(300)
        speakThroughBullhorn("The foundation is ready")
    }

    fun buildFloor(floor: Int) {
        sleep(100)
        speakThroughBullhorn("The #$floor is raised")
    }

    fun placeWindow(floor: Int) {
        sleep(100)
        speakThroughBullhorn("Windows are placed on the #$floor floor")
    }

    fun installDoor(floor: Int) {
        sleep(100)
        speakThroughBullhorn("Doors are installed on the #$floor floor")
    }

    fun provideElectricity(floor: Int) {
        sleep(100)
        speakThroughBullhorn("Electricity is provided on the #$floor floor")
    }

    fun buildRoof() {
        sleep(200)
        speakThroughBullhorn("The roof is ready")
    }

    fun fitOut(floor: Int) {
        Thread.sleep(200)
        speakThroughBullhorn("The #$floor is furnished")
    }
}

class BuildingSecond(
        val name: String,
        var floors: Int = 0,
        private val scope: CoroutineScope
) {

    private fun currentThreadName() = "[${Thread.currentThread().name}]"

    suspend fun makeFoundation() = scope.launch {
        delay(300)
        speakThroughBullhorn("${currentThreadName()} The foundation is ready")
    }

    suspend fun buildFloor(floor: Int) = scope.launch {
        delay(100)
        speakThroughBullhorn("${currentThreadName()} Floor number $floor is build")
        floors++
    }

    fun placeWindows(floor: Int) {
        scope.launch {
            delay(100)
            speakThroughBullhorn("${currentThreadName()} Windows are placed on the #$floor floor")
        }
    }

    suspend fun installDoors(floor: Int) {
        scope.launch {
            delay(100)
            speakThroughBullhorn("${currentThreadName()} Doors are installed on the #$floor floor")
        }
    }

    suspend fun provideElectricity(floor: Int) {
        scope.launch {
            delay(100)
            speakThroughBullhorn("${currentThreadName()} Electricity is provided on the #$floor floor")
        }
    }

    suspend fun buildRoof() = scope.launch {
        delay(200)
        speakThroughBullhorn("${currentThreadName()} The roof is ready")
    }

    suspend fun fitOut(floor: Int) {
        scope.launch {
            delay(200)
            speakThroughBullhorn("${currentThreadName()} The #$floor is furnished")
        }
    }
}

class BuildingYard {
    suspend fun startProject(name: String, floors: Int) {
        val building = withContext(context = Dispatchers.Default) {
            val result = BuildingSecond(name, scope = this)
            val cores = Runtime.getRuntime().availableProcessors()
            speakThroughBullhorn("The building $name is start with $cores building machines engaged")
            result.makeFoundation().join()

            (1..floors).forEach {
                with(result) {
                    buildFloor(it).join()

                    placeWindows(it)
                    installDoors(it)
                    provideElectricity(it)
                    fitOut(it)
                }
            }

            result.buildRoof()
            result
        }



        if (building.floors == floors) {
            speakThroughBullhorn("${building.name} is ready!")
        }
    }
}


fun main() {
    runBlocking {
        BuildingYard().startProject("Smart House", 1)
    }
}
