package com.inkiu.data.di

import com.inkiu.data.repository.tweet.TweetRepositoryImpl
import com.inkiu.data.repository.user.UserRepositoryImpl
import com.inkiu.domain.repositoty.TweetRepository
import com.inkiu.domain.repositoty.UserRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindsTweetRepository(repositoryImpl: TweetRepositoryImpl): TweetRepository

    @Singleton
    @Binds
    abstract fun bindsUserRepository(repositoryImpl: UserRepositoryImpl): UserRepository

}