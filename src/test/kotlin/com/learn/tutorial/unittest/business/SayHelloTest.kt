package com.learn.tutorial.unittest.business

import com.learn.tutorial.unittest.bean.User
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import kotlin.test.assertEquals

class SayHelloTest {
    @Mock
    private lateinit var user: User

    private lateinit var sayHello: SayHello

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        sayHello = SayHello(user)
    }

    @Test
    fun test_say_hello(){
        Mockito.`when`(user.fullName()).thenReturn("Ryan Giggs")
        assertEquals("Hello, Ryan Giggs", sayHello.sayHello())
    }
}