package com.inkiu.twittersample.di.home

import com.inkiu.domain.repositoty.TweetRepository
import com.inkiu.domain.repositoty.UserRepository
import com.inkiu.domain.usecase.GetHomeTweets
import com.inkiu.twittersample.common.image.GlideImageLoader
import com.inkiu.twittersample.common.image.ImageLoader
import com.inkiu.twittersample.di.PerFragment
import com.inkiu.twittersample.ui.home.hometweet.HomeTweetsFragment
import dagger.Module
import dagger.Provides

@Module
class HomeTweetsModule {

//    @Provides
//    @PerFragment
//    fun provideGetHomeTweets(
//        userRepository: UserRepository,
//        tweetRepository: TweetRepository
//    ) = GetHomeTweets(userRepository, tweetRepository)
//
//    @Provides
//    @PerFragment
//    fun provideImageLoader(
//        fragment: HomeTweetsFragment
//    ): ImageLoader = GlideImageLoader(fragment)

}