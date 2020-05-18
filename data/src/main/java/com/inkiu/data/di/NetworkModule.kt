package com.inkiu.data.di

import com.inkiu.data.api.ApiLogger
import com.inkiu.data.api.TokenProvider
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
        @Named("baseUrl") baseUrl: String,
        tokenProvider: TokenProvider,
        logger: ApiLogger
    ) : TwitterApi = TwitterApiImpl(
        consumerKey,
        consumerSecret,
        tokenProvider,
        baseUrl,
        logger
    )

}