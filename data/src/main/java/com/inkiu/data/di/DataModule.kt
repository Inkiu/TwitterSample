package com.inkiu.data.di

import dagger.Module

@Module(
    includes = [
        NetworkModule::class,
        DataSourceModule::class,
        RepositoryModule::class
    ]
)
class DataModule