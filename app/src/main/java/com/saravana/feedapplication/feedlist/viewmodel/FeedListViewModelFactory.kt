package com.saravana.feedapplication.feedlist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.saravana.feedapplication.feedlist.repository.FeedRepository

class FeedListViewModelFactory(private val feedRepository: FeedRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FeedListViewModel(feedRepository) as T
    }
}
