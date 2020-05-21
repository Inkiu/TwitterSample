package com.inkiu.data.repository.tweet

import com.inkiu.data.mapper.TweetDataToEntityMapper
import com.inkiu.data.repository.user.UserLocalDataSource
import com.inkiu.domain.entities.tweet.TweetEntity
import com.inkiu.domain.repositoty.TweetRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TweetRepositoryImpl @Inject constructor(
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

    override suspend fun searchTweets(query: String, sinceId: Long, count: Int): List<TweetEntity> {
        return tweetRemoteDataSource.searchTweets(query, sinceId, count).also {
            tweetLocalDataSource.updateTweets(it)
        }.let { results ->
            results.map { tweetDataToEntityMapper(it) }
        }
    }
}