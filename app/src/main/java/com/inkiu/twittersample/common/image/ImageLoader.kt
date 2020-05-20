package com.inkiu.twittersample.common.image

import android.widget.ImageView

interface ImageLoader {

    fun loadCircle(
        url: String,
        imageView: ImageView,
        callback: (Boolean) -> Unit = { }
    )

    fun load(
        url: String,
        imageView: ImageView,
        callback: (Boolean) -> Unit = { }
    )
}