package com.saravana.feedapplication.feedlist.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.saravana.feedapplication.R
import com.saravana.feedapplication.databinding.ActivityFeedListBinding
import com.saravana.feedapplication.feedlist.adapter.FeedListAdapter
import com.saravana.feedapplication.feedlist.constant.BundleConstant
import com.saravana.feedapplication.feedlist.listener.FeedClickListener
import com.saravana.feedapplication.feedlist.model.Feed
import com.saravana.feedapplication.feedlist.repository.FeedRepository
import com.saravana.feedapplication.feedlist.viewmodel.FeedListViewModel
import com.saravana.feedapplication.feedlist.viewmodel.FeedListViewModelFactory
import kotlinx.android.synthetic.main.activity_feed_list.*

class FeedListActivity : AppCompatActivity(), FeedClickListener {

    private val feedAdapter = FeedListAdapter(this)
    private val feedRepository = FeedRepository()
    private lateinit var activityFeedListBinding: ActivityFeedListBinding
    private lateinit var feedListViewModel: FeedListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed_list)
        createDataBinder()
        createViewModel()
        setObservers()
        setupDataBinding()

        if (savedInstanceState == null) {
            initViewModel()
        }
    }

    private fun createDataBinder() {
        activityFeedListBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_feed_list
        )
    }

    private fun createViewModel() {
        feedListViewModel = ViewModelProviders.of(
            this,
            FeedListViewModelFactory(feedRepository)
        )[FeedListViewModel::class.java]
    }

    private fun setObservers() {
        feedListViewModel.isLoadingDataFromServer().observe(this, Observer {
            feedListSwipeRefreshLayout.isRefreshing = it
        })

        feedListViewModel.getFeedDataViewModel().observe(this, Observer {
            if (!it.feedTitle.isNullOrEmpty()) {
                it.feedTitle?.let { title -> setScreenTitle(title) }
            } else {
                setScreenTitle(getString(R.string.feed_title_unavailable))
            }
            it.feedList?.let { list -> setFeedList(list) }
        })
    }

    private fun setupDataBinding() {
        activityFeedListBinding.viewmodel = feedListViewModel
        activityFeedListBinding.feedadapter = feedAdapter
    }

    private fun initViewModel() {
        feedListViewModel.init()
    }

    private fun setScreenTitle(title: String) {
        supportActionBar?.title = title
    }

    private fun setFeedList(list: ArrayList<Feed>) {
        feedAdapter.setItems(list)
    }

    override fun onFeedClicked(feed: Feed) {
        val feedDetailIntent = Intent(this@FeedListActivity, FeedDetailActivity::class.java).apply {
            putExtra(BundleConstant.KEY_FEED, feed)
        }
        startActivity(feedDetailIntent)
    }
}
