package com.inkiu.data.repository.tweet

import com.inkiu.data.entities.TweetData
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

class TweetLocalDataSource {
    private val cache = mutableMapOf<Long, TweetData>()

    fun updateTweets(tweets: List<TweetData>): Completable {
        return Completable.fromCallable {
            tweets.forEach { cache[it.id] = it }
        }
    }

    fun getTweet(index: Long): Maybe<TweetData> {
        return Maybe.fromCallable {
            cache[index]
        }
    }

    fun getTweets(indices: List<Long>): Single<List<TweetData>> {
        return Single.fromCallable {
            indices.map { tweetIndex ->
                cache[tweetIndex] ?: TweetData()
            }
        }
    }
}