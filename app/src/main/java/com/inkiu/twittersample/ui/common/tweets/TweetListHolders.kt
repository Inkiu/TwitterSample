package com.inkiu.twittersample.ui.common.tweets

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.inkiu.twittersample.R
import com.inkiu.twittersample.common.image.ImageLoader
import com.inkiu.twittersample.ui.common.model.Tweet
import com.inkiu.twittersample.ui.common.tweets.datasource.DataSourceState
import kotlinx.android.synthetic.main.item_list_tweet.view.*
import kotlinx.android.synthetic.main.item_list_tweet_counts.view.*
import kotlinx.android.synthetic.main.item_list_tweet_profile.view.*
import kotlinx.android.synthetic.main.item_network_state.view.*
import java.lang.Exception

class NetworkStateItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(state: DataSourceState?) {
        with(itemView) {
            when (state) {
                is DataSourceState.Loading -> setLoading()
                is DataSourceState.Failure -> setError(state.throwable)
                else -> setComplete()
            }
        }
    }

    private fun setError(e: Throwable?) = with(itemView) {
        progress_bar.visibility = View.GONE
        error_msg.visibility = View.VISIBLE
        error_msg.text = e?.message ?: "UnknownError"
    }

    private fun setLoading() = with(itemView) {
        progress_bar.visibility = View.VISIBLE
        error_msg.visibility = View.GONE
    }

    private fun setComplete() = with(itemView) {
        progress_bar.visibility = View.GONE
        error_msg.visibility = View.GONE
    }
}

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
            imageLoader.loadCircle(item.user.profileUrl, profileImage)
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

class MediaTweetHolder(view: View) : PlainTweetHolder(view){

    override fun bind(item: Tweet, imageLoader: ImageLoader) {
        super.bind(item, imageLoader)
        val media = item.media ?: return
        MediaBindingDelegate.bind(
            itemView.tweetMediaContainer,
            media,
            imageLoader
        )
    }
}

class QuotedTweetHolder(view: View) : PlainTweetHolder(view) {
    override fun bind(item: Tweet, imageLoader: ImageLoader) {
        super.bind(item, imageLoader)
        val quoted = item.quoted ?: return
        QuotedBindingDelegate.bind(
            itemView.tweetQuotedContainer,
            quoted,
            imageLoader
        )

    }
}