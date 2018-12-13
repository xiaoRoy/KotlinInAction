package com.learn.lab.field

class Article(
        id: Int,
        private val _author: String? = null,
        private var _content: String? = null
) {

    //default val
    val id: Int = 0
        get() {
            return field
        }

    //default var
    var title: String = ""
        get() = field
        set(value) {
            field = value
        }

    //no backing field val
    val author: String
        get() = _author ?: ""

    //no backing field var
    var pageCount
        get() = 1
        set(value) {
            //property must be initialize
            /* if(field > 3) {
                 field = value
             }*/
        }

    /* var content: String = ""
         get() = if (field.isEmpty() && _content != null) _content else field*/
    var content
        get() = _content ?: ""
        set(value) {
            _content = value
        }
}
