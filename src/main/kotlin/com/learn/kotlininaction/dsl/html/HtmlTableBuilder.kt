package com.learn.kotlininaction.dsl.html

open class Tag(val name: String) {

    private val children =  mutableListOf<Tag>()

    protected fun<T : Tag> doInit(child: T, init: T.() -> Unit) {
        child.init()// or init(child)
        children.add(child)
        println(children.hashCode())
    }

    override fun toString(): String = "<$name>${children.joinToString("")}</$name>"
}

fun table(init: TABLE.() -> Unit) = TABLE().apply(init)

fun createTable() = table {
    tr {
        td {

        }
    }
}

fun createTableExplicitThis() = table {
    this@table.tr {
        this@tr.td {

        }
    }
}

fun main() {
    print(createTable())
}

class TABLE : Tag("table") {
    fun tr(init: TR.() -> Unit){
        doInit(TR(), init)
        println("doInit: TR")
    }
}

class TR : Tag("tr") {
    fun td(init: TD.() -> Unit) {
        doInit(TD(), init)
        println("doInit: TD")
    }
}

class TD : Tag("td")