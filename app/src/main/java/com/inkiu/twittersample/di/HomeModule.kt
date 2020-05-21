package com.inkiu.twittersample.di

import android.content.Context
import com.inkiu.domain.repositoty.TweetRepository
import com.inkiu.domain.repositoty.UserRepository
import com.inkiu.domain.usecase.GetHomeTweets
import com.inkiu.domain.usecase.GetUser
import com.inkiu.twittersample.common.image.GlideImageLoader
import com.inkiu.twittersample.common.image.ImageLoader
import com.inkiu.twittersample.ui.home.HomeActivity
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
abstract class HomeModule {

    @Module
    companion object {
        @JvmStatic
        @Provides
        @PerActivity
        fun provideGetHomeTweets(
            userRepository: UserRepository,
            tweetRepository: TweetRepository
        ) = GetHomeTweets(userRepository, tweetRepository)

        @JvmStatic
        @Provides
        @PerActivity
        fun provideGetUser(
            userRepository: UserRepository,
            tweetRepository: TweetRepository
        ) = GetUser(userRepository)
    }

    @Binds
    @PerActivity
    @Named("ActivityContext")
    abstract fun bindsActivityContext(activity: HomeActivity): Context

    @Binds
    @PerActivity
    abstract fun bindsImageLoader(glideImageLoader: GlideImageLoader): ImageLoader

}