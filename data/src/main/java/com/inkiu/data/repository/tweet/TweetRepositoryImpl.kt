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

    override fun getTweet(id: Long): Single<TweetEntity> {
        return tweetLocalDataSource.getTweet(id)
            .flatMap {
                userLocalDataSource.updateUsers(listOf(it.user))
                    .andThen(Maybe.just(it))
            }
            .switchIfEmpty(Single.just(TweetData()))
            .map { tweetDataToEntityMapper(it) }
    }

    override fun getHomeTweets(startIndex: Long, count: Int): Single<List<TweetEntity>> {
        return tweetRemoteDataSource.getHomeTweets(startIndex, count)
            .flatMap {
                tweetLocalDataSource.updateTweets(it)
                    .andThen(Single.just(it))
            }
            .flatMap {
                userLocalDataSource.updateUsers(it.map { it.user })
                    .andThen(Single.just(it))
            }
            .map { list ->
                list.map { tweetDataToEntityMapper(it) }
            }
    }

    override fun getUserTweets(
        userIndex: Long,
        startIndex: Long,
        count: Int
    ): Single<List<TweetEntity>> {
        return tweetRemoteDataSource.getUserTweets(userIndex, startIndex, count)
            .doOnSuccess {
                tweetLocalDataSource.updateTweets(it)
            }
            .flatMap {
                userLocalDataSource.updateUsers(it.map { it.user })
                    .andThen(Single.just(it))
            }
            .map { list ->
                list.map { tweetDataToEntityMapper(it) }
            }
    }

}