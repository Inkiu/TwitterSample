package com.inkiu.data.api

import com.inkiu.data.model.TweetData
import com.inkiu.data.model.UserData
import com.inkiu.domain.Constant

interface TwitterApi {

    suspend fun getHomeTweets(
        count: Int,
        fromTweetIndex: Long = Constant.INVALID_ID
    ): List<TweetData>

    suspend fun getUserTweets(
        userIndex: Long,
        count: Int,
        fromTweetIndex: Long = Constant.INVALID_ID
    ): List<TweetData>

    suspend fun getMyProfile(): UserData

    suspend fun getUser(
        userIndex: Long
    ): UserData

    suspend fun searchTweets(
        query: String,
        sinceId: Long,
        count: Int
    ): List<TweetData>

}