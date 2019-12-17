package com.saravana.feedapplication.feedlist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.saravana.feedapplication.feedlist.model.Feed

class FeedDetailViewModelFactory(private val feed: Feed) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FeedDetailViewModel(feed) as T
    }
}
