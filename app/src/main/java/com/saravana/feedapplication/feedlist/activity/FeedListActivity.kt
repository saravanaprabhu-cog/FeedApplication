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
import com.saravana.feedapplication.feedlist.callback.FeedClickListener
import com.saravana.feedapplication.feedlist.model.Feed
import com.saravana.feedapplication.feedlist.viewmodel.FeedListViewModel
import kotlinx.android.synthetic.main.activity_feed_list.*

class FeedListActivity : AppCompatActivity(), FeedClickListener {

    private val feedAdapter = FeedListAdapter(this)

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
            it.feedTitle?.let { it1 -> setScreenTitle(it1) }
            it.feedList?.let { it1 -> setFeedList(it1) }
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

    override fun onFeedClicked(feed: Feed) {
        val feedDetailIntent = Intent(this@FeedListActivity, FeedDetailActivity::class.java)
        feedDetailIntent.putExtra("feed", feed)
        startActivity(feedDetailIntent)
    }
}
