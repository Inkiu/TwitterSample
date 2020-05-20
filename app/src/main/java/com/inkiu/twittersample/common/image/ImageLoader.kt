package com.inkiu.twittersample.common.image

import android.widget.ImageView

interface ImageLoader {
    fun load(url: String,
             imageView: ImageView,
             callback: (Boolean) -> Unit = { }
    )
}