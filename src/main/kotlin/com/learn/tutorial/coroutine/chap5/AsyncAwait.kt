package com.learn.tutorial.coroutine.chap5

import kotlinx.coroutines.*
import java.io.File

fun main() {
//    displayUser()
    checkUser(992)
}


private class User(
        val id: Int,
        val name: String,
        val lastName: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id
    }
}

private fun getUserById(id: Int, onUserReady: (User) -> Unit) {
    Thread.sleep(3000L)
    onUserReady(User(id, "Smith", "Adam"))
}

private fun getUserByIdAsync(id: Int): Deferred<User> {
    return GlobalScope.async {
        delay(3000L)
        println("getUserByIdAsync")
        User(id, "Smith", "Mary")
    }
}

private fun displayUser() {
    GlobalScope.launch {
        val id = 44
        val user = getUserByIdAsync(id).await()
        println("I am ${user.name}")
    }
    runBlocking {
        delay(5000L)
    }
}

private fun readUserAsync(filePath: String) = GlobalScope.async {
    println("Reading the file of users")
    delay(1000L)
    println(File("").absoluteFile)
    File(filePath)
            .readLines()
            .asSequence()
            .filter { it.isNotEmpty() }
            .map {
                val data = it.split(" ")
                if (data.size == 3) data else emptyList()
            }
            .filter { it.isNotEmpty() }
            .map {
                val (id, name, lastName) = it
                User(id.toInt(), name, lastName)
            }
            .toList()

}

private fun checkUser(id: Int) {
    GlobalScope.launch {
        val user = getUserByIdAsync(id).await()
        println("gap")
        val allUsers = readUserAsync("src/users.txt").await()

        println("Finding User")
        val hasFound = user in allUsers

        if(hasFound) {
            println("Found user in file")
        } else {
            println("User not found")
        }
    }
    println("Outside of the coroutine")
    runBlocking {
        delay(7000L)
    }
}
