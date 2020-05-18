package com.inkiu.data.api

import com.inkiu.data.entities.TweetData
import retrofit2.http.GET
import retrofit2.http.Query

interface TweetRetrofitApi {

    @GET("statuses/home_timeline.json")
    suspend fun getHomeTweets(
        @Query("count") count: Int,
        @Query("since_id") fromIndex: Long?,
        @Query("tweet_mode") mode: String = "extended"
    ): List<TweetData>

    @GET("statuses/user_timeline.json")
    suspend fun getUserTweets(
        @Query("user_id") userIndex: Long,
        @Query("count") count: Int,
        @Query("since_id") fromIndex: Long? = null,
        @Query("tweet_mode") mode: String = "extended"
    ): List<TweetData>

}