package com.saravana.feedapplication.feedlist.model

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class FeedResponseTest {

    private lateinit var validFeedResponse: FeedResponse

    @Before
    fun setUp() {
        validFeedResponse = FeedResponse("Feed Title", arrayListOf())
    }

    @Test
    fun test_isValidFeedTitle_withSomeFeedTitle_shouldReturnTrue() {
        assertTrue(validFeedResponse.isValidFeedTitle())
    }

    @Test
    fun test_isValidFeedTitle_withNullFeedTitle_shouldReturnFalse() {
        assertFalse(FeedResponse(null, arrayListOf()).isValidFeedTitle())
    }

    @Test
    fun test_isValidFeedTitle_withEmptyStringFeedTitle_shouldReturnFalse() {
        assertFalse(FeedResponse("", arrayListOf()).isValidFeedTitle())
    }

    @Test
    fun test_isValidFeedTitle_withNullStringFeedTitle_shouldReturnFalse() {
        assertFalse(FeedResponse("null", arrayListOf()).isValidFeedTitle())
    }
}
