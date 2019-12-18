package com.saravana.feedapplication.feedlist.repository

import com.saravana.feedapplication.common.webservice.RetrofitRequest
import com.saravana.feedapplication.feedlist.api.FeedService
import com.saravana.feedapplication.feedlist.constant.URLConstant
import com.saravana.feedapplication.feedlist.listener.FeedResponseListener
import com.saravana.feedapplication.feedlist.model.FeedResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FeedRepository {

    private val retrofitService =
        RetrofitRequest.getRetrofitFor(URLConstant.BASE_URL).create(FeedService::class.java)


    fun fetchFeedData(feedResponseListener: FeedResponseListener) {
        fetchFeedDataFromServer(feedResponseListener)
    }

    private fun fetchFeedDataFromServer(feedResponseListener: FeedResponseListener) {
        val call = retrofitService.getFeedData(URLConstant.FEED_PATH)

        call.enqueue(object : Callback<FeedResponse> {
            override fun onFailure(call: Call<FeedResponse>, t: Throwable) {
                feedResponseListener.onFeedResponse(FeedResponse("", ArrayList()))
            }

            override fun onResponse(call: Call<FeedResponse>, response: Response<FeedResponse>) {
                val feedResponse = response.body()
                if (feedResponse != null) {
                    feedResponseListener.onFeedResponse(feedResponse)
                } else {
                    feedResponseListener.onFeedResponse(FeedResponse("", ArrayList()))
                }
            }
        })
    }
}
