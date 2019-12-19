package com.saravana.feedapplication.feedlist.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.nhaarman.mockitokotlin2.verify
import com.saravana.feedapplication.feedlist.model.FeedResponse
import com.saravana.feedapplication.feedlist.repository.FeedRepository
import com.saravana.feedapplication.feedlist.util.getOrAwaitValue
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class FeedListViewModelTest {
    @Rule
    @JvmField
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var mockFeedRepository: FeedRepository

    private val fakeFeedResponse = FeedResponse("Title", arrayListOf())

    private lateinit var feedListViewModel: FeedListViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        feedListViewModel = FeedListViewModel(mockFeedRepository)
    }

    @Test
    fun getFeedResponseObservable() {
        feedListViewModel.getFeedResponseObservable()
        verify(mockFeedRepository).getFeedResponseObservable()
    }

    @Test
    fun isLoadingDataObservable() {
        feedListViewModel.isLoadingDataObservable()
        verify(mockFeedRepository).isLoadingDataObservable()
    }

    @Test
    fun init() {
        Mockito.`when`(mockFeedRepository.getFeedResponseObservable()).thenReturn(MutableLiveData(fakeFeedResponse))
        val feedResponse = feedListViewModel.getFeedResponseObservable().getOrAwaitValue()
        assertEquals(feedResponse, fakeFeedResponse)
    }

    @Test
    fun onRefresh() {
        Mockito.`when`(mockFeedRepository.getFeedResponseObservable()).thenReturn(MutableLiveData(fakeFeedResponse))
        val feedResponse = feedListViewModel.getFeedResponseObservable().getOrAwaitValue()
        assertEquals(feedResponse, fakeFeedResponse)
    }
}
