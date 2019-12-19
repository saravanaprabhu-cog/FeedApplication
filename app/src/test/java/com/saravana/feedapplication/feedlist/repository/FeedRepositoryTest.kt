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

    private lateinit var mockFeedService: FeedService
    private lateinit var mockFeedCall: Call<FeedResponse>
    private lateinit var mockFeedCallback: FeedRepository.MyCallback
    private lateinit var mockFeedResponse: Response<FeedResponse>
    private lateinit var mockThrowable: Throwable

    private val fakeFeedResponse = FeedResponse("Title", arrayListOf())

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mockFeedService = mock(FeedService::class.java)
        mockFeedCall = mock(Call::class.java) as Call<FeedResponse>
        mockFeedCallback = mock(FeedRepository.MyCallback::class.java)
        mockFeedResponse = mock(Response::class.java) as Response<FeedResponse>
        mockThrowable = mock(Throwable::class.java)

        feedRepository = FeedRepository(mockFeedService, mockFeedCall)
    }

    @Test
    fun fetchFeedDataFromServer_verifyOnResponse() {
        `when`(mockFeedCall.enqueue(mockFeedCallback)).thenAnswer {
            (it.getArgument<Callback<FeedResponse>>(0)).onResponse(mockFeedCall, mockFeedResponse)
        }

        feedRepository.fetchFeedDataFromServer3(mockFeedCallback)

        verify(mockFeedCallback).onResponse(mockFeedCall, mockFeedResponse)
    }

    @Test
    fun fetchFeedDataFromServer_verifyOnError() {
        `when`(mockFeedCall.enqueue(mockFeedCallback)).thenAnswer {
            (it.getArgument<Callback<FeedResponse>>(0)).onFailure(mockFeedCall, mockThrowable)
        }

        feedRepository.fetchFeedDataFromServer3(mockFeedCallback)

        verify(mockFeedCallback).onFailure(mockFeedCall, mockThrowable)
    }

    @Test
    fun onResponseEmitData() {
        feedRepository.fetchFeedDataFromServer3(mockFeedCallback)

        val feedCallback = feedRepository.MyCallback()

        feedCallback.onResponse(mockFeedCall, mockFeedResponse)

        val feedResponse = feedRepository.getFeedResponseObservable().getOrAwaitValue()
        Assert.assertNotNull(feedResponse)

        val progress = feedRepository.isLoadingDataObservable().getOrAwaitValue()
        Assert.assertEquals(false, progress)
    }

    @Test
    fun onFailureEmitData() {
        feedRepository.fetchFeedDataFromServer3(mockFeedCallback)

        val feedCallback = feedRepository.MyCallback()

        feedCallback.onFailure(mockFeedCall, mockThrowable)

        val feedResponse = feedRepository.getFeedResponseObservable().getOrAwaitValue()
        Assert.assertNull(feedResponse)

        val progress = feedRepository.isLoadingDataObservable().getOrAwaitValue()
        Assert.assertEquals(false, progress)
    }

    /*fun sampleTest() {
        *//* val callback = mock(Callback::class.java)
         val service = mock(FeedService::class.java)

         val mockRetrofitCall = mock(Call::class.java)
         `when`(mockRetrofitCall.enqueue(callback)).thenReturn()
 *//*

        val gson = Gson()
        val jsonString = "{\n" +
                "\"title\":\"About Canada\",\n" +
                "\"rows\":[\n" +
                "\t{\n" +
                "\t\"title\":\"Title\",\n" +
                "\t\"description\":\"Description\",\n" +
                "\t\"imageHref\":\"url\"\n" +
                "\t}\n" +
                "\t]\n" +
                "\t}"
        val response = gson.fromJson(jsonString, FeedResponse::class.java)
        *//*val call = Calls.response(response)

        val networkObserver = Mockito.mock(Observer::class.java) as Observer<Boolean>
        feedRepository.isLoadingDataObservable().observeForever(networkObserver)
        feedRepository.fetchFeedDataFromServer()
        val inOrder = Mockito.inOrder(networkObserver)
        inOrder.verify(networkObserver).onChanged(false)
        inOrder.verifyNoMoreInteractions()*//*

        val retrofitService = RetrofitRequest.getRetrofitFor(URLConstant.BASE_URL).create(FeedService::class.java)

        val mockRetrofitCall = mock(Call::class.java) as Call<FeedResponse>
        val mockCallback = mock(Callback::class.java) as Callback<FeedResponse>
        val mockResponse = mock(Response::class.java) as Response<FeedResponse>

        `when`(mockRetrofitCall.enqueue(mockCallback)).thenAnswer {
            (it.getArgument<Callback<FeedResponse>>(0)).onResponse(mockRetrofitCall, mockResponse)
        }

        feedRepository.fetchFeedDataFromServer(mockRetrofitCall, mockCallback)

        verify(mockCallback).onResponse(mockRetrofitCall, mockResponse)

    }

    @Test
    fun sampleTest2() {
        val retrofitService = RetrofitRequest.getRetrofitFor(URLConstant.BASE_URL).create(FeedService::class.java)

        val mockRetrofitCall = mock(Call::class.java) as Call<FeedResponse>
        val mockCallback = mock(Callback::class.java) as Callback<FeedResponse>
        val mockThrowable = Throwable()

        `when`(mockRetrofitCall.enqueue(mockCallback)).thenAnswer {
            (it.getArgument<Callback<FeedResponse>>(0)).onFailure(mockRetrofitCall, mockThrowable)
        }

        feedRepository.fetchFeedDataFromServer(mockRetrofitCall, mockCallback)

        verify(mockCallback).onFailure(mockRetrofitCall, mockThrowable)
    }

    @Test
    fun sampleTest3() {
        val retrofitService = RetrofitRequest.getRetrofitFor(URLConstant.BASE_URL).create(FeedService::class.java)

        val mockRetrofitCall = mock(Call::class.java) as Call<FeedResponse>
        val mockCallback = mock(FeedRepository.MyCallback::class.java)
        val mockThrowable = Throwable()

        `when`(mockRetrofitCall.enqueue(mockCallback)).thenAnswer {
            (it.getArgument<FeedRepository.MyCallback>(0)).onFailure(mockRetrofitCall, mockThrowable)
        }

        feedRepository.fetchFeedDataFromServer2(mockRetrofitCall, mockCallback)

        verify(mockCallback).onFailure(mockRetrofitCall, mockThrowable)
    }

    @Test
    fun sampleTest4() {
        val retrofitService = RetrofitRequest.getRetrofitFor(URLConstant.BASE_URL).create(FeedService::class.java)

        val mockRetrofitCall = mock(Call::class.java) as Call<FeedResponse>
        val mockCallback = mock(FeedRepository.MyCallback::class.java)
        val mockResponse = mock(Response::class.java) as Response<FeedResponse>

        `when`(mockRetrofitCall.enqueue(mockCallback)).thenAnswer {
            (it.getArgument<FeedRepository.MyCallback>(0)).onResponse(mockRetrofitCall, mockResponse)
        }

        feedRepository.fetchFeedDataFromServer2(mockRetrofitCall, mockCallback)

        verify(mockCallback).onResponse(mockRetrofitCall, mockResponse)
    }

    @Test
    fun sampleTest5() {
        val retrofitService = RetrofitRequest.getRetrofitFor(URLConstant.BASE_URL).create(FeedService::class.java)

        val mockRetrofitCall = mock(Call::class.java) as Call<FeedResponse>
        val mockCallback = mock(FeedRepository.MyCallback::class.java)
        val mockResponse = mock(Response::class.java) as Response<FeedResponse>

        feedRepository.fetchFeedDataFromServer2(mockRetrofitCall, mockCallback)

        val callback = feedRepository.MyCallback()

        callback.onResponse(mockRetrofitCall, mockResponse)

        val feedResponse = feedRepository.getFeedResponseObservable().getOrAwaitValue()
        Assert.assertNotNull(feedResponse)

        val progress = feedRepository.isLoadingDataObservable().getOrAwaitValue()
        Assert.assertEquals(false, progress)
    }

    @Test
    fun sampleTest6() {
        val retrofitService = RetrofitRequest.getRetrofitFor(URLConstant.BASE_URL).create(FeedService::class.java)

        val mockRetrofitCall = mock(Call::class.java) as Call<FeedResponse>
        val mockCallback = mock(FeedRepository.MyCallback::class.java)
        val mockResponse = mock(Response::class.java) as Response<FeedResponse>
        val mockThrowable = Throwable()

        feedRepository.fetchFeedDataFromServer2(mockRetrofitCall, mockCallback)

        val callback = feedRepository.MyCallback()

        callback.onFailure(mockRetrofitCall, mockThrowable)

        val feedResponse = feedRepository.getFeedResponseObservable().getOrAwaitValue()
        Assert.assertNull(feedResponse)

        val progress = feedRepository.isLoadingDataObservable().getOrAwaitValue()
        Assert.assertEquals(false, progress)
    }

    @Test
    fun sampleTest7() {
        val mockCallback = mock(FeedRepository.MyCallback::class.java)
        val mockResponse = mock(Response::class.java) as Response<FeedResponse>

        `when`(mockFeedCall.enqueue(mockCallback)).thenAnswer {
            (it.getArgument<FeedRepository.MyCallback>(0)).onResponse(mockFeedCall, mockResponse)
        }

        feedRepository.fetchFeedDataFromServer3(mockCallback)

        verify(mockCallback).onResponse(mockFeedCall, mockResponse)
    }*/
}