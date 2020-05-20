package com.inkiu.twittersample.ui.common.tweets

import com.inkiu.twittersample.ui.common.model.User

interface TweetClickListener {
    fun onClickTweet(user: User)
}