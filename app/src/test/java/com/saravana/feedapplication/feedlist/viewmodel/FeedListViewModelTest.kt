package com.saravana.feedapplication.feedlist.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.saravana.feedapplication.feedlist.listener.FeedResponseListener
import com.saravana.feedapplication.feedlist.model.FeedResponse
import com.saravana.feedapplication.feedlist.repository.FeedRepository
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.stubbing.Answer


@RunWith(MockitoJUnitRunner::class)
class FeedListViewModelTest {
    @Rule
    @JvmField
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var mockFeedRepository: FeedRepository

    @Mock
    var mockFeedResponseListener: FeedResponseListener = object : FeedResponseListener {
        override fun onFeedResponse(feedResponse: FeedResponse) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }
    private lateinit var feedListViewModel: FeedListViewModel

    private val feedResponse = FeedResponse("", arrayListOf())

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        feedListViewModel = FeedListViewModel(mockFeedRepository)
    }

    @Test
    fun testLoadingData() {
        feedListViewModel.init()
        assertEquals(true, feedListViewModel.isLoadingDataObservable().value)
    }

    @Test
    fun testInit() {
        //TODO make this unit test work
        Mockito.`when`(mockFeedRepository.fetchFeedData(mockFeedResponseListener))
            .thenAnswer(Answer {
                mockFeedResponseListener.onFeedResponse(feedResponse)
            })
        feedListViewModel.init()
    }

}
