package com.learn.kotlininaction.chap4.createinstance

import java.io.File

/*
* 4.4.1 Object declarations: singletons made easy
* */

class Employee

object PayRoll{
    val employeeList = arrayListOf<Employee>()

    fun calculateSalary(){

    }
}

object CaseInsensitiveComparator : Comparator<File>{
    override fun compare(oneFile: File?, anotherFile: File?): Int =
        if(oneFile != null && anotherFile != null){
            oneFile.path.compareTo(anotherFile.path, ignoreCase = true)
        } else{
            0
        }
}

data class Cup constructor(val id: String){
    object CupComparator: Comparator<Cup>{
        override fun compare(oneCup: Cup, anotherCup: Cup) = oneCup.id.compareTo(anotherCup.id)
    }
}

fun main(args: Array<String>) {
    compareFilePath()
}

fun compareFilePath(){
    val fileOne = File("/picture")
    val fileAnother = File("/music")
    val files = listOf(fileOne, fileAnother)
    println(files.sortedWith(CaseInsensitiveComparator))
}
