package com.inkiu.data.repository.tweet

import com.inkiu.data.entities.TweetData

class TweetLocalDataSource {
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