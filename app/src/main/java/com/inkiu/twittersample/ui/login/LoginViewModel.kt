package com.inkiu.twittersample.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.inkiu.twittersample.common.UserTokenManager
import com.inkiu.twittersample.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

class LoginViewModel(
    private val tokenManager: UserTokenManager
) : BaseViewModel() {

    private val viewState = LoginViewState()
    val viewStateData: MutableLiveData<LoginViewState>
            = MutableLiveData(viewState)

    override fun onAttached() {

    }

    fun onTokenArrived(token: String, secret: String) = launch {
        tokenManager.updateToken(token, secret)
        viewState.updateLoginState(LoginState.LoginSuccess).post()
    }

    private fun LoginViewState.post() {
        viewStateData.value = this
    }

}

@Singleton
class LoginVMFactory @Inject constructor(
    private val tokenManager: UserTokenManager
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(
            tokenManager
        ) as T
    }
}