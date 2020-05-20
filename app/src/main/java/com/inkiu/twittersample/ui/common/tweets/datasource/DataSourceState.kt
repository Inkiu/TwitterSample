package com.inkiu.twittersample.ui.common.tweets.datasource

sealed class DataSourceState {
    object Init : DataSourceState()
    object Loading : DataSourceState()
    object Success : DataSourceState()
    data class Failure(val throwable: Throwable?) : DataSourceState()
}