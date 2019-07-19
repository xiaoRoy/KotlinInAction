package com.learn.lab.json

import com.google.gson.Gson
import org.junit.Test
import java.lang.ClassCastException

class CourseJsonTest {

    companion object {
        const val COURSE_JSON = """
            {
    "name": "JAVA",
    "category": null
}
        """
    }

    @Test(expected = TypeCastException::class)
    fun test_parseCourse() {
        val course: Course = Gson().fromJson(COURSE_JSON,Course::class.java)
        course.category.toUpperCase()
    }
}