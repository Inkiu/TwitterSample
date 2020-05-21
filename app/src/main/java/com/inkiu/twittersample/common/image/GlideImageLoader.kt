package com.inkiu.twittersample.common.image

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.inkiu.twittersample.R
import javax.inject.Inject
import javax.inject.Named

class GlideImageLoader @Inject constructor(
    @Named("ApplicationContext") context: Context
) : ImageLoader {

    private val profileRequestOption = RequestOptions
        .bitmapTransform(CircleCrop())
        .placeholder(R.drawable.ic_face_24dp)
        .error(R.drawable.ic_help_24dp)

    private val glide = Glide.with(context)

    override fun loadCircleProfile(url: String, imageView: ImageView, callback: (Boolean) -> Unit) {
        val thumbnail = glide.load(url).apply(profileRequestOption).thumbnail(0.25f)
        glide.load(url)
            .apply(profileRequestOption)
            .transition(withCrossFade())
            .thumbnail(thumbnail)
            .into(imageView)
    }

    override fun load(url: String, imageView: ImageView, callback: (Boolean) -> Unit) {
        glide.load(url)
            .transition(withCrossFade())
            .thumbnail(glide.load(url).thumbnail(0.25f))
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