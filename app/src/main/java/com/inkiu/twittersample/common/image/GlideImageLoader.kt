package com.inkiu.twittersample.common.image

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.inkiu.twittersample.R
import javax.inject.Inject
import javax.inject.Named

class GlideImageLoader @Inject constructor(
    @Named("ActivityContext") context: Context
) : ImageLoader {

    val roundRequestOptions = RequestOptions
        .bitmapTransform(CircleCrop())

    private val glide = Glide.with(context)

    override fun loadCircle(url: String, imageView: ImageView, callback: (Boolean) -> Unit) {
        glide.load(url)
            .apply(roundRequestOptions)
            .listener(GlideListener(callback))
            .into(imageView)
    }

    override fun load(url: String, imageView: ImageView, callback: (Boolean) -> Unit) {
        glide.load(url)
            .listener(GlideListener(callback))
            .into(imageView)
    }

    private class GlideListener(val delegate: (Boolean) -> Unit): RequestListener<Drawable> {
        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<Drawable>?,
            isFirstResource: Boolean
        ): Boolean {
            delegate(false)
            return false
        }

        override fun onResourceReady(
            resource: Drawable?,
            model: Any?,
            target: Target<Drawable>?,
            dataSource: DataSource?,
            isFirstResource: Boolean
        ): Boolean {
            delegate(true)
            return false
        }
    }
}