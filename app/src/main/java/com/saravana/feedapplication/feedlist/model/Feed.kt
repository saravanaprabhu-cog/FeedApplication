package com.saravana.feedapplication.feedlist.model

import com.google.gson.annotations.SerializedName

data class Feed(
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("imageHref") val imageUrl: String
)
