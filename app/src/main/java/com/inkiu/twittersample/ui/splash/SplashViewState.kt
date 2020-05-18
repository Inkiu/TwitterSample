package com.inkiu.twittersample.ui.splash

data class SplashViewState(
    private var _isLoading: Boolean = false,
    private var _isLoggedIn: Boolean = false
) {
    val isLoading: Boolean get() =  _isLoading
    val isLoggedIn: Boolean get() = _isLoggedIn

    fun updateLoading(on: Boolean) = this.apply {
        _isLoading = on
    }

    fun updateLoggedIn(on: Boolean) = this.apply {
        _isLoggedIn = on
    }

}