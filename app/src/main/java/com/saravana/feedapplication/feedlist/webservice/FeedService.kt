package com.saravana.feedapplication.feedlist.webservice

import com.saravana.feedapplication.feedlist.model.FeedResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface FeedService {
    @GET("{path}")
    fun getFeedData(@Path(value = "path") path: String): Call<FeedResponse>
}
