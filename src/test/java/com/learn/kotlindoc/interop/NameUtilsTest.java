package com.learn.kotlindoc.interop;

import org.junit.Assert;
import org.junit.Test;

public class NameUtilsTest {

    @Test
    public void test_getFullName() {
        String result = NameUtils.getFullName("Peter", "Parker");
        Assert.assertEquals("Peter Parker", result);
    }

    @Test
    public void test_getFullName_onlyFirstName() {
        String result = NameUtils.getFullName("Peter", "");
        Assert.assertEquals("Peter", result);
    }

    @Test
    public void test_getFullName_onlyLastName() {
        String result = NameUtils.getFullName("", "Parker");
        Assert.assertEquals("Parker", result);
    }
}
