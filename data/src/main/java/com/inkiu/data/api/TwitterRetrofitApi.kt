package com.inkiu.data.api

import com.inkiu.data.entities.TweetData
import com.inkiu.data.entities.UserData
import retrofit2.http.GET
import retrofit2.http.Query

interface TwitterRetrofitApi {

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

    @GET("account/verify_credentials.json")
    suspend fun getMyProfile(): UserData

    @GET("users/show.json")
    suspend fun getUser(
        @Query("user_id") userIndex: Long
    ): UserData

    @GET("search/tweets.json")
    suspend fun searchTweets(
        @Query("query") query: String,
        @Query("since_id") sinceId: Long,
        @Query("count") count: Int
    ): List<TweetData>

}