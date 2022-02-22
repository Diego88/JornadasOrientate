package com.example.jornadasorientate.data.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadImage(context: Context, url: String, options: RequestOptions? = null) {
    Glide.with(context)
        .load(url)
        .placeholder(android.R.drawable.ic_menu_gallery)
        .apply {
            options?.let(this::apply)
        }
        .into(this)
}
