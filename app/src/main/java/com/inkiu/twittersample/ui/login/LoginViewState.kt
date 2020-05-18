package com.inkiu.twittersample.ui.login

data class LoginViewState(
    private var _loginState: LoginState = LoginState.LOGIN_FAILED
) {

    val loginState: LoginState get() = _loginState

    fun updateLoginState(state: LoginState) = this.apply {
        _loginState = state
    }

}

enum class LoginState {
    LOGIN_FAILED, LOGIN_SUCCESS
}