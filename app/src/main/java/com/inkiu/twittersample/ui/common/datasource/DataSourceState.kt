package com.inkiu.twittersample.ui.common.datasource

sealed class DataSourceState {
    object Init : DataSourceState()
    object LoadingInitial : DataSourceState()
    object Loading : DataSourceState()
    object Success : DataSourceState()
    object Empty : DataSourceState()
    data class Failure(val throwable: Throwable?) : DataSourceState()
}