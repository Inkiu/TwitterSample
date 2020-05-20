package com.inkiu.twittersample.ui.common.tweets

interface TweetClickListener {
    fun onClickTweet(tweetId: Long)
    fun onClickUser(userId: Long)
}