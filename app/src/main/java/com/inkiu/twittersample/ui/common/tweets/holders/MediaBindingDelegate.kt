package com.inkiu.twittersample.ui.common.tweets.holders

import android.view.View
import com.inkiu.twittersample.common.image.ImageLoader
import com.inkiu.twittersample.ui.common.model.Media
import com.inkiu.twittersample.ui.common.model.PhotosMedia
import com.inkiu.twittersample.ui.common.model.SingleMedia
import kotlinx.android.synthetic.main.item_list_tweet_media.view.*

object MediaBindingDelegate {
    fun bind(parent: View, media: Media, imageLoader: ImageLoader) {
        with(parent) {
            visibility = View.VISIBLE
            when (media) {
                is PhotosMedia -> {
                    mediaMultiImageView.visibility = View.VISIBLE
                    mediaImageView.visibility = View.GONE
                    val urls = media.photos.map { it.sourceUrl }
                        .toTypedArray()
                    mediaMultiImageView.putImages(*urls)
                }
                is SingleMedia -> {
                    mediaMultiImageView.visibility =View.GONE
                    mediaImageView.visibility = android.view.View.VISIBLE
                    imageLoader.load(media.sourceUrl, mediaImageView)
                }
                else -> {
                    visibility = View.GONE
                }
            }
        }
    }
}