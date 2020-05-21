package com.inkiu.twittersample.di

import android.content.Context
import com.inkiu.domain.repositoty.TweetRepository
import com.inkiu.domain.repositoty.UserRepository
import com.inkiu.domain.usecase.GetReplyTweets
import com.inkiu.domain.usecase.GetUserDetail
import com.inkiu.domain.usecase.GetUserTweets
import com.inkiu.twittersample.common.image.GlideImageLoader
import com.inkiu.twittersample.common.image.ImageLoader
import com.inkiu.twittersample.di.PerActivity
import com.inkiu.twittersample.ui.detail.DetailActivity
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
abstract class DetailModule {

    @Module
    companion object {
        @JvmStatic
        @Provides
        @Named("user_id")
        @PerActivity
        fun provideUserId(detailActivity: DetailActivity) =
            detailActivity.intent.getLongExtra(DetailActivity.ARG_USER_ID, 0)

        @JvmStatic
        @Provides
        @PerActivity
        fun provideGetUserDetail(
            userRepository: UserRepository
        ) = GetUserDetail(userRepository)

        @JvmStatic
        @Provides
        @PerActivity
        fun provideGetUserTweets(
            tweetRepository: TweetRepository,
            userRepository: UserRepository
        ) = GetUserTweets(userRepository, tweetRepository)
    }

    @Binds
    @PerActivity
    @Named("ActivityContext")
    abstract fun bindsActivityContext(activity: DetailActivity): Context

    @Binds
    @PerActivity
    abstract fun bindsImageLoader(glideImageLoader: GlideImageLoader): ImageLoader
}