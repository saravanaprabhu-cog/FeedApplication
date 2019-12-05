package com.saravana.feedapplication.feedlist.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.saravana.feedapplication.databinding.ItemFeedBinding
import com.saravana.feedapplication.feedlist.model.Feed

class FeedItemViewHolder(private val itemFeedBinding: ItemFeedBinding) :
    RecyclerView.ViewHolder(itemFeedBinding.root) {

    fun bindView(feedItem: Feed) {
        itemFeedBinding.apply {
            feed = feedItem
            executePendingBindings()
        }
    }
}
