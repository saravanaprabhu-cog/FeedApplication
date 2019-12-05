package com.saravana.feedapplication.feedlist.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.saravana.feedapplication.R

@BindingAdapter("lazyLoadFromUrl")
fun loadImageFromUrl(imageView: ImageView, url: String?) {
    Glide.with(imageView.context)
        .load(url)
        .fitCenter()
        .placeholder(R.drawable.ic_fallback_image_dark)
        .error(R.drawable.ic_broken_image_dark)
        .fallback(R.drawable.ic_fallback_image_dark)
        .into(imageView)
}
