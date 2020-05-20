package com.inkiu.twittersample.di

import com.inkiu.twittersample.di.detail.DetailModule
import com.inkiu.twittersample.di.home.HomeModule
import com.inkiu.twittersample.di.login.LoginModule
import com.inkiu.twittersample.ui.splash.SplashActivity
import com.inkiu.twittersample.di.splash.SplashModule
import com.inkiu.twittersample.ui.detail.DetailActivity
import com.inkiu.twittersample.ui.home.HomeActivity
import com.inkiu.twittersample.ui.login.LoginActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [SplashModule::class])
    @PerActivity
    abstract fun splashActivity(): SplashActivity

    @ContributesAndroidInjector(modules = [LoginModule::class])
    @PerActivity
    abstract fun loginActivity(): LoginActivity

    @ContributesAndroidInjector(modules = [HomeModule::class])
    @PerActivity
    abstract fun homeActivity(): HomeActivity

    @ContributesAndroidInjector(modules = [DetailModule::class])
    @PerActivity
    abstract fun detailActivity(): DetailActivity

}