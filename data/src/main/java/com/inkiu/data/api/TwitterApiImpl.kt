package com.inkiu.data.api

import com.inkiu.data.api.interceptors.RemainCallLoggingInterceptor
import com.inkiu.data.entities.TweetData
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer
import se.akerfeldt.okhttp.signpost.SigningInterceptor

class TwitterApiImpl(
    consumerKey: String,
    consumerSecret: String,
    userToken: String,
    userTokenSecret: String,
    baseUrl: String,
    private val logger: ApiLogger
) : TwitterApi {

    private val moshi: Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val api: TweetRetrofitApi

    init {
        val signingInterceptor = OkHttpOAuthConsumer(consumerKey, consumerSecret)
            .apply { setTokenWithSecret(userToken, userTokenSecret) }
            .let { SigningInterceptor(it) }

        // note - httpLogger 업데이트 하면서 { } 초기화 되지 않
        val httpLoggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                logger.log(message)
            }
        }).apply { level = HttpLoggingInterceptor.Level.BASIC }

        val remainCallLoggingInterceptor = RemainCallLoggingInterceptor(logger::log)

        val httpClient = OkHttpClient.Builder()
            .addInterceptor(signingInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(remainCallLoggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

        api = retrofit.create(TweetRetrofitApi::class.java)
    }

    override suspend fun getHomeTweets(
        count: Int,
        fromTweetIndex: Long
    ): List<TweetData> {
        return api.getHomeTweets(
            count,
            if (fromTweetIndex == -1L) null else fromTweetIndex
        )
    }

    override suspend fun getUserTweets(
        userIndex: Long,
        count: Int,
        fromTweetIndex: Long
    ): List<TweetData> {
        return api.getUserTweets(
            userIndex,
            count,
            if (fromTweetIndex == -1L) null else fromTweetIndex
        )
    }

}