package com.inkiu.twittersample.ui.common.tweets

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.inkiu.twittersample.R
import com.inkiu.twittersample.model.Tweet
import com.inkiu.twittersample.ui.common.tweets.datasource.DataSourceState

object TweetListTypeFactory {

    enum class Type(val layoutId: Int) {
        Plain(R.layout.item_list_tweet),
        Media(R.layout.item_list_tweet),
        Quoted(R.layout.item_list_tweet),
        MediaAndRetweet(R.layout.item_list_tweet),
        Loading(R.layout.item_network_state),
        Error(R.layout.item_network_state)
    }

    fun getLoadingType() = Type.Loading.ordinal

    // TODO - Any?
    fun type(any: Any?): Int {
        return if (any is Tweet) tweetType(any)
        else {
            when (any) {
                is DataSourceState -> Type.Loading.layoutId
                else -> Type.Error.layoutId
            }
        }
    }

    fun holder(typeId: Int, parent: ViewGroup): RecyclerView.ViewHolder {
        val type = Type.values()[typeId]
        val view = LayoutInflater.from(parent.context).inflate(type.layoutId, parent, false)
        return when(type) {
            Type.Plain -> PlainTweetHolder(view)
            Type.Media, Type.MediaAndRetweet -> MediaTweetHolder(view)
            Type.Quoted -> QuotedTweetHolder(view)
            Type.Loading, Type.Error -> NetworkStateItemViewHolder(view)
        }
    }

    private fun tweetType(tweet: Tweet): Int {
        return when {
            tweet.isMediaContained() && tweet.isQuotedContained() -> Type.MediaAndRetweet.ordinal
            tweet.isMediaContained() -> Type.Media.ordinal
            tweet.isQuotedContained() -> Type.Quoted.ordinal
            else -> Type.Plain.ordinal
        }
    }

}