package com.inkiu.data.api

import android.content.Context
import com.inkiu.data.entities.TweetData
import com.inkiu.data.entities.UserData
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlin.random.Random

class TestTwitterApi(
    private val context: Context
) : TwitterApi {

    private val moshi: Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    override suspend fun getHomeTweets(count: Int, fromTweetIndex: Long): List<TweetData> {
        return getTestTweets(count)
    }

    override suspend fun getUserTweets(
        userIndex: Long,
        count: Int,
        fromTweetIndex: Long
    ): List<TweetData> {
        return getTestTweets(count)
    }

    private fun getTestTweets(
        count: Int
    ): List<TweetData> {
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
        return dataList.subList(start, start + count)
    }

    override suspend fun getMyProfile(): UserData {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getUser(userIndex: Long): UserData {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun searchTweets(query: String, sinceId: Long, count: Int): List<TweetData> {
        return getTestTweets(count)
    }
}