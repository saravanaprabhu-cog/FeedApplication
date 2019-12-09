package com.saravana.feedapplication.feedlist.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.saravana.feedapplication.R
import com.saravana.feedapplication.databinding.ActivityFeedDetailBinding
import com.saravana.feedapplication.feedlist.constant.BundleConstant
import com.saravana.feedapplication.feedlist.model.Feed

class FeedDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed_detail)

        val activityBinding = DataBindingUtil.setContentView<ActivityFeedDetailBinding>(
            this,
            R.layout.activity_feed_detail
        )

        val feed: Feed = intent.getParcelableExtra(BundleConstant.KEY_FEED)
        if (feed.isValidTitle()) {
            feed.title?.let { setScreenTitle(it) }
        } else {
            setScreenTitle(getString(R.string.feed_title_unavailable))
        }
        activityBinding.feed = feed
    }

    private fun setScreenTitle(title: String) {
        supportActionBar?.title = title
    }
}
