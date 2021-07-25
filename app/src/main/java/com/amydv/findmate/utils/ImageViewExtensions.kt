package com.amydv.findmate.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

/**
 * Loads image from the url.
 * @param imgUrl string url
 */
fun ImageView.loadImage(imgUrl: String) {
    GlideApp.with(this)
        .load(imgUrl)
        .circleCrop()
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)
}