package com.inkiu.data.repository.tweet

import com.inkiu.data.entities.TweetData
import com.inkiu.data.mapper.TweetDataToEntityMapper
import com.inkiu.data.repository.user.UserLocalDataSource
import com.inkiu.domain.entities.tweet.TweetEntity
import com.inkiu.domain.repositoty.TweetRepository
import io.reactivex.Maybe
import io.reactivex.Single

class TweetRepositoryImpl(
    private val tweetLocalDataSource: TweetLocalDataSource,
    private val tweetRemoteDataSource: TweetRemoteDataSource,
    private val userLocalDataSource: UserLocalDataSource,
    private val tweetDataToEntityMapper: TweetDataToEntityMapper
) : TweetRepository {

    override suspend fun getTweet(id: Long): TweetEntity {
        val tweet = tweetLocalDataSource.getTweet(id)
        return tweetDataToEntityMapper(tweet)
    }

    override suspend fun getHomeTweets(startIndex: Long, count: Int): List<TweetEntity> {
        val tweets = tweetRemoteDataSource.getHomeTweets(startIndex, count)
        tweetLocalDataSource.updateTweets(tweets)
        userLocalDataSource.updateUsers(tweets.map { it.user })
        return tweets.map { tweetDataToEntityMapper(it) }
    }

    override suspend fun getUserTweets(
        userIndex: Long,
        startIndex: Long,
        count: Int
    ): List<TweetEntity> {
        val tweets = tweetRemoteDataSource.getUserTweets(userIndex, startIndex, count)
        tweetLocalDataSource.updateTweets(tweets)
        userLocalDataSource.updateUsers(tweets.map { it.user })
        return tweets.map { tweetDataToEntityMapper(it) }
    }

}