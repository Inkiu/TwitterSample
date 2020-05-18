package com.inkiu.data.api

import com.inkiu.data.entities.TweetData
import io.reactivex.Single

interface TwitterApi {

    suspend fun getHomeTweets(
        count: Int,
        fromTweetIndex: Long = -1L
    ): List<TweetData>

    suspend fun getUserTweets(
        userIndex: Long,
        count: Int,
        fromTweetIndex: Long = -1L
    ): List<TweetData>

}