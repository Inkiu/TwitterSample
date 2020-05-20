package com.inkiu.domain.repositoty

import com.inkiu.domain.entities.tweet.TweetEntity

interface TweetRepository {

    suspend fun getTweet(id: Long): TweetEntity

    suspend fun getHomeTweets(startIndex: Long, count: Int): List<TweetEntity>

    suspend fun getUserTweets(userIndex: Long, startIndex: Long, count: Int): List<TweetEntity>

    suspend fun searchTweets(query: String, sinceId: Long, count: Int): List<TweetEntity>

}