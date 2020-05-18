package com.inkiu.twittersample

import com.inkiu.twittersample.di.DaggerAppComponent
import com.twitter.sdk.android.core.Twitter
import com.twitter.sdk.android.core.TwitterAuthConfig
import com.twitter.sdk.android.core.TwitterConfig
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class TwitterApp : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        initTwitter()
    }

    private fun initTwitter() {
        val authConfig = TwitterAuthConfig(
            BuildConfig.CONSUMER_KEY,
            BuildConfig.CONSUMER_SECRET
        )

        val twitterConfig = TwitterConfig.Builder(this)
            .twitterAuthConfig(authConfig)
            .build()

        Twitter.initialize(twitterConfig)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent
            .builder()
            .applicationContext(this)
            .consumerKey(BuildConfig.CONSUMER_KEY)
            .consumerSecret(BuildConfig.CONSUMER_SECRET)
            .baseUrl(getString(R.string.twitter_base_url))
            .build()
    }

}