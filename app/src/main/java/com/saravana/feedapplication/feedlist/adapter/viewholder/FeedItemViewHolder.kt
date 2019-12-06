package com.saravana.feedapplication.feedlist.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.saravana.feedapplication.databinding.ItemFeedBinding
import com.saravana.feedapplication.feedlist.listener.FeedClickListener
import com.saravana.feedapplication.feedlist.model.Feed

class FeedItemViewHolder(
    private val itemFeedBinding: ItemFeedBinding,
    private val feedClickListener: FeedClickListener
) :
    RecyclerView.ViewHolder(itemFeedBinding.root) {

    fun bindView(feedItem: Feed) {
        itemFeedBinding.apply {
            feed = feedItem
            feedclicklistener = feedClickListener
            executePendingBindings()
        }
    }
}
