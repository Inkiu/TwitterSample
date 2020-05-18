package com.inkiu.twittersample.di

import android.content.Context
import com.inkiu.data.di.DataModule
import com.inkiu.twittersample.TwitterApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Named
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBindingModule::class,
        DataModule::class
    ]
)
interface AppComponent : AndroidInjector<TwitterApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun applicationContext(@Named("ApplicationContext") applicationContext: Context): Builder

        @BindsInstance
        fun consumerKey(@Named("consumerKey") key: String): Builder

        @BindsInstance
        fun consumerSecret(@Named("consumerSecret") secret: String): Builder

        @BindsInstance
        fun baseUrl(@Named("consumerSecret") secret: String): Builder

        fun build(): AppComponent
    }

}