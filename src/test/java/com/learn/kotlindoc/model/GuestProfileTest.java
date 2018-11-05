package com.learn.kotlindoc.model;

import org.junit.Assert;
import org.junit.Test;

public class GuestProfileTest {

    @Test
    public void test_guest_name() {
        GuestProfile guestProfile = new GuestProfile("Smith", true);
        Assert.assertEquals("Smith", guestProfile.getName());
    }

    @Test
    public void test_change_guest_name() {
        GuestProfile guestProfile = new GuestProfile("Mary", true);
        guestProfile.setName("Lucy");
        Assert.assertEquals("Lucy", guestProfile.getName());
    }

    @Test
    public void test_guest_isAdult() {
        GuestProfile guestProfile = new GuestProfile("Mary", true);
        guestProfile.setAdult(false);
        Assert.assertFalse(guestProfile.isAdult());
    }
}
