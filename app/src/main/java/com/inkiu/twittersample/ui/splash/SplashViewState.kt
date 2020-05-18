package com.inkiu.twittersample.ui.splash

data class SplashViewState(
    private var _loginState: LoginState = LoginState.CHECKING
) {
    val loginState: LoginState get() = _loginState

    fun updateLoginState(state: LoginState) = this.apply {
        _loginState = state
    }
}

enum class LoginState { CHECKING, LOGGED_IN, LOGGED_OUT }