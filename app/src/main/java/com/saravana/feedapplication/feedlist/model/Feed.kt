package com.saravana.feedapplication.feedlist.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Feed(
    @SerializedName("title") val title: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("imageHref") val imageUrl: String?
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readString(),
        source.readString(),
        source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(title)
        writeString(description)
        writeString(imageUrl)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Feed> = object : Parcelable.Creator<Feed> {
            override fun createFromParcel(source: Parcel): Feed = Feed(source)
            override fun newArray(size: Int): Array<Feed?> = arrayOfNulls(size)
        }
    }
}
