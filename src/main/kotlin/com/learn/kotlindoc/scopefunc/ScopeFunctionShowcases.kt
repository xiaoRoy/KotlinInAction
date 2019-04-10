package com.learn.kotlindoc.scopefunc

private class Person(
        val name: String,
        var age: Int,
        address: String
) {
    var address: String
        private set

    init {
        this.address = address
    }

    fun moveTo(address: String) {
        this.address = address
    }

    fun increaseAge() {
        age++
    }
}


private fun showAliceInfo() {
    val alice = Person("Alice", 20, "LA")
    println(alice)
    alice.increaseAge()
    alice.moveTo("New York")
    println(alice)
}

private fun showAliceInfoWithLet() {
    Person("Alice", 20, "LA").let {
        println(it)
        it.increaseAge()
        it.moveTo("New York")
        println(it)
    }
}

private fun showAliceInfoWithRun() {
    Person("Alice", 20, "LA").run {
        println(this)
        increaseAge()
        moveTo("New York")
        println(this)
    }
}

private fun showAliceUsingWith() {
    with(Person("Alice", 20, "LA")) {
        println(this)
        increaseAge()
        moveTo("New York")
        println(this)
    }
}

private fun showAliceUsingApply() {
    Person("Alice", 20, "LA").apply { 
        println(this)
        increaseAge()
        moveTo("New York")
        println(this)
    }
}
