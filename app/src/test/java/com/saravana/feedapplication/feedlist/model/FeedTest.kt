package com.saravana.feedapplication.feedlist.model

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class FeedTest {

    private lateinit var validFeed: Feed

    @Before
    fun setUp() {
        validFeed = Feed("Feed Title", "Feed Description", "image URL")
    }

    @Test
    fun test_isValidTitle_withSomeTitle_shouldReturnTrue() {
        assertTrue(validFeed.isValidTitle())
    }

    @Test
    fun test_isValidTitle_withNullTitle_shouldReturnFalse() {
        assertFalse(Feed(null, "some description", "some url").isValidTitle())
    }

    @Test
    fun test_isValidTitle_withEmptyStringTitle_shouldReturnFalse() {
        assertFalse(Feed("", "some description", "some url").isValidTitle())
    }

    @Test
    fun test_isValidTitle_withNullStringTitle_shouldReturnFalse() {
        assertFalse(Feed("null", "some description", "some url").isValidTitle())
    }

    @Test
    fun test_isValidDescription_withSomeDescription_shouldReturnTrue() {
        assertTrue(validFeed.isValidDescription())
    }

    @Test
    fun test_isValidDescription_withNullDescription_shouldReturnFalse() {
        assertFalse(Feed("some title", null, "some url").isValidDescription())
    }

    @Test
    fun test_isValidDescription_withEmptyStringDescription_shouldReturnFalse() {
        assertFalse(Feed("some title", "", "some url").isValidDescription())
    }

    @Test
    fun test_isValidDescription_withNullStringDescription_shouldReturnFalse() {
        assertFalse(Feed("some title", "null", "some url").isValidDescription())
    }
}
