package com.inkiu.data.di

import com.inkiu.data.api.ApiLogger
import com.inkiu.data.api.TwitterApi
import com.inkiu.data.api.TwitterApiImpl
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideTwitterApi(
        @Named("consumerKey") consumerKey: String,
        @Named("consumerSecret") consumerSecret: String,
        @Named("userToken") userToken: String,
        @Named("userTokenSecret") userTokenSecret: String,
        @Named("baseUrl") baseUrl: String,
        logger: ApiLogger
    ) : TwitterApi = TwitterApiImpl(
        consumerKey,
        consumerSecret,
        userToken,
        userTokenSecret,
        baseUrl,
        logger
    )

}