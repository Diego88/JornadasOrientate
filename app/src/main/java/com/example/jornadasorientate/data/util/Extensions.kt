package com.example.jornadasorientate.data.util

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(url: String) {
    Glide.with(context)
        .load(url)
        .placeholder(android.R.drawable.ic_menu_gallery)
        .circleCrop()
        .into(this)
}
