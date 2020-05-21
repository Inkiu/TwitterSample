package com.inkiu.twittersample.ui.login

data class LoginViewState(
    private var _loginState: LoginState = LoginState.LoginFailed(null)
) {

    val loginState: LoginState get() = _loginState

    fun updateLoginState(state: LoginState) = this.apply {
        _loginState = state
    }

}

sealed class LoginState {
    object LoginSuccess : LoginState()
    data class LoginFailed(val e: Throwable?) : LoginState()
}