package com.inkiu.twittersample.ui.common

sealed class LoadingState {
    object Init : LoadingState()
    object LoadingInitial : LoadingState()
    object Loading : LoadingState()
    object Success : LoadingState()
    object Empty : LoadingState()
    data class Failure(val throwable: Throwable?) : LoadingState()
}