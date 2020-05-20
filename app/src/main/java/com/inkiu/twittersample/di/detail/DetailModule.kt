package com.inkiu.twittersample.di.detail

import android.content.Context
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
    }

    @Binds
    @PerActivity
    @Named("ActivityContext")
    abstract fun bindsActivityContext(activity: DetailActivity): Context

    @Binds
    @PerActivity
    abstract fun bindsImageLoader(glideImageLoader: GlideImageLoader): ImageLoader
}