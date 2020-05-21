package com.inkiu.twittersample.di

import android.content.Context
import com.inkiu.twittersample.di.PerActivity
import com.inkiu.twittersample.ui.login.LoginActivity
import dagger.Binds
import dagger.Module
import javax.inject.Named

@Module
abstract class LoginModule {

    @Binds
    @PerActivity
    @Named("ActivityContext")
    abstract fun bindsActivityContext(activity: LoginActivity): Context

}