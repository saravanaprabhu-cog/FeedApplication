package com.saravana.feedapplication.feedlist.viewmodel

import com.saravana.feedapplication.feedlist.model.Feed
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class FeedDetailViewModelTest {

    private val fakeFeed = Feed("title", "description", "image url")

    private lateinit var feedDetailViewModel: FeedDetailViewModel

    @Before
    fun setup() {
        feedDetailViewModel = FeedDetailViewModel(fakeFeed)
    }

    @Test
    fun getFeed() {
        Assert.assertEquals(fakeFeed, feedDetailViewModel.getFeed())
    }
}
