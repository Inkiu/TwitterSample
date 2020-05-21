package com.inkiu.data.di

import android.content.Context
import com.inkiu.data.api.*
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