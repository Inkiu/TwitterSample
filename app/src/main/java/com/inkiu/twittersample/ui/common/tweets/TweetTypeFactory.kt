package com.inkiu.twittersample.ui.common.tweets

import android.view.LayoutInflater
import android.view.ViewGroup
import com.inkiu.twittersample.R
import com.inkiu.twittersample.ui.common.tweets.holders.MediaTweetHolder
import com.inkiu.twittersample.ui.common.tweets.holders.PlainTweetHolder
import com.inkiu.twittersample.ui.common.tweets.holders.QuotedTweetHolder
import com.inkiu.twittersample.ui.common.model.Tweet

object TweetTypeFactory {

    enum class Type(val layoutId: Int) {
        Error(R.layout.item_list_tweet), // TODO - 다른 거로
        Plain(R.layout.item_list_tweet),
        Media(R.layout.item_list_tweet),
        Quoted(R.layout.item_list_tweet),
        MediaAndRetweet(R.layout.item_list_tweet)
    }

    fun type(tweet: Tweet?): Int {
        return when {
            tweet == null -> Type.Error.ordinal
            tweet.isMediaContained() && tweet.isQuotedContained() -> Type.MediaAndRetweet.ordinal
            tweet.isMediaContained() -> Type.Media.ordinal
            tweet.isQuotedContained() -> Type.Quoted.ordinal
            else -> Type.Plain.ordinal
        }
    }

    // TODO - clickListener
    fun holder(typeId: Int, parent: ViewGroup): TweetViewHolder<Tweet> {
        val type = Type.values()[typeId]
        val view = LayoutInflater.from(parent.context)
            .inflate(type.layoutId, parent, false)
        return when(type) {
            Type.Plain -> PlainTweetHolder(view)
            Type.Media -> MediaTweetHolder(view)
            Type.Quoted -> QuotedTweetHolder(view)
            else -> PlainTweetHolder(view) // TODO - error holder
        }
    }

}