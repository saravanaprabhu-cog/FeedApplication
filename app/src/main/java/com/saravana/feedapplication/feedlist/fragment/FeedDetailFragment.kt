package com.saravana.feedapplication.feedlist.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.saravana.feedapplication.R
import com.saravana.feedapplication.databinding.FragmentFeedDetailBinding
import com.saravana.feedapplication.feedlist.model.Feed

class FeedDetailFragment : Fragment() {

    private lateinit var fragmentFeedDetailBinding: FragmentFeedDetailBinding
    private lateinit var feed: Feed

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        feed = FeedDetailFragmentArgs.fromBundle(arguments!!).feed

        createDataBinder(inflater, container)
        setupDataBinding()
        setupValues()
        return fragmentFeedDetailBinding.root
    }

    private fun createDataBinder(inflater: LayoutInflater, container: ViewGroup?) {
        fragmentFeedDetailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_feed_detail, container, false)
    }

    private fun setupDataBinding() {
        fragmentFeedDetailBinding.feed = feed
    }

    private fun setupValues() {
        if (!feed.title.isNullOrEmpty()) {
            feed.title?.let { setScreenTitle(it) }
        } else {
            setScreenTitle(getString(R.string.feed_title_unavailable))
        }
    }

    private fun setScreenTitle(title: String) {
        (activity as AppCompatActivity).supportActionBar?.title = title
    }
}
