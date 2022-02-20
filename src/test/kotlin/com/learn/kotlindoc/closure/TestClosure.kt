package com.learn.kotlindoc.closure

import org.junit.Test
import kotlin.test.assertEquals

class TestClosure {


    @Test
    fun test_closure() {
        val logs = listOf<String>("one", "two", "three", "four", "five")
        val logInformation = LogInformation()
        var count = -1
        repeat(logs.size) { index ->
            val log = logs[index]
            count ++
            if (index != logs.lastIndex) {
                logInformation.setAction {
                    println("index: $index")
                    println(logs[count])
                }
            }

        }
        logInformation.showLog()

    }

    class LogInformation() {
        private var action: () -> Unit = {}

        fun showLog() {
            action()
        }

        fun setAction(action: () -> Unit) {
            this.action = action
        }
    }


    @Test
    fun test_makeFunction() {
        val result = FunctionBuilder().makeFunctions(10).last()()
        assertEquals(10, result)
        val resultSecond = FunctionBuilder().makeFunctions(10)[2]()
        assertEquals(10, resultSecond)
    }

    class FunctionBuilder {

        fun makeFunctions(count: Int): List<() -> Int> {
            val result= mutableListOf<() -> Int> ()
            var index = 0
            while (index < count) {
                result.add { index }
                index ++
            }
            return result
        }
    }
}
