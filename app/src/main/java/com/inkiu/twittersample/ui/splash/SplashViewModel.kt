package com.inkiu.twittersample.ui.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.inkiu.twittersample.common.UserTokenManager
import com.inkiu.twittersample.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

class SplashViewModel(
    private val tokenManager: UserTokenManager
) : BaseViewModel() {

    private val viewState = SplashViewState()
    val viewStateData: MutableLiveData<SplashViewState>
        = MutableLiveData(viewState)

    fun onTokenArrived(token: String, secret: String) = launch {
        tokenManager.updateToken(token, secret)
        viewState.updateLoggedIn(true).post()
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