package com.inkiu.twittersample.di

import android.content.Context
import androidx.core.content.ContextCompat
import com.inkiu.data.api.ApiLogger
import com.inkiu.data.api.TokenProvider
import com.inkiu.twittersample.R
import com.inkiu.twittersample.common.UserTokenManager
import com.inkiu.twittersample.common.ApiLoggerImpl
import com.inkiu.twittersample.model.mapper.TextComposeToSpannableMapper
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
abstract class AppModule {

    @Module
    companion object {
        @JvmStatic
        @Provides
        fun provideTextComposeSpannableMapper(
            @Named("ApplicationContext") context: Context
        ) = TextComposeToSpannableMapper(ContextCompat.getColor(context, R.color.hyperlink_blue))
    }

    @Binds
    abstract fun bindsTokenProvider(tokenManager: UserTokenManager): TokenProvider

    @Binds
    abstract fun bindsApiLogger(apiLoggerImpl: ApiLoggerImpl): ApiLogger

}