package com.saravana.feedapplication.feedlist.model

import com.google.gson.annotations.SerializedName

data class FeedResponse(
    @SerializedName("title") val feedTitle: String?,
    @SerializedName("rows") val feedList: ArrayList<Feed>?
) {
    fun isValidFeedTitle(): Boolean {
        feedTitle?.let {
            return !(it == "null" || it == "")
        }
        return false
    }
}
