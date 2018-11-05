package com.learn.kotlindoc.interop;

import com.learn.kotlindoc.model.GuestProfile;
import org.junit.Assert;
import org.junit.Test;

public class GuestFilterTest {

    @Test
    public void test_guest_filter_hasNameAndIsAdult() {
        boolean result = GuestFilterKt.filterGuestHasNameAndIsAdult(new GuestProfile("Smith", false));
        Assert.assertFalse(result);
    }
}
