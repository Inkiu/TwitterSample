package com.inkiu.twittersample

import com.inkiu.twittersample.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class TwitterApp : DaggerApplication() {

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