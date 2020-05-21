package com.inkiu.twittersample.common.image

import android.widget.ImageView
import androidx.annotation.DrawableRes

interface ImageLoader {

    fun loadCircleProfile(
        url: String,
        imageView: ImageView,
        @DrawableRes placeHolder: Int = -1,
        callback: (Boolean) -> Unit = { }
    )

    fun load(
        url: String,
        imageView: ImageView,
        callback: (Boolean) -> Unit = { }
    )
}