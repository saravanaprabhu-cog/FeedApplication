package com.saravana.feedapplication.feedlist.listener

import com.saravana.feedapplication.feedlist.model.Feed

interface FeedClickListener {
    fun onFeedClicked(feed: Feed)
}
