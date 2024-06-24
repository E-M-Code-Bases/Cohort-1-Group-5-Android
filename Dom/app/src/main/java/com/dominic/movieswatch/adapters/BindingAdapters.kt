package com.dominic.movieswatch.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.dominic.movieswatch.R

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, imageUrl: String?) {
    Glide.with(view.context)
        .load("https://image.tmdb.org/t/p/w500/$imageUrl")
        .placeholder(R.drawable.ic_placeholder_image)
        .into(view)
}
