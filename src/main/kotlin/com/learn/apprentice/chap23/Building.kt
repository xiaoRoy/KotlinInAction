package com.learn.apprentice.chap23

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


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
        var floor: Int = 0,
        private val scope: CoroutineScope
) {

    private fun currentThreadName() = "[${Thread.currentThread().name}]"

    suspend fun makeFoundation() {
        delay(300)
        scope.launch {
            speakThroughBullhorn("${currentThreadName()} The foundation is ready")
        }
    }

    suspend fun buildFloor(floor: Int) {
        delay(100)
        scope.launch {
            speakThroughBullhorn("${currentThreadName()} The foundation is ready")
        }
    }

}
