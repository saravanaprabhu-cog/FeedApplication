package com.saravana.feedapplication.feedlist.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.saravana.feedapplication.feedlist.api.FeedService
import com.saravana.feedapplication.feedlist.model.FeedResponse
import com.saravana.feedapplication.feedlist.util.getOrAwaitValue
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@RunWith(MockitoJUnitRunner::class)
class FeedRepositoryTest {

    @Rule
    @JvmField
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var feedRepository: FeedRepository

    @Mock
    private lateinit var mockFeedService: FeedService
    @Mock
    private lateinit var mockFeedCall: Call<FeedResponse>
    @Mock
    private lateinit var mockFeedCallback: FeedRepository.FeedResponseHandler
    @Mock
    private lateinit var mockFeedResponse: Response<FeedResponse>
    @Mock
    private lateinit var mockThrowable: Throwable

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        feedRepository = FeedRepository(mockFeedService)

        `when`(mockFeedService.getFeedData(anyString())).thenReturn(mockFeedCall)
    }

    @Test
    fun fetchFeedDataFromServer_verifyOnResponse() {
        `when`(mockFeedCall.enqueue(mockFeedCallback)).thenAnswer {
            (it.getArgument<Callback<FeedResponse>>(0)).onResponse(mockFeedCall, mockFeedResponse)
        }

        feedRepository.fetchFeedDataFromServer(mockFeedCallback)

        verify(mockFeedCallback).onResponse(mockFeedCall, mockFeedResponse)
    }

    @Test
    fun fetchFeedDataFromServer_verifyOnError() {
        `when`(mockFeedCall.enqueue(mockFeedCallback)).thenAnswer {
            (it.getArgument<Callback<FeedResponse>>(0)).onFailure(mockFeedCall, mockThrowable)
        }

        feedRepository.fetchFeedDataFromServer(mockFeedCallback)

        verify(mockFeedCallback).onFailure(mockFeedCall, mockThrowable)
    }

    @Test
    fun onResponseEmitData() {
        feedRepository.fetchFeedDataFromServer(mockFeedCallback)

        val feedCallback = feedRepository.FeedResponseHandler()

        feedCallback.onResponse(mockFeedCall, mockFeedResponse)

        val feedResponse = feedRepository.getFeedResponseObservable().getOrAwaitValue()
        Assert.assertNotNull(feedResponse)

        val progress = feedRepository.isLoadingDataObservable().getOrAwaitValue()
        Assert.assertEquals(false, progress)
    }

    @Test
    fun onFailureEmitData() {
        feedRepository.fetchFeedDataFromServer(mockFeedCallback)

        val feedCallback = feedRepository.FeedResponseHandler()

        feedCallback.onFailure(mockFeedCall, mockThrowable)

        val feedResponse = feedRepository.getFeedResponseObservable().getOrAwaitValue()
        Assert.assertNull(feedResponse)

        val progress = feedRepository.isLoadingDataObservable().getOrAwaitValue()
        Assert.assertEquals(false, progress)
    }
}
