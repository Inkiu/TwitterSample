package com.inkiu.data.api

import android.content.Context
import com.inkiu.data.entities.TweetData
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.reactivex.Single
import kotlin.random.Random

class TestTwitterApi(
    private val context: Context
) : TwitterApi {

    private val moshi: Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    override fun getHomeTweets(count: Int, fromTweetIndex: Long): Single<List<TweetData>> {
        return getTestTweets(count)
    }

    override fun getUserTweets(
        userIndex: Long,
        count: Int,
        fromTweetIndex: Long
    ): Single<List<TweetData>> {
        return getTestTweets(count)
    }

    private fun getTestTweets(
        count: Int
    ): Single<List<TweetData>> {
        return Single.fromCallable {
            val json = context.assets.open("test_data_tweets_100.txt").bufferedReader().use {
                it.readText()
            }
            val dataList = moshi.adapter<List<TweetData>>(
                Types.newParameterizedType(
                    List::class.java,
                    TweetData::class.java
                )
            ).fromJson(json) ?: emptyList()

            val range = dataList.count() - count
            val start = if (range == 0) 0 else Random.nextInt(0, dataList.count() - count)
            dataList.subList(start, start + count)
        }
    }

}