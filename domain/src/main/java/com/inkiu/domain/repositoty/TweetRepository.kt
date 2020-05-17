package com.inkiu.domain.repositoty

import com.inkiu.domain.entities.tweet.TweetEntity
import io.reactivex.Single

interface TweetRepository {

    fun getTweet(id: Long): Single<TweetEntity>

    fun getHomeTweets(startIndex: Long, count: Int): Single<List<TweetEntity>>

    fun getUserTweets(userIndex: Long, startIndex: Long, count: Int): Single<List<TweetEntity>>

}