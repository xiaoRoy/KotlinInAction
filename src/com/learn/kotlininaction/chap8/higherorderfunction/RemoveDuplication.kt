package com.learn.kotlininaction.chap8.higherorderfunction

class SiteVisit(val path: String, val duration: Double, val os: OS)

enum class OS{
    WINDOWS, LINUX, MAC, IOS, ANDROID
}

val logs = listOf(
        SiteVisit("/", 34.0, OS.WINDOWS),
        SiteVisit("/", 22.0, OS.MAC),
        SiteVisit("/login", 12.0, OS.WINDOWS),
        SiteVisit("/signUp", 8.0, OS.IOS),
        SiteVisit("/", 16.3, OS.ANDROID)
)

fun windowDurationAverage(){
    logs.filter { it.os == OS.WINDOWS }
            .map (SiteVisit::duration)
            .average()
}

fun List<SiteVisit>.durationAverage(os: OS): Double{
    return this.filter { it.os == os }
            .map (SiteVisit::duration)
            .average()
}

fun List<SiteVisit>.durationAverage(predicate: (SiteVisit) -> Boolean): Double{
    return logs.filter(predicate)
            .map(SiteVisit::duration)
            .average();
}

fun main(args: Array<String>) {
}
