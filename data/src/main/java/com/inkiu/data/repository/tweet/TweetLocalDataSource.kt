package com.inkiu.data.repository.tweet

import com.inkiu.data.entities.TweetData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TweetLocalDataSource @Inject constructor() {
    private val cache = mutableMapOf<Long, TweetData>()

    fun updateTweets(tweets: List<TweetData>) {
        tweets.forEach { cache[it.id] = it }
    }

    fun getTweet(index: Long): TweetData {
        return cache[index] ?: TweetData()
    }

    fun getTweets(indices: List<Long>): List<TweetData> {
        return indices.map { tweetIndex ->
            cache[tweetIndex] ?: TweetData()
        }
    }
}