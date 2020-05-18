package com.inkiu.twittersample.di

import com.inkiu.data.api.ApiLogger
import com.inkiu.data.api.TokenProvider
import com.inkiu.twittersample.common.UserTokenManager
import com.inkiu.twittersample.ui.common.ApiLoggerImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class AppModule {

    @Binds
    abstract fun bindsTokenProvider(tokenManager: UserTokenManager): TokenProvider

    @Binds
    abstract fun bindsApiLogger(apiLoggerImpl: ApiLoggerImpl): ApiLogger

}