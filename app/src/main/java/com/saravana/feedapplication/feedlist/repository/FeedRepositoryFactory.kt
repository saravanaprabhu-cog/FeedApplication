package com.saravana.feedapplication.feedlist.repository

import com.saravana.feedapplication.common.webservice.RetrofitRequest
import com.saravana.feedapplication.feedlist.api.FeedService
import com.saravana.feedapplication.feedlist.constant.URLConstant

object FeedRepositoryFactory {

    fun provideFeedRepository(): FeedRepository {
        val retrofitService = RetrofitRequest.getRetrofitFor(URLConstant.BASE_URL).create(FeedService::class.java)
        val call = retrofitService.getFeedData(URLConstant.FEED_PATH)
        return FeedRepository(retrofitService, call)
    }

}
