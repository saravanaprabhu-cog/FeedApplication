package com.saravana.feedapplication.feedlist.model

import com.google.gson.annotations.SerializedName

data class FeedResponse(
    @SerializedName("title") val feedTitle: String,
    @SerializedName("rows") val feedList: ArrayList<Feed>
)
