package com.learn.kotlininaction.dsl.html

open class Tag {

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
}

class TABLE : Tag() {
    fun tr(init: TR.() -> Unit) {

    }
}

class TR : Tag() {
    fun td(init: TD.() -> Unit) {

    }
}

class TD : Tag()