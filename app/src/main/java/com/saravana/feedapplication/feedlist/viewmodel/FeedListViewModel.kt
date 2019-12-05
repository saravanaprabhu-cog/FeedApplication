package com.saravana.feedapplication.feedlist.viewmodel

import androidx.lifecycle.ViewModel
import com.saravana.feedapplication.feedlist.repository.FeedRepository

class FeedListViewModel : ViewModel() {

    fun getFeedDataViewModel() = FeedRepository.getFeedResponseLiveData()

    fun isLoadingDataFromServer() = FeedRepository.isLoadingData()

    fun init() {
        FeedRepository.fetchFeedData()
    }

    fun onRefresh() {
        FeedRepository.fetchFeedData()
    }
}
