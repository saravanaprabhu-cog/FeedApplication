package com.saravana.feedapplication.feedlist.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.saravana.feedapplication.feedlist.api.FeedService
import com.saravana.feedapplication.feedlist.constant.URLConstant
import com.saravana.feedapplication.feedlist.model.FeedResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FeedRepository(private val feedService: FeedService) {

    private val feedResponseObservable = MutableLiveData<FeedResponse>()
    private val isLoadingDataObservable = MutableLiveData<Boolean>()

    fun getFeedResponseObservable(): LiveData<FeedResponse> {
        return feedResponseObservable
    }

    fun isLoadingDataObservable(): LiveData<Boolean> {
        return isLoadingDataObservable
    }

    fun fetchFeedDataFromServer(callback: FeedResponseHandler) {
        isLoadingDataObservable.value = true
        val call = feedService.getFeedData(URLConstant.FEED_PATH)
        call.enqueue(callback)
    }

    inner class FeedResponseHandler : Callback<FeedResponse> {
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
}
