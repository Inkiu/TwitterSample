package com.inkiu.twittersample.di

import android.content.Context
import com.inkiu.twittersample.ui.splash.SplashActivity
import com.inkiu.twittersample.di.PerActivity
import dagger.Binds
import dagger.Module
import javax.inject.Named

@Module
abstract class SplashModule {

    @Binds
    @PerActivity
    @Named("ActivityContext")
    abstract fun bindsActivityContext(activity: SplashActivity): Context

}