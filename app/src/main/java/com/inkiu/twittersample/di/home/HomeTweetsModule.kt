package com.inkiu.twittersample.di.home

import com.inkiu.domain.repositoty.TweetRepository
import com.inkiu.domain.repositoty.UserRepository
import com.inkiu.domain.usecase.GetHomeTweets
import com.inkiu.twittersample.di.PerFragment
import dagger.Module
import dagger.Provides

@Module
class HomeTweetsModule {

    @Provides
    @PerFragment
    fun provideGetHomeTweets(
        userRepository: UserRepository,
        tweetRepository: TweetRepository
    ) = GetHomeTweets(userRepository, tweetRepository)

}