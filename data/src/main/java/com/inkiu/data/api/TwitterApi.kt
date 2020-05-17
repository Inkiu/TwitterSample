package com.inkiu.data.api

import com.inkiu.data.entities.TweetData
import io.reactivex.Single

interface TwitterApi {

    fun getHomeTweets(
        count: Int,
        fromTweetIndex: Long = -1L
    ): Single<List<TweetData>>

    fun getUserTweets(
        userIndex: Long,
        count: Int,
        fromTweetIndex: Long = -1L
    ): Single<List<TweetData>>

}