package com.inkiu.twittersample.ui.common.tweets

import android.view.View
import com.inkiu.twittersample.R
import com.inkiu.twittersample.common.image.ImageLoader
import com.inkiu.twittersample.ui.common.model.*
import kotlinx.android.synthetic.main.item_list_tweet_media.view.*
import kotlinx.android.synthetic.main.item_list_tweet_profile.view.*
import kotlinx.android.synthetic.main.item_list_tweet_retweet.view.*

object MediaBindingDelegate {
    fun bind(parent: View, media: Media, imageLoader: ImageLoader) {
        with(parent) {
            visibility = View.VISIBLE
            when (media) {
                is PhotosMedia -> {
                    mediaMultiImageView.visibility = View.VISIBLE
                    singleMediaContainer.visibility = View.GONE
                    val urls = media.photos.map { it.sourceUrl }
                        .toTypedArray()
                    mediaMultiImageView.putImages(*urls)
                }
                is AnimatedMedia -> {
                    mediaMultiImageView.visibility =View.GONE
                    singleMediaContainer.visibility = View.VISIBLE
                    imageLoader.load(media.sourceUrl, mediaImageView)
                    mediaTipImageView.setImageResource(R.drawable.ic_gif)
                }
                is VideoMedia -> {
                    mediaMultiImageView.visibility =View.GONE
                    singleMediaContainer.visibility = View.VISIBLE
                    imageLoader.load(media.sourceUrl, mediaImageView)
                    mediaTipImageView.setImageResource(R.drawable.ic_play_circle)
                }
                else -> {
                    visibility = View.GONE
                }
            }
        }
    }
}

object QuotedBindingDelegate {
    fun bind(parent: View, quoted: Quoted, imageLoader: ImageLoader) {
        with(parent) {
            visibility = View.VISIBLE
            imageLoader.loadCircle(quoted.user.profileUrl, profileImage)
            profileDisplayName.text = quoted.content
            profileVerified.visibility = if (quoted.user.verified) View.VISIBLE else View.GONE
            profileName.text = quoted.user.name
            tweetCreated.text = quoted.createdDate.toLocaleString()
        }
    }
}