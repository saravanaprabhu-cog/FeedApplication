package com.saravana.feedapplication.feedlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.saravana.feedapplication.R
import com.saravana.feedapplication.databinding.ItemFeedBinding
import com.saravana.feedapplication.feedlist.adapter.viewholder.FeedItemViewHolder
import com.saravana.feedapplication.feedlist.listener.FeedClickListener
import com.saravana.feedapplication.feedlist.model.Feed

class FeedListAdapter(private val feedClickListener: FeedClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mItems = arrayListOf<Feed>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val feedItemBinding: ItemFeedBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_feed,
            parent,
            false
        )
        return FeedItemViewHolder(feedItemBinding, feedClickListener)
    }

    override fun getItemCount() = mItems.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as FeedItemViewHolder).bindView(mItems[position])
    }

    fun setItems(items: ArrayList<Feed>) {
        mItems = items
        notifyDataSetChanged()
    }
}
