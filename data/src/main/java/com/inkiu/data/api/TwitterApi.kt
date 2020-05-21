package com.inkiu.data.api

import com.inkiu.data.entities.TweetData
import com.inkiu.data.entities.UserData
import com.inkiu.domain.Constant
import retrofit2.http.GET
import retrofit2.http.Query

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