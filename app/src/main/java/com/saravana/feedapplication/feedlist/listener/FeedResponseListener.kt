package com.saravana.feedapplication.feedlist.listener

import com.saravana.feedapplication.feedlist.model.FeedResponse

interface FeedResponseListener {
    fun onFeedResponse(feedResponse: FeedResponse)
}