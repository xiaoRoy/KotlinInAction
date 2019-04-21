package com.learn.kotlininaction.dsl.html

open class Tag(val name: String) {

    private val children =  mutableListOf<Tag>()

    fun table(init: TABLE.() -> Unit) = TABLE().apply(init)

    protected fun<T : Tag> doInit(child: T, init: T.() -> Unit) {
        child.init()// or init(child)
        children.add(child)
    }

    override fun toString(): String = "<$name>${children.joinToString("")}</$name>"

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
}

class TABLE : Tag("table") {
    fun tr(init: TR.() -> Unit) = doInit(TR(), init)
}

class TR : Tag("tr") {
    fun td(init: TD.() -> Unit) = doInit(TD(), init)
}

class TD : Tag("td")