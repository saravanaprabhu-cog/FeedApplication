package com.saravana.feedapplication.feedlist.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.saravana.feedapplication.R
import com.saravana.feedapplication.databinding.ActivityFeedListBinding
import com.saravana.feedapplication.feedlist.adapter.FeedListAdapter
import com.saravana.feedapplication.feedlist.model.Feed
import com.saravana.feedapplication.feedlist.viewmodel.FeedListViewModel
import kotlinx.android.synthetic.main.activity_feed_list.*

class FeedListActivity : AppCompatActivity() {

    private val feedAdapter = FeedListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed_list)

        val activityBinding: ActivityFeedListBinding =
            DataBindingUtil.setContentView(
                this,
                R.layout.activity_feed_list
            )

        val viewModel = ViewModelProviders.of(this)[FeedListViewModel::class.java]
        activityBinding.viewmodel = viewModel

        viewModel.isLoadingDataFromServer().observe(this, Observer {
            feedListSwipeRefreshLayout.isRefreshing = it
        })

        viewModel.getFeedDataViewModel().observe(this, Observer {
            setScreenTitle(it.feedTitle)
            setFeedList(it.feedList)
        })
        activityBinding.feedadapter = feedAdapter

        if (savedInstanceState == null) {
            viewModel.init()
        }
    }

    private fun setScreenTitle(title: String) {
        supportActionBar?.title = title
    }

    private fun setFeedList(list: ArrayList<Feed>) {
        feedAdapter.setItems(list)
    }
}
