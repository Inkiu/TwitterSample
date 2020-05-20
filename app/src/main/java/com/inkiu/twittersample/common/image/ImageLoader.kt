package com.inkiu.twittersample.common.image

import android.widget.ImageView

interface ImageLoader {

    fun loadCircleProfile(
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