package com.saravana.feedapplication.feedlist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.saravana.feedapplication.feedlist.listener.FeedResponseListener
import com.saravana.feedapplication.feedlist.model.FeedResponse
import com.saravana.feedapplication.feedlist.repository.FeedRepository

class FeedListViewModel(private val feedRepository: FeedRepository) : ViewModel() {

    private val feedResponseLiveData = MutableLiveData<FeedResponse>()
    private val isLoadingData = MutableLiveData<Boolean>()

    fun getFeedResponseObservable(): LiveData<FeedResponse> {
        return feedResponseLiveData
    }

    fun isLoadingDataObservable(): LiveData<Boolean> {
        return isLoadingData
    }

    fun init() {
        fetchFeedData()
    }

    fun onRefresh() {
        fetchFeedData()
    }

    private fun fetchFeedData() {
        isLoadingData.value = true
        feedRepository.fetchFeedData(object : FeedResponseListener {
            override fun onFeedResponse(feedResponse: FeedResponse) {
                isLoadingData.value = false
                feedResponseLiveData.value = feedResponse
            }
        })
    }
}
