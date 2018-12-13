package com.learn.kotlindoc.interop

import com.learn.kotlindoc.model.GuestProfile

fun filterGuestHasNameAndIsAdult(guestProfile: GuestProfile) = guestProfile.name.isNotEmpty() && guestProfile.isAdult