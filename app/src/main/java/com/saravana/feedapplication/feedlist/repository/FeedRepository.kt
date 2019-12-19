package com.saravana.feedapplication.feedlist.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.saravana.feedapplication.common.webservice.RetrofitRequest
import com.saravana.feedapplication.feedlist.api.FeedService
import com.saravana.feedapplication.feedlist.constant.URLConstant
import com.saravana.feedapplication.feedlist.model.FeedResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FeedRepository(private val feedService: FeedService, private val feedCall: Call<FeedResponse>) {

    private val retrofitService = RetrofitRequest.getRetrofitFor(URLConstant.BASE_URL).create(FeedService::class.java)

    private val feedResponseObservable = MutableLiveData<FeedResponse>()
    private val isLoadingDataObservable = MutableLiveData<Boolean>()


    init {
        feedResponseObservable.value = FeedResponse("", ArrayList())
        isLoadingDataObservable.value = false
    }

    fun getFeedResponseObservable(): LiveData<FeedResponse> {
        return feedResponseObservable
    }

    fun isLoadingDataObservable(): LiveData<Boolean> {
        return isLoadingDataObservable
    }

    fun fetchFeedDataFromServer3(callback: MyCallback) {
        isLoadingDataObservable.value = true
        feedCall.enqueue(callback)
    }

    inner class MyCallback : Callback<FeedResponse> {
        override fun onFailure(call: Call<FeedResponse>, t: Throwable) {
            feedResponseObservable.value = null
            isLoadingDataObservable.value = false
        }

        override fun onResponse(call: Call<FeedResponse>, response: Response<FeedResponse>) {
            val feedResponse = response.body()
            if (feedResponse != null) {
                feedResponseObservable.value = feedResponse
            } else {
                feedResponseObservable.value = FeedResponse("", ArrayList())
            }
            isLoadingDataObservable.value = false
        }
    }

    /*fun fetchFeedDataFromServer() {
        val call = retrofitService.getFeedData(URLConstant.FEED_PATH)
        isLoadingDataObservable.value = true
        call.enqueue(object : Callback<FeedResponse> {
            override fun onFailure(call: Call<FeedResponse>, t: Throwable) {
                feedResponseObservable.value = FeedResponse("", ArrayList())
                isLoadingDataObservable.value = false
            }

            override fun onResponse(call: Call<FeedResponse>, response: Response<FeedResponse>) {
                val feedResponse = response.body()
                if (feedResponse != null) {
                    feedResponseObservable.value = feedResponse
                } else {
                    feedResponseObservable.value = FeedResponse("", ArrayList())
                }
                isLoadingDataObservable.value = false
            }
        })
    }

    fun fetchFeedDataFromServer(retrofitCall: Call<FeedResponse>, callback: Callback<FeedResponse>) {
        isLoadingDataObservable.value = true
        retrofitCall.enqueue(callback)
    }

    fun fetchFeedDataFromServer2(retrofitCall: Call<FeedResponse>, callback: MyCallback) {
        isLoadingDataObservable.value = true
        retrofitCall.enqueue(callback)
    }

    private fun getFeedServiceCall(retrofit: FeedService) = retrofit.getFeedData(URLConstant.FEED_PATH)*/
}
