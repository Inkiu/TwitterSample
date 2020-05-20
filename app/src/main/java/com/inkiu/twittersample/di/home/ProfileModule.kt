package com.inkiu.twittersample.di.home

import com.inkiu.domain.repositoty.TweetRepository
import com.inkiu.domain.repositoty.UserRepository
import com.inkiu.domain.usecase.GetUser
import com.inkiu.domain.usecase.GetUserTweets
import com.inkiu.twittersample.common.image.GlideImageLoader
import com.inkiu.twittersample.common.image.ImageLoader
import com.inkiu.twittersample.di.PerFragment
import com.inkiu.twittersample.ui.home.homeprofile.ProfileFragment
import dagger.Module
import dagger.Provides

@Module
class ProfileModule {

//    @Provides
//    @PerFragment
//    fun provideGetUserTweets(
//        userRepository: UserRepository,
//        tweetRepository: TweetRepository
//    ) = GetUserTweets(userRepository, tweetRepository)
//
//    @Provides
//    @PerFragment
//    fun provideGetUser(
//        userRepository: UserRepository
//    ) = GetUser(userRepository)
//
//    @Provides
//    @PerFragment
//    fun provideImageLoader(
//        fragment: ProfileFragment
//    ): ImageLoader = GlideImageLoader(fragment)
}