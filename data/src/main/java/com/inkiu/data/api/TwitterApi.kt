package com.inkiu.data.api

import com.inkiu.data.entities.TweetData
import com.inkiu.data.entities.UserData
import retrofit2.http.GET
import retrofit2.http.Query

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

    suspend fun getMyProfile(): UserData

    suspend fun getUser(
        @Query("user_id") userIndex: Long
    ): UserData

}