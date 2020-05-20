package com.inkiu.twittersample.di.home

import android.content.Context
import com.inkiu.domain.repositoty.TweetRepository
import com.inkiu.domain.repositoty.UserRepository
import com.inkiu.domain.usecase.GetHomeTweets
import com.inkiu.domain.usecase.GetUserDetail
import com.inkiu.twittersample.common.image.GlideImageLoader
import com.inkiu.twittersample.common.image.ImageLoader
import com.inkiu.twittersample.di.PerActivity
import com.inkiu.twittersample.di.PerFragment
import com.inkiu.twittersample.ui.home.HomeActivity
import com.inkiu.twittersample.ui.home.homeprofile.ProfileFragment
import com.inkiu.twittersample.ui.home.hometweet.HomeTweetsFragment
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import javax.inject.Named

@Module
abstract class HomeModule {

    @ContributesAndroidInjector(modules = [HomeTweetsModule::class])
    @PerFragment
    abstract fun homeTweetsFragment(): HomeTweetsFragment

    @ContributesAndroidInjector(modules = [ProfileModule::class])
    @PerFragment
    abstract fun profileFragment(): ProfileFragment

    @Module
    companion object {
        @JvmStatic
        @Provides
        @PerActivity
        fun provideGetHomeTweets(
            userRepository: UserRepository,
            tweetRepository: TweetRepository
        ) = GetHomeTweets(userRepository, tweetRepository)
    }

    @Binds
    @PerActivity
    @Named("ActivityContext")
    abstract fun bindsActivityContext(activity: HomeActivity): Context

    @Binds
    @PerActivity
    abstract fun bindsImageLoader(glideImageLoader: GlideImageLoader): ImageLoader

}