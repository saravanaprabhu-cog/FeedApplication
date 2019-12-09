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
import com.saravana.feedapplication.feedlist.viewmodel.FeedListViewModel
import kotlinx.android.synthetic.main.activity_feed_list.*

class FeedListActivity : AppCompatActivity(), FeedClickListener {

    private val feedAdapter = FeedListAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed_list)

        val activityFeedListBinding: ActivityFeedListBinding =
            DataBindingUtil.setContentView(
                this,
                R.layout.activity_feed_list
            )

        val feedListViewModel = ViewModelProviders.of(this)[FeedListViewModel::class.java]
        activityFeedListBinding.viewmodel = feedListViewModel

        feedListViewModel.isLoadingDataFromServer().observe(this, Observer {
            feedListSwipeRefreshLayout.isRefreshing = it
        })

        feedListViewModel.getFeedDataViewModel().observe(this, Observer {
            it.feedTitle?.let { title -> setScreenTitle(title) }
            it.feedList?.let { list -> setFeedList(list) }
        })
        activityFeedListBinding.feedadapter = feedAdapter

        if (savedInstanceState == null) {
            feedListViewModel.init()
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
        feedDetailIntent.putExtra(BundleConstant.KEY_FEED, feed)
        startActivity(feedDetailIntent)
    }
}
