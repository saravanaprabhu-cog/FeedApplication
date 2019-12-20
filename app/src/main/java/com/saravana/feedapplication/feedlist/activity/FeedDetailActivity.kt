package com.saravana.feedapplication.feedlist.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.saravana.feedapplication.R
import com.saravana.feedapplication.databinding.ActivityFeedDetailBinding
import com.saravana.feedapplication.feedlist.constant.BundleConstant
import com.saravana.feedapplication.feedlist.model.Feed
import com.saravana.feedapplication.feedlist.viewmodel.FeedDetailViewModel
import com.saravana.feedapplication.feedlist.viewmodel.FeedDetailViewModelFactory

class FeedDetailActivity : AppCompatActivity() {

    private lateinit var activityFeedDetailBinding: ActivityFeedDetailBinding
    private lateinit var feedDetailViewModel: FeedDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed_detail)
        createDataBinder()
        createViewModel()
        setupDataBinding()
        setupValues()
    }

    private fun createDataBinder() {
        activityFeedDetailBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_feed_detail
        )
    }

    private fun createViewModel() {
        val feed: Feed = intent.getParcelableExtra(BundleConstant.KEY_FEED)

        feedDetailViewModel = ViewModelProviders.of(
            this,
            FeedDetailViewModelFactory(feed)
        )[FeedDetailViewModel::class.java]
    }

    private fun setupDataBinding() {
        activityFeedDetailBinding.feed = feedDetailViewModel.getFeed()
    }

    private fun setupValues() {
        if (!feedDetailViewModel.getFeed().title.isNullOrEmpty()) {
            feedDetailViewModel.getFeed().title?.let { setScreenTitle(it) }
        } else {
            setScreenTitle(getString(R.string.feed_title_unavailable))
        }
    }

    private fun setScreenTitle(title: String) {
        supportActionBar?.title = title
    }
}
