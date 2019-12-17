package com.saravana.feedapplication.feedlist.viewmodel

import androidx.lifecycle.ViewModel
import com.saravana.feedapplication.feedlist.repository.FeedRepository

class FeedListViewModel(private val feedRepository: FeedRepository) : ViewModel() {

    fun getFeedDataViewModel() = feedRepository.getFeedResponseLiveData()

    fun isLoadingDataFromServer() = feedRepository.isLoadingData()

    fun init() {
        feedRepository.fetchFeedData()
    }

    fun onRefresh() {
        feedRepository.fetchFeedData()
    }

}
