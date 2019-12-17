package com.saravana.feedapplication.feedlist.viewmodel

import com.saravana.feedapplication.feedlist.repository.FeedRepository
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class FeedListViewModelTest {

    @Mock
    lateinit var mockFeedRepository: FeedRepository

    lateinit var feedListViewModel: FeedListViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        feedListViewModel = FeedListViewModel(mockFeedRepository)
    }

    @Test
    fun testInit() {
        feedListViewModel.init()
        Mockito.verify(mockFeedRepository).fetchFeedData()
    }
}