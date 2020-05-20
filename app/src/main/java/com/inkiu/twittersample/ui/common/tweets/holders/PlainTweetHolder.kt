package com.inkiu.twittersample.ui.common.tweets.holders

import android.util.Log
import android.view.View
import com.inkiu.twittersample.R
import com.inkiu.twittersample.common.image.ImageLoader
import com.inkiu.twittersample.ui.common.tweets.TweetViewHolder
import com.inkiu.twittersample.ui.common.model.Tweet
import kotlinx.android.synthetic.main.item_list_tweet.view.*
import kotlinx.android.synthetic.main.item_list_tweet_counts.view.*
import kotlinx.android.synthetic.main.item_list_tweet_profile.view.*

open class PlainTweetHolder(
    view: View
) : TweetViewHolder<Tweet>(view) {
    override fun bind(item: Tweet, imageLoader: ImageLoader) {
        bindProfile(item, imageLoader)
        bindContent(item)
        bindCounts(item)
    }

    // TODO date
    private fun bindProfile(item: Tweet, imageLoader: ImageLoader) {
        with(itemView) {
            imageLoader.load(item.user.profileUrl, profileImage) { }
        }
        with(itemView.tweetProfileContainer) {
            profileDisplayName.text = item.user.displayName
            profileVerified.visibility = if (item.user.verified) View.VISIBLE else View.GONE
            profileName.text = item.user.name
            tweetCreated.text = item.createdDate.toLocaleString()
        }
    }

    private fun bindContent(item: Tweet) {
        with(itemView) {
            tweetContent.text = item.content
        }
    }

    private fun bindCounts(item: Tweet) {
        with(itemView.tweetCountContainer) {
            tweetCommentCount.text = "${item.commentCount}"
            tweetRetweetCount.text = "${item.reTweetCount}"
            tweetLikedCount.text = "${item.likeCount}"

            tweetRetweetImage.setImageResource(
                if (item.retweeted) R.mipmap.ic_launcher
                else R.mipmap.ic_launcher
            )

            tweetLikedImage.setImageResource(
                if (item.liked) R.mipmap.ic_launcher
                else R.mipmap.ic_launcher
            )
        }
    }
}

class MediaTweetHolder(view: View) : PlainTweetHolder(view) {
    override fun bind(item: Tweet, imageLoader: ImageLoader) {
        super.bind(item, imageLoader)

    }
}

class QuotedTweetHolder(view: View) : PlainTweetHolder(view) {
    override fun bind(item: Tweet, imageLoader: ImageLoader) {
        super.bind(item, imageLoader)

    }
}


// TODO - delegate