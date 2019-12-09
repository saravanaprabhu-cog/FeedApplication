package com.saravana.feedapplication.feedlist.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Feed(
    @SerializedName("title") val title: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("imageHref") val imageUrl: String?
) : Parcelable {
    fun isValidTitle(): Boolean {
        title?.let {
            return !(it == "null" || it == "")
        }
        return false
    }

    fun isValidDescription(): Boolean {
        description?.let {
            return !(it == "null" || it == "")
        }
        return false
    }
}
