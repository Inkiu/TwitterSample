package com.inkiu.twittersample.ui.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.inkiu.twittersample.common.UserTokenManager
import com.inkiu.twittersample.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

class SplashViewModel(
    private val tokenManager: UserTokenManager
) : BaseViewModel() {

    private val viewState = SplashViewState()
    val viewStateData: MutableLiveData<SplashViewState>
        = MutableLiveData(viewState)

    override fun onAttached() {
        launch {
            viewState.updateLoginState(
                if (tokenManager.isLoggedIn()) LoginState.LOGGED_IN
                else LoginState.LOGGED_OUT
            )
            viewState.post()
        }
    }

    fun onTokenArrived(token: String, secret: String) = launch {
//        tokenManager.updateToken(token, secret)
//        viewState.updateLoggedIn(true).post()
    }

    private fun SplashViewState.post() {
        viewStateData.value = this
    }

}

@Singleton
class SplashVMFactory @Inject constructor(
    private val tokenManager: UserTokenManager
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SplashViewModel(
            tokenManager
        ) as T
    }
}