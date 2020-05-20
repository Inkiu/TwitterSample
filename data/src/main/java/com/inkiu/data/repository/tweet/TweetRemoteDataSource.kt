package com.inkiu.data.repository.tweet

import com.inkiu.data.api.TwitterApi
import com.inkiu.data.entities.TweetData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TweetRemoteDataSource @Inject constructor(
    private val twitterApi: TwitterApi
) {

    // Note - startIndex 가 -1 이라면 처음부터 enum ??
    suspend fun getHomeTweets(startIndex: Long, count: Int): List<TweetData> {
        return twitterApi.getHomeTweets(
            count = count,
            fromTweetIndex = startIndex
        )
    }

    // Note - startIndex 가 -1 이라면 처음부터
    suspend fun getUserTweets(userIndex: Long, startIndex: Long, count: Int): List<TweetData> {
        return twitterApi.getUserTweets(userIndex, count, startIndex)
    }

    suspend fun searchTweets(query: String, sinceId: Long, count: Int): List<TweetData> {
        return twitterApi.searchTweets(query, sinceId, count)
    }
}