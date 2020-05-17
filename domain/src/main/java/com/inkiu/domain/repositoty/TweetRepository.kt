package com.inkiu.domain.repositoty

import com.inkiu.domain.entities.tweet.TweetEntity

interface TweetRepository {

    fun getTweet(id: Long): TweetEntity

    fun getHomeTweets(): List<TweetEntity>

    fun getUserTweets(userIndex: Long): List<TweetEntity>

}