package com.inkiu.twittersample.ui.common.tweets

import com.inkiu.twittersample.model.User

interface TweetClickListener {
    fun onClickTweet(user: User)
}