package com.saravana.feedapplication.feedlist.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.saravana.feedapplication.feedlist.listener.FeedResponseListener
import com.saravana.feedapplication.feedlist.model.FeedResponse
import com.saravana.feedapplication.feedlist.repository.FeedRepository
import org.junit.Assert.assertTrue
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

    @Captor
    var argCaptor: ArgumentCaptor<FeedResponseListener>? = null

    val feedResponse = FeedResponse("", arrayListOf())

    @Mock
    lateinit var mockFeedRepository: FeedRepository
    @Mock
    var mockFeedResponseListener: FeedResponseListener = object : FeedResponseListener {
        override fun onFeedResponse(feedResponse: FeedResponse) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }

    @Mock
    internal var observer: Observer<FeedResponse>? = null

    lateinit var feedListViewModel: FeedListViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        feedListViewModel = FeedListViewModel(mockFeedRepository)
        feedListViewModel.getFeedDataViewModel().observeForever(this.observer!!)
    }

    @Test
    fun sampleTest() {
        assertTrue(feedListViewModel.getFeedDataViewModel().hasObservers())
    }

    @Test
    fun testInit() {
        Mockito.`when`(mockFeedRepository.fetchFeedData(mockFeedResponseListener))
            .thenAnswer(Answer { })
        feedListViewModel.init()
        Mockito.verify(mockFeedRepository).fetchFeedData(argCaptor!!.capture())
        assertTrue(argCaptor?.value is MyCallback)
    }

    class MyCallback : FeedResponseListener {
        override fun onFeedResponse(feedResponse: FeedResponse) {
        }

    }

    @Test
    fun doAction_doesSomething() {
        /* Given */
        val mock = mock<FeedResponseListener> {
            on { onFeedResponse() } doReturn FeedResponse()
        }
        val classUnderTest = ClassUnderTest(mock)

        /* When */
        feedListViewModel.init()

        /* Then */
        verify(mock).doSomething(any())
    }
}