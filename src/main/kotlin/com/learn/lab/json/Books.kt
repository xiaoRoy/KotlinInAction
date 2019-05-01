package com.learn.lab.json

import com.google.gson.annotations.SerializedName

class Book(
        @SerializedName("id") private val _id: String? = null,
        @SerializedName("title") private val _title: String? = null,
        val pageCount: Int = 0,
        @SerializedName("author") private val _author: String? = null,
        val publisher: String = ""
) {

    val id: String
        get() = _id ?: ""

    val title: String by lazy { _title ?: "" }

    val author: String
        get() = _author ?: ""
}

/*
* Model With Property not include in JSON but without default value.
* */
class BookB(
        @SerializedName("id") private val _id: String? = null,
        @SerializedName("title") private val _title: String? = null,
        val pageCount: Int = 0,
        @SerializedName("author") private val _author: String? = null,
        val publisher: String
) {

    val id: String
        get() = _id ?: ""

    val title: String by lazy { _title ?: "" }

    val author: String
        get() = _author ?: ""
}

class BookC(
        val id: String = "",
        val title: String = "",
        val pageCount: Int = 0,
        val author: String? = null,
        val publisher: String
)
