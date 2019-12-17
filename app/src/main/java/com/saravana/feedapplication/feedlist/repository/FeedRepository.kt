package com.saravana.feedapplication.feedlist.repository

import androidx.lifecycle.MutableLiveData
import com.saravana.feedapplication.common.webservice.RetrofitRequest
import com.saravana.feedapplication.feedlist.api.FeedService
import com.saravana.feedapplication.feedlist.constant.URLConstant
import com.saravana.feedapplication.feedlist.model.FeedResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FeedRepository {
    private val feedResponseLiveData = MutableLiveData<FeedResponse>()
    private val isLoadingData = MutableLiveData<Boolean>()

    fun getFeedResponseLiveData() = feedResponseLiveData
    fun isLoadingData() = isLoadingData

    fun fetchFeedData() {
        fetchFeedDataFromServer()
    }

    private fun fetchFeedDataFromServer() {
        isLoadingData.value = true

        val service = RetrofitRequest.getRetrofitFor(URLConstant.BASE_URL).create(FeedService::class.java)
        val call = service.getFeedData(URLConstant.FEED_PATH)

        call.enqueue(object : Callback<FeedResponse> {
            override fun onFailure(call: Call<FeedResponse>, t: Throwable) {
                feedResponseLiveData.value = FeedResponse("No Title", ArrayList())
                isLoadingData.value = false
            }

            override fun onResponse(call: Call<FeedResponse>, response: Response<FeedResponse>) {
                val feedResponse = response.body()
                feedResponseLiveData.value = feedResponse
                isLoadingData.value = false
            }
        })
    }
}
