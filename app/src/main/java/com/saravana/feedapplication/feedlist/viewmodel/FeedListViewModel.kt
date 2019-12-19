package com.saravana.feedapplication.feedlist.viewmodel

import androidx.lifecycle.ViewModel
import com.saravana.feedapplication.feedlist.repository.FeedRepository

class FeedListViewModel(private val feedRepository: FeedRepository) : ViewModel() {

    fun getFeedResponseObservable() = feedRepository.getFeedResponseObservable()

    fun isLoadingDataObservable() = feedRepository.isLoadingDataObservable()

    fun init() {
        feedRepository.fetchFeedDataFromServer3(feedRepository.MyCallback())
    }

    fun onRefresh() {
        feedRepository.fetchFeedDataFromServer3(feedRepository.MyCallback())
    }
}
