package com.saravana.feedapplication.feedlist.viewmodel

import androidx.lifecycle.ViewModel
import com.saravana.feedapplication.feedlist.model.Feed

class FeedDetailViewModel(private val feed: Feed) : ViewModel() {

    fun getFeed() = feed

}
