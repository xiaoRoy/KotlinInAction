package com.learn.designpattern.builder

class FoodOrder(
        val number: Int,
        val bread: String = "",
        val condiments: String = "",
        val meat: String = "",
        val fish: String = ""
) {

    class Builder(
            private val number: Int,//required
            private var bread: String = "",//optional
            private var condiments: String = "",
            private var meat: String = "",
            private var fish: String = ""
    ) {

        fun bread(bread: String) = apply { this.bread = bread }
        fun condiments(condiments: String) = apply { this.condiments = condiments }
        fun meat(meat: String) = apply { this.meat = meat }
        fun fish(fish: String) = apply { this.fish = fish }

        fun build() = FoodOrder(number, bread, condiments, meat, fish)
    }
}