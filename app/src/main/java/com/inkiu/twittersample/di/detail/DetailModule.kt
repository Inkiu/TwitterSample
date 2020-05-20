package com.inkiu.twittersample.di.detail

import android.content.Context
import com.inkiu.domain.repositoty.TweetRepository
import com.inkiu.domain.usecase.GetReplyTweets
import com.inkiu.domain.usecase.GetTweet
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
        @Named("tweet_id")
        @PerActivity
        fun provideTweetId(detailActivity: DetailActivity) =
            detailActivity.intent.getLongExtra(DetailActivity.ARG_TWEET_ID, 0)

        @JvmStatic
        @Provides
        @PerActivity
        fun providerGetTweet(
            tweetRepository: TweetRepository
        ) = GetTweet(tweetRepository)

        @JvmStatic
        @Provides
        @PerActivity
        fun provideGetReplyTweets(
            tweetRepository: TweetRepository
        ) = GetReplyTweets(tweetRepository)
    }

    @Binds
    @PerActivity
    @Named("ActivityContext")
    abstract fun bindsActivityContext(activity: DetailActivity): Context

    @Binds
    @PerActivity
    abstract fun bindsImageLoader(glideImageLoader: GlideImageLoader): ImageLoader
}