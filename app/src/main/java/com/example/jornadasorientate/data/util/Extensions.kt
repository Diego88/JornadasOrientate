package com.example.jornadasorientate.data.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(context: Context, url: String) {
    Glide.with(context)
        .load(url)
        .placeholder(android.R.drawable.ic_menu_gallery)
        .circleCrop()
        .into(this)
}
