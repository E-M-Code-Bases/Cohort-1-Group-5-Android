package com.dominic.movieswatch.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.dominic.movieswatch.R

//@BindingAdapter("imageUrl")
//fun setImageUrl(view: ImageView, url: String?) {
//    if (!url.isNullOrEmpty()) {
//        Glide.with(view.context)
//            .load(url)
//            .into(view)
//    }
//}
@BindingAdapter("posterPath")
fun bindImage(imageView: ImageView, posterPath: String?) {
    val fullPosterPath = posterPath?.let { "https://image.tmdb.org/t/p/w500$it" }
    Glide.with(imageView.context)
        .load(fullPosterPath)
        .placeholder(R.drawable.ic_placeholder_image)
        .error(R.drawable.ic_placeholder_image)
        .into(imageView)
}


@BindingAdapter("favoriteIcon")
fun setFavoriteIcon(view: ImageView, isFavorite: Boolean) {
    val drawable = if (isFavorite) {
        R.drawable.ic_favorite
    } else {
        R.drawable.ic_favorite_border
    }
    view.setImageResource(drawable)
}