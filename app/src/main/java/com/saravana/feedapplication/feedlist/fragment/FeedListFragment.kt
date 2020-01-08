package com.saravana.feedapplication.feedlist.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.saravana.feedapplication.R
import com.saravana.feedapplication.databinding.FragmentFeedListBinding
import com.saravana.feedapplication.feedlist.adapter.FeedListAdapter
import com.saravana.feedapplication.feedlist.listener.FeedClickListener
import com.saravana.feedapplication.feedlist.model.Feed
import com.saravana.feedapplication.feedlist.repository.FeedRepositoryFactory
import com.saravana.feedapplication.feedlist.viewmodel.FeedListViewModel
import com.saravana.feedapplication.feedlist.viewmodel.FeedListViewModelFactory
import kotlinx.android.synthetic.main.fragment_feed_list.*

class FeedListFragment : Fragment(), FeedClickListener {

    private val feedAdapter = FeedListAdapter(this)
    private val feedRepository = FeedRepositoryFactory.provideFeedRepository()
    private lateinit var fragmentFeedListBinding: FragmentFeedListBinding
    private lateinit var feedListViewModel: FeedListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        createDataBinder()
        createViewModel()
        setObservers()
        setupDataBinding()

        if (savedInstanceState == null) {
            initViewModel()
        }
        return inflater.inflate(R.layout.fragment_feed_list, container, false)
    }

    private fun createDataBinder() {
        fragmentFeedListBinding = DataBindingUtil.setContentView(
            requireActivity(),
            R.layout.fragment_feed_list
        )
    }

    private fun createViewModel() {
        feedListViewModel = ViewModelProviders.of(
            this,
            FeedListViewModelFactory(feedRepository)
        )[FeedListViewModel::class.java]
    }

    private fun setObservers() {
        feedListViewModel.isLoadingDataObservable().observe(this, Observer {
            feedListSwipeRefreshLayout.isRefreshing = it
        })

        feedListViewModel.getFeedResponseObservable().observe(this, Observer {
            if (it == null) {
                setScreenTitle(getString(R.string.feed_title_unavailable))
                setFeedList(arrayListOf())
            } else {
                if (it.feedTitle.isNullOrEmpty()) {
                    setScreenTitle(getString(R.string.feed_title_unavailable))
                } else {
                    setScreenTitle(it.feedTitle)
                }

                if (it.feedList.isNullOrEmpty()) {
                    setFeedList(arrayListOf())
                } else {
                    setFeedList(it.feedList)
                }
            }
        })
    }

    private fun setupDataBinding() {
        fragmentFeedListBinding.viewmodel = feedListViewModel
        fragmentFeedListBinding.feedadapter = feedAdapter
    }

    private fun initViewModel() {
        feedListViewModel.init()
    }

    private fun setScreenTitle(title: String) {
        (activity as AppCompatActivity).supportActionBar?.title = title
    }

    private fun setFeedList(list: ArrayList<Feed>) {
        feedAdapter.setItems(list)
    }

    override fun onFeedClicked(feed: Feed) {
        val action = FeedListFragmentDirections.actionFeedListFragmentToFeedDetailFragment(feed)
        view?.findNavController()?.navigate(action)
    }
}
