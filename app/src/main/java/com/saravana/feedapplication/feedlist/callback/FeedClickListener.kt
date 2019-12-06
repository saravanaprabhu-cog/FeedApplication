package com.saravana.feedapplication.feedlist.callback

import com.saravana.feedapplication.feedlist.model.Feed

interface FeedClickListener {
    fun onFeedClicked(feed: Feed)
}
